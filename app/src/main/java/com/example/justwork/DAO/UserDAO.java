package com.example.justwork.DAO;

import androidx.lifecycle.MutableLiveData;

import com.example.justwork.model.Company;
import com.example.justwork.model.DrivingLicenceList;
import com.example.justwork.model.User;
import com.example.justwork.model.UserType;

public interface UserDAO {
    void registerUser(int cpr, String username, String email, String password, int phoneNumber,
                      String address, DrivingLicenceList drivingLicences, String gender, String nationality);

    void login(String email, String password);

    void registerCompany(int cvr, String email, String name, String password, String address);
    MutableLiveData<User> getEmployee();
    MutableLiveData<Company> getCompany();
}
