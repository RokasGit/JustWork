package com.example.justwork.DAO;

import androidx.lifecycle.MutableLiveData;

import com.example.justwork.model.Job;
import com.example.justwork.model.User;

import java.util.ArrayList;
import java.util.List;

public interface JobDAO {

    void PostJob(Job job);

    MutableLiveData<List<Job>> getCompanyJobs(int cvr);

    Job getCompanyJobById(int id);


}
