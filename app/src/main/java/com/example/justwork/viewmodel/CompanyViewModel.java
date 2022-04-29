package com.example.justwork.viewmodel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.justwork.model.Job;
import com.example.justwork.repository.JobRepository;

import java.util.ArrayList;
import java.util.List;

public class CompanyViewModel extends AndroidViewModel {

    private JobRepository jobRepository;
    private String companyName;

    public CompanyViewModel(Application application){
        super(application);
        jobRepository = JobRepository.getInstance();
        companyName = "";
    }

    public LiveData<List<Job>> getJobPostings() {
        return jobRepository.getCompanyJobs();
    }

    public void addJob(Job job) {
        jobRepository.addCompanyJob(job);
    }


    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
}
