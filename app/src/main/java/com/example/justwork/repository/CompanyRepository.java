package com.example.justwork.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.justwork.model.Job;
import com.example.justwork.model.JobApplication;

import java.util.ArrayList;
import java.util.List;

public class CompanyRepository {

    private MutableLiveData<List<Job>> companyJobs;
    private MutableLiveData<List<JobApplication>> jobApplicants;

    private static CompanyRepository instance;


    private CompanyRepository(){
        companyJobs = new MutableLiveData<>();
        jobApplicants = new MutableLiveData<>();

        List<Job> NewListJob = new ArrayList<>();
        List<JobApplication> NewListJobApplicant = new ArrayList<>();
        companyJobs.setValue(NewListJob);
        jobApplicants.setValue(NewListJobApplicant);
    }

    public static CompanyRepository getInstance(){
        if(instance==null){
            instance=new CompanyRepository();
        }
        return instance;
    }

    public LiveData<List<Job>> getCompanyJobs(){
        return companyJobs;
    }

    public void addCompanyJob(Job job){
        List<Job> currentJobs = companyJobs.getValue();
        currentJobs.add(job);
        companyJobs.setValue(currentJobs);
    }

    public LiveData<List<JobApplication>> getJobApplications(){
        return jobApplicants;
    }

    public void addJobApplicant(JobApplication jobApplication){
        List<JobApplication> currentJobApplication = jobApplicants.getValue();
        currentJobApplication.add(jobApplication);
        jobApplicants.setValue(currentJobApplication);
    }
}
