package com.example.justwork.DAO;

import androidx.lifecycle.MutableLiveData;

import com.example.justwork.model.Company;
import com.example.justwork.model.DrivingLicenceList;
import com.example.justwork.model.Job;
import com.example.justwork.model.JobApplication;
import com.example.justwork.model.User;

import java.util.List;

public class DAOImpl implements DAO{
    private UserDAO userDAO;
    private JobDAO jobDAO;
    private static DAO daoInstance;

    private DAOImpl(){
        userDAO = UserDAOImpl.getInstance();
        jobDAO = JobDAOImpl.getInstance();
    }
    public static DAO getInstance(){
        if(daoInstance==null){
            daoInstance = new DAOImpl();
        }
        return daoInstance;
    }
    @Override
    public void registerUser(int cpr, String username, String email, String password, int phoneNumber, String address, DrivingLicenceList drivingLicences, String gender, String nationality) {
        userDAO.registerUser(cpr,username,email,password,phoneNumber,address,drivingLicences,gender,nationality);
    }

    @Override
    public void login(String email, String password) {
        userDAO.login(email,password);
    }

    @Override
    public void registerCompany(int cvr, String email, String name, String password, String address) {
        userDAO.registerCompany(cvr,email,name,password,address);
    }

    @Override
    public MutableLiveData<User> getEmployee() {
        return userDAO.getEmployee();
    }

    @Override
    public MutableLiveData<Company> getCompany() {
        return userDAO.getCompany();
    }

    @Override
    public void PostJob(Job job) {
        jobDAO.PostJob(job);
    }

    @Override
    public void AddJobApplication(JobApplication jobApplication) {
        jobDAO.AddJobApplication(jobApplication);
    }

    @Override
    public MutableLiveData<List<JobApplication>> getJobApplicationsForCompany(int cvr) {
        return jobDAO.getJobApplicationsForCompany(cvr);
    }

    @Override
    public MutableLiveData<List<Job>> getCompanyJobs(int cvr) {
        return jobDAO.getCompanyJobs(cvr);
    }

    @Override
    public Job getCompanyJobById(String id) {
        return jobDAO.getCompanyJobById(id);
    }

    @Override
    public JobApplication getJobApplicationById(String id) {
        return jobDAO.getJobApplicationById(id);
    }


}
