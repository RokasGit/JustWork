package com.example.justwork.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.justwork.model.Company;
import com.example.justwork.model.Job;
import com.example.justwork.model.User;
import com.example.justwork.repository.ListRepository;

import java.util.List;

public class ListViewModel extends AndroidViewModel {
    private ListRepository listRepository;
    public ListViewModel(@NonNull Application application) {
        super(application);
        listRepository = ListRepository.getInstance();
    }
    public LiveData<List<Job>> getJobs() {
        return listRepository.getJobs();
    }

    public LiveData<List<Company>> getCompanies() {
        return listRepository.getCompanies();
    }

    public LiveData<List<User>> getUsers() {
        return listRepository.getUsers();
    }

    public LiveData<User> getUserByCpr(long cpr) {return listRepository.getUserByCpr(cpr);}
}
