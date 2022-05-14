package com.example.justwork.DAO;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.example.justwork.model.Company;
import com.example.justwork.model.Job;
import com.example.justwork.model.JobApplication;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class JobDAOImpl implements JobDAO{

    private DatabaseReference databaseReference;
    private MutableLiveData<List<Job>> Jobs;
    private MutableLiveData<List<JobApplication>> jobApplications;
    private static JobDAO jobDAOInstance;


    public JobDAOImpl(){
        databaseReference = FirebaseDatabase.getInstance().getReference();
        List<Job> ifNullJob = new ArrayList<>();
        List<JobApplication> ifNullJobApplications = new ArrayList<>();

        Jobs = new MutableLiveData<>();
        jobApplications = new MutableLiveData<>();
        Jobs.setValue(ifNullJob);
        jobApplications.setValue(ifNullJobApplications);
    }

    public static JobDAO getInstance() {
        if(jobDAOInstance==null){
            jobDAOInstance = new JobDAOImpl();
        }
        return jobDAOInstance;
    }

    @Override
    public void PostJob(Job job) {
        String jobIdGenerate = databaseReference.push().getKey();
        job.setId(jobIdGenerate);
        databaseReference.child("Jobs").child(job.getId()).setValue(job);
    }

    @Override
    public void AddJobApplication(JobApplication jobApplication) {
        String jobApplicationIdGenerate = databaseReference.push().getKey();
        jobApplication.setJobApplicationId(jobApplicationIdGenerate);
        databaseReference.child("JobApplication").child(jobApplication.getJobApplicationId()).setValue(jobApplication);
    }

    @Override
    public void updateJobApplication(JobApplication jobApplication) {
        databaseReference.child("JobApplication").child(jobApplication.getJobApplicationId()).setValue(jobApplication);
    }

    @Override
    public void DeleteJobApplication(JobApplication jobApplication) {
        databaseReference.child("JobApplication").child(jobApplication.getJobApplicationId()).removeValue();
    }

    @Override
    public void applyForJob(long userCpr,
                            int companyCvr, String jobId, String firstName, String lastName,
                            String email, String message, String country, String status) {
        String jobApplicationId = databaseReference.push().getKey();
        JobApplication jobApplication = new JobApplication(jobApplicationId,userCpr,companyCvr,jobId,firstName,lastName,
                email,message,country,status);
        databaseReference.child("JobApplication").child(jobApplication.getJobApplicationId()).setValue(jobApplication);

    }

    @Override
    public void cancelJobApplication(String id) {
        List<JobApplication> tempjobsapplications = jobApplications.getValue();
        Query query = databaseReference.child("JobApplication").child(id);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                snapshot.getRef().removeValue();
                tempjobsapplications.remove(snapshot.getValue(JobApplication.class));
                jobApplications.setValue(tempjobsapplications);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        System.out.println("Job application deleted successfully");
        System.out.println(databaseReference.child("JobApplication").child(id) );
        databaseReference.child("JobApplication").child(id).setValue(null);
        System.out.println("Set that job application to null");


    }

}
