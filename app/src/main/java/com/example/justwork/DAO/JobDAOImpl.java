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
}
