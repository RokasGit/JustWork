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


    public CompanyViewModel(Application application){
        super(application);
        companyRepository = CompanyRepository.getInstance();
        
    }

    public String getCompanyName(){
        return companyRepository.getCompanyName();
    }

    public int getCompanyCvr(){
        return companyRepository.getCompanyCvr();
    }


}
