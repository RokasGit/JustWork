package com.example.justwork.viewmodel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.justwork.model.Company;
import com.example.justwork.model.DrivingLicenceList;
import com.example.justwork.model.User;
import com.example.justwork.repository.AccountRepository;

public class AccountViewModel extends AndroidViewModel {
    private AccountRepository accountRepository;

    public AccountViewModel(Application application){
        super(application);
        accountRepository = AccountRepository.getInstance(application);
    }
    public void registerUser(int cpr, String username, String email, String password, int phoneNumber,
                             String address, DrivingLicenceList drivingLicences, String gender, String nationality){
        accountRepository.registerUser(cpr, username, email, password, phoneNumber, address, drivingLicences, gender, nationality);
    }
    public void login(String email, String password){
        accountRepository.login(email,password);
    }

    public void registerCompany(int cvr, String email, String name, String password, String address){
        accountRepository.registerCompany(cvr, email, name, password, address);
    }
    public LiveData<User> getEmployee() {
        return accountRepository.getEmployee();
    }

    public LiveData<Company> getCompany() {
        return accountRepository.getCompany();
    }

    public void updateEmployeeInfo(String userName,  String password) {
        accountRepository.updateEmployeeInfo(userName, password);
    }
}
