package com.example.justwork.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.justwork.DAO.DAO;
import com.example.justwork.DAO.DAOImpl;
import com.example.justwork.model.Company;
import com.example.justwork.model.DrivingLicenceList;
import com.example.justwork.model.User;

public class AccountRepository {
    private static AccountRepository instance;
    private DAO dao;
    private LiveData<Company> companyLiveData;
    private LiveData<User> employeeLiveData;

    //    private
    private AccountRepository(Application application) {
        dao = DAOImpl.getInstance();
        companyLiveData = dao.getEmptyCompany();
        employeeLiveData = dao.getEmptyEmployee();
    }

    public static AccountRepository getInstance(Application application) {
        if (instance == null) {
            instance = new AccountRepository(application);
        }
        return instance;
    }

    public void registerUser(int cpr, String username, String email, String password, int phoneNumber,
                             String address, DrivingLicenceList drivingLicences, String gender, String nationality) {
        dao.registerUser(cpr, username, email, password, phoneNumber, address, drivingLicences, gender, nationality);
    }

    public void login(String email, String password) {
        dao.login(email, password);
    }

    public void registerCompany(int cvr, String email, String name, String password, String address) {
        dao.registerCompany(cvr, email, name, password, address);
    }

    public LiveData<User> getEmployee() {
        return employeeLiveData;
    }

    public LiveData<Company> getCompany() {
        return companyLiveData;
    }

    public void updateEmployeeInfo(String userName, String password) {
        dao.updateEmployeeInfo(userName, password);
    }
}
