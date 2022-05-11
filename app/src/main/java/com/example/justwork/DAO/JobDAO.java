package com.example.justwork.DAO;

import androidx.lifecycle.MutableLiveData;

import com.example.justwork.model.Job;
import com.example.justwork.model.JobApplication;

import java.util.List;

public interface JobDAO {

    void PostJob(Job job);

    void AddJobApplication(JobApplication jobApplication);

    MutableLiveData<List<JobApplication>> getJobApplicationsForCompany(int cvr);

    MutableLiveData<List<Job>> getCompanyJobs(int cvr);

    Job getCompanyJobById(String id);

    JobApplication getJobApplicationById(String id);

    void updateJobApplication(JobApplication jobApplication);


}
