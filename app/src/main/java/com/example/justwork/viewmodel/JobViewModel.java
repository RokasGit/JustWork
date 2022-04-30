package com.example.justwork.viewmodel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.justwork.model.Job;
import com.example.justwork.repository.JobRepository;

import java.util.List;

public class JobViewModel extends AndroidViewModel {

    private JobRepository jobRepository;

    public JobViewModel(Application application){
        super(application);
        jobRepository = JobRepository.getInstance();
    }


    public LiveData<List<Job>> getJobs() {
        return jobRepository.getJobs();
    }

    public void addJob(Job job) {
        jobRepository.addJob(job);
    }
}
