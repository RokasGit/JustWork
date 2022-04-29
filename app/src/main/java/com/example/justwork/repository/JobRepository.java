package com.example.justwork.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.justwork.model.Job;

import java.util.ArrayList;
import java.util.List;

public class JobRepository{

    private static JobRepository instance;

    private JobRepository(){
        //
    }

    public static JobRepository getInstance(){
        if(instance==null){
            instance=new JobRepository();
        }
        return instance;
    }


}
