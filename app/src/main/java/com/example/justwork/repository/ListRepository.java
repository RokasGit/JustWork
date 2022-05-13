package com.example.justwork.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.justwork.DAO.DAO;
import com.example.justwork.DAO.DAOImpl;
import com.example.justwork.model.Company;
import com.example.justwork.model.Job;
import com.example.justwork.model.User;

import java.util.List;

public class ListRepository {
    private static ListRepository instance;
    private DAO dao;
    private LiveData<List<Job>> jobs;
    private LiveData<List<Company>> companies;
    private LiveData<List<User>> users;

    private ListRepository() {
        dao = DAOImpl.getInstance();
        jobs = dao.getAllJobs();
        companies = dao.getAllCompanies();
        users = dao.getAllUsers();
    }

    public static ListRepository getInstance() {
        if (instance == null) {
            instance = new ListRepository();
        }
        return instance;
    }

    public LiveData<List<Job>> getJobs() {
        return jobs;
    }

    public LiveData<List<Company>> getCompanies() {
        return companies;
    }

    public LiveData<List<User>> getUsers() {
        return users;
    }

    public User getUserByCpr(int cpr){

        List<User> temp = users.getValue();


        System.out.println("========");
        System.out.println("AAAAAAAAAA+ " + temp.size());

        for (int i = 0; i< users.getValue().size(); i++) {
            if (users.getValue().get(i).getCpr() == cpr){
                return users.getValue().get(i);
            }
        }

        return null;
    }
}
