package com.example.justwork.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.justwork.DAO.DAO;
import com.example.justwork.DAO.DAOImpl;
import com.example.justwork.model.Company;
import com.example.justwork.model.Job;
import com.example.justwork.model.JobApplication;

import java.util.ArrayList;
import java.util.List;

public class CompanyRepository {

    private static CompanyRepository instance;
    private DAO dao;


    private CompanyRepository(){
        dao = DAOImpl.getInstance();
    }

    public static CompanyRepository getInstance(){
        if(instance==null){
            instance=new CompanyRepository();
        }
        return instance;
    }
    public LiveData<Company> findCompanyByCVR(int cvr){
        return dao.findCompanyByCVR(cvr);
    }
    public String getCompanyName(){
        return dao.getCompany().getValue().getName();
    }

    public int getCompanyCvr(){
        return dao.getCompany().getValue().getCvr();
    }




}
