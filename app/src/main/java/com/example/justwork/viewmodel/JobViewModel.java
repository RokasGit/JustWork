package com.example.justwork.viewmodel;

import android.app.Application;
import android.content.res.Resources;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.justwork.model.Job;
import com.example.justwork.model.JobApplication;
import com.example.justwork.repository.JobRepository;

import java.util.List;

public class JobViewModel extends AndroidViewModel {

    private JobRepository jobRepository;

    public JobViewModel(Application application){
        super(application);
        jobRepository = JobRepository.getInstance();
    }

    public LiveData<List<Job>> getJobs() {
        return jobRepository.getAllJobs();
    }

    public LiveData<List<Job>> getJobsForLoggedCompany(){
        return jobRepository.getJobsForLoggedCompany();
    }

    public LiveData<List<JobApplication>> getJobAppForUser(){
        return jobRepository.getJobAppForUser();
    }

    public LiveData<List<JobApplication>> getJobAppForCompany(){
        return jobRepository.getJobAppForCompany();
    }

    public void addJob(Job job) {
        jobRepository.addJob(job);
    }

    public JobApplication getJobApplicationById(String id){
        return jobRepository.getJobApplicationById(id);
    }
    public Job getJobById(String id){
       return jobRepository.getJobById(id);
    }

    public LiveData<Job> findJobByID(String id){
        return jobRepository.findJobByID(id);
    }

    public void updateJobApplication(JobApplication jobApplication){ jobRepository.updateJobApplication(jobApplication);}

    public void addJobApplication(JobApplication jobApplication){
        jobRepository.addJobApplication(jobApplication);
    }

    public LiveData<List<JobApplication>> getJobApplications() {
        return jobRepository.getJobApplications();
    }
    public void applyForJob(int userCpr, int companyCvr, String jobId, String firstName,
                            String lastName, String email, String message, String country, String status){
        jobRepository.applyForJob(userCpr, companyCvr, jobId, firstName, lastName, email, message, country, status);
    }
}
