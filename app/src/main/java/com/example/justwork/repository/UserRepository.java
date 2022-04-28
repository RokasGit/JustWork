package com.example.justwork.repository;

public class UserRepository {
    private static UserRepository instance;
    private UserRepository(){
        // initialize dao stuff
    }
    public static UserRepository getInstance(){
        if(instance==null){
            instance=new UserRepository();
        }
        return instance;
    }
}
