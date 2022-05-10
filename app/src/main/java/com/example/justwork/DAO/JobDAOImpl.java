package com.example.justwork.DAO;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.example.justwork.model.Job;
import com.example.justwork.model.JobApplication;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class JobDAOImpl implements JobDAO{

    private DatabaseReference databaseReference;
    private MutableLiveData<List<Job>> Jobs;
    private MutableLiveData<List<JobApplication>> jobApplications;
    private Job toGetJob;
    private JobApplication toGetJobApplication;
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
    public MutableLiveData<List<JobApplication>> getJobApplicationsForCompany(int cvr) {

        databaseReference.child("JobApplication").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                List<JobApplication> jobsApplToGet = new ArrayList<>();


                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                    JobApplication temp= dataSnapshot.getValue(JobApplication.class);


                    if (temp.getCompanyCvr() == cvr){
                        jobsApplToGet.add(temp);
                    }
                }

                jobApplications.setValue(jobsApplToGet);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        return jobApplications;
    }

    @Override
    public MutableLiveData<List<Job>> getCompanyJobs(int cvr) {

        databaseReference.child("Jobs").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                List<Job> jobsToGet = new ArrayList<>();

                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                    Job tempJob = dataSnapshot.getValue(Job.class);


                    if (tempJob.getCompanyCvr() == cvr){
                        jobsToGet.add(tempJob);
                    }
                }

                Jobs.setValue(jobsToGet);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        return Jobs;
    }

    @Override
    public Job getCompanyJobById(String id) {


        databaseReference.child("Jobs").child(id+"").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                toGetJob = snapshot.getValue(Job.class);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        return toGetJob;
    }

    @Override
    public JobApplication getJobApplicationById(String id) {

        databaseReference.child("JobApplication").child(id+"").addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                toGetJobApplication = snapshot.getValue(JobApplication.class);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        return toGetJobApplication;
    }
}
