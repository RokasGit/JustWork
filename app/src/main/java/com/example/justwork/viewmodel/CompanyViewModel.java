package com.example.justwork.viewmodel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.ViewModel;

import com.example.justwork.model.Job;

import java.util.ArrayList;

public class CompanyViewModel extends AndroidViewModel {

    private ArrayList<Job> jobPostings;
    private Job tempJob;

    public CompanyViewModel(Application application){
        super(application);
        jobPostings = new ArrayList<>();
        tempJob = null;
    }

    public ArrayList<Job> getJobPostings() {
        return jobPostings;
    }

    public void addJob(Job job) {
        jobPostings.add(job);
    }

    public void setTempJob(Job tempJob) {
        this.tempJob = tempJob;
    }

    public Job getTempJob() {
        return tempJob;
    }
}
