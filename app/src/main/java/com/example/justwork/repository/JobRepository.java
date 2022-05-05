package com.example.justwork.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.justwork.model.Job;
import com.example.justwork.model.JobApplication;

import java.util.ArrayList;
import java.util.List;

public class JobRepository{

    private MutableLiveData<List<Job>> companyJobs;
    private MutableLiveData<List<JobApplication>> userJobApplications;
    private static JobRepository instance;

    private JobRepository(){
        companyJobs = new MutableLiveData<>();
        List<Job> NewListJob = new ArrayList<>();
        companyJobs.setValue(NewListJob);

        userJobApplications = new MutableLiveData<>();
        List<JobApplication> NewListJobApplication = new ArrayList<>();
        userJobApplications.setValue(NewListJobApplication);
    }

    public static JobRepository getInstance(){
        if(instance==null){
            instance=new JobRepository();
        }
        return instance;
    }

    public LiveData<List<Job>> getJobs(){
        return companyJobs;
    }

    public void addJob(Job job){
        List<Job> currentJobs = companyJobs.getValue();
        currentJobs.add(job);
        companyJobs.setValue(currentJobs);
    }


    public LiveData<List<JobApplication>> getJobApplications(String username) {

        List<JobApplication> jobApplications  = userJobApplications.getValue();
        for (JobApplication jobApplication: jobApplications) {
            if(!jobApplication.getJobApplicationID().equals(username)){ // if the username for this job application doesn't equal,
                //it will be removed from list. List will be set only to those applications that have the username.
                jobApplications.remove(jobApplication);
            }
        }
        userJobApplications.setValue(jobApplications);
        return userJobApplications;
    }

    public void addJobApplication(JobApplication jobApplication, String username) {
        jobApplication.setJobApplicationID(username);
        List<JobApplication> jobApplicationsToReturn  = userJobApplications.getValue();
        jobApplicationsToReturn.add(jobApplication);
        userJobApplications.setValue(jobApplicationsToReturn);
    }
}
