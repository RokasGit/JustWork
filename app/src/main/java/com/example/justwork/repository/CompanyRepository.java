package com.example.justwork.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.justwork.model.Job;
import com.example.justwork.model.JobApplication;

import java.util.ArrayList;
import java.util.List;

public class CompanyRepository {

    private MutableLiveData<List<JobApplication>> jobApplicants;

    private static CompanyRepository instance;


    private CompanyRepository(){
        jobApplicants = new MutableLiveData<>();
        List<JobApplication> NewListJobApplicant = new ArrayList<>();
        jobApplicants.setValue(NewListJobApplicant);
    }

    public static CompanyRepository getInstance(){
        if(instance==null){
            instance=new CompanyRepository();
        }
        return instance;
    }

    public LiveData<List<JobApplication>> getJobApplications(){
        return jobApplicants;
    }

    public void addJobApplicant(JobApplication jobApplication){
        List<JobApplication> currentJobApplication = jobApplicants.getValue();
        currentJobApplication.add(jobApplication);
        jobApplicants.setValue(currentJobApplication);
    }

    public void deleteJobApplicant(JobApplication jobApplication){
        List<JobApplication> currentJobApplication = jobApplicants.getValue();
        currentJobApplication.remove(jobApplication);
        jobApplicants.setValue(currentJobApplication);
    }
}
