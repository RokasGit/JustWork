package com.example.justwork.DAO;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.justwork.model.Company;
import com.example.justwork.model.Job;
import com.example.justwork.model.User;

import java.util.List;

public interface ListDAO {
    LiveData<List<Job>> getAllJobs();
    LiveData<List<Company>> getAllCompanies();
    LiveData<List<User>> getAllUsers();
    MutableLiveData<Company> findCompanyByCVR(int cvr);
    MutableLiveData<Job> findJobByID(String id);
}
