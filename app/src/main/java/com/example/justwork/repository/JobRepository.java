package com.example.justwork.repository;

public class JobRepository{
    private static JobRepository instance;
    private JobRepository(){
        // initialize dao stuff
    }
    public static JobRepository getInstance(){
        if(instance==null){
            instance=new JobRepository();
        }
        return instance;
    }
}
