package com.example.justwork.viewmodel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.justwork.model.Job;
import com.example.justwork.model.JobApplication;
import com.example.justwork.repository.CompanyRepository;
import com.example.justwork.repository.JobRepository;

import java.util.ArrayList;
import java.util.List;

public class CompanyViewModel extends AndroidViewModel {

    private CompanyRepository companyRepository;
    private String companyName;

    public CompanyViewModel(Application application){
        super(application);
        companyRepository = CompanyRepository.getInstance();
        companyName = "";
    }

    public LiveData<List<Job>> getJobPostings() {
        return companyRepository.getCompanyJobs();
    }

    public void addJob(Job job) {
        companyRepository.addCompanyJob(job);
    }

    public LiveData<List<JobApplication>> getJobApplicants() {
        return companyRepository.getJobApplications();
    }

    public void addJobApplication(JobApplication jobApplication){
        companyRepository.addJobApplicant(jobApplication);
    }

    public void deleteJobApplication(JobApplication jobApplication){
        companyRepository.deleteJobApplicant(jobApplication);
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
}
