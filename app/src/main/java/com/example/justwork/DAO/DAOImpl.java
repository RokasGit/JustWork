package com.example.justwork.DAO;

import androidx.lifecycle.LiveData;
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
    private ListDAO listDAO;
    private static DAO daoInstance;

    private DAOImpl(){
        userDAO = UserDAOImpl.getInstance();
        jobDAO = JobDAOImpl.getInstance();
        listDAO= ListDAOImpl.getInstance();
    }
    public static DAO getInstance(){
        if(daoInstance==null){
            daoInstance = new DAOImpl();
        }
        return daoInstance;
    }
    @Override
    public void registerUser(long cpr, String username, String email, String password, int phoneNumber, String address, DrivingLicenceList drivingLicences, String gender, String nationality) {
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
    public void updateEmployeeInfo(String userName,  String password) {
        System.out.println("In simple fucking dao");
        userDAO.updateEmployeeInfo(userName, password);
        System.out.println("Going to userdao now");
    }

    @Override
    public LiveData<User> getEmptyEmployee() {
        return userDAO.getEmptyEmployee();
    }

    @Override
    public LiveData<Company> getEmptyCompany() {
        return userDAO.getEmptyCompany();
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
    public void updateJobApplication(JobApplication jobApplication) {
        jobDAO.updateJobApplication(jobApplication);
    }

    @Override
    public LiveData<List<JobApplication>> getAllJobApplications(){
        return listDAO.getAllJobApplications();
    }

    public void applyForJob(long userCpr, int companyCvr, String jobId, String firstName,
                            String lastName, String email, String message, String country, String status) {
        jobDAO.applyForJob(userCpr, companyCvr, jobId, firstName, lastName, email, message, country, status);
    }

    @Override
    public void cancelJobApplication(String id) {
        jobDAO.cancelJobApplication(id);
    }


    @Override
    public LiveData<List<Job>> getAllJobs() {
        return listDAO.getAllJobs();
    }

    @Override
    public LiveData<List<Company>> getAllCompanies() {
        return listDAO.getAllCompanies();
    }

    @Override
    public LiveData<List<User>> getAllUsers() {
        return listDAO.getAllUsers();
    }

    @Override
    public MutableLiveData<Company> findCompanyByCVR(int cvr) {
        return listDAO.findCompanyByCVR(cvr);
    }

    @Override
    public MutableLiveData<Job> findJobByID(String id) {
        return listDAO.findJobByID(id);
    }

    @Override
    public MutableLiveData<JobApplication> findJobApplicationByID(String id) {
        return listDAO.findJobApplicationByID(id);
    }

    @Override
    public MutableLiveData<User> getUserByCpr(long cpr) {
        return listDAO.getUserByCpr(cpr);
    }

    @Override
    public boolean updateJobByCancel(String jobID) {
        return listDAO.updateJobByCancel(jobID);
    }
}
