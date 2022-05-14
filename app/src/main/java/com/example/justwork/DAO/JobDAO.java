package com.example.justwork.DAO;

import androidx.lifecycle.MutableLiveData;

import com.example.justwork.model.Job;
import com.example.justwork.model.JobApplication;

import java.util.List;

public interface JobDAO {

    void PostJob(Job job);

    void AddJobApplication(JobApplication jobApplication);

    void updateJobApplication(JobApplication jobApplication);
    void applyForJob(long userCpr, int companyCvr, String jobId, String firstName, String lastName,
                     String email, String message, String country, String status);


    void cancelJobApplication(String id);
}
