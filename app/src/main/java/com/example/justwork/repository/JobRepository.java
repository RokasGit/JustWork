package com.example.justwork.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.justwork.model.Job;

import java.util.ArrayList;
import java.util.List;

public class JobRepository{
    private MutableLiveData<List<Job>> companyJobs;
    private static JobRepository instance;

    private JobRepository(){
        companyJobs = new MutableLiveData<>();
        List<Job> NewList = new ArrayList<>();
        companyJobs.setValue(NewList);
    }

    public static JobRepository getInstance(){
        if(instance==null){
            instance=new JobRepository();
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
}
