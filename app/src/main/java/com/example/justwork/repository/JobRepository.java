package com.example.justwork.repository;

import androidx.lifecycle.LiveData;

import com.example.justwork.DAO.DAO;
import com.example.justwork.DAO.DAOImpl;
import com.example.justwork.model.Job;
import com.example.justwork.model.JobApplication;

import java.util.List;

public class JobRepository{

    private DAO dao;
    private LiveData<List<Job>> companyJobs;
    private LiveData<List<JobApplication>> compnayJobApplications;
    private static JobRepository instance;

    private JobRepository(){
        dao = DAOImpl.getInstance();
        companyJobs = dao.getCompanyJobs(dao.getCompany().getValue().getCvr());

        compnayJobApplications = dao.getJobApplicationsForCompany(dao.getCompany().getValue().getCvr());
    }

    public static JobRepository getInstance(){
        if(instance==null){
            instance=new JobRepository();
        }
        return instance;
    }

    public LiveData<List<Job>> getJobs(){
        return companyJobs;
    }

    public void addJob(Job job){
        dao.PostJob(job);
    }

    public void updateJobApplication(JobApplication jobApplication){ dao.updateJobApplication(jobApplication);}

    public Job getCompanyJobById(String id) {
        return dao.getCompanyJobById(id);
    }

    public JobApplication getJobApplicationById(String id){
        return dao.getJobApplicationById(id);
    }

    public LiveData<List<JobApplication>> getJobApplicationsForCompany() {
        return compnayJobApplications;
    }

    public void addJobApplication(JobApplication jobApplication) {
       dao.AddJobApplication(jobApplication);
    }
}
