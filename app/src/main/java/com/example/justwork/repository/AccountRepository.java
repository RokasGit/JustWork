package com.example.justwork.repository;

public class AccountRepository {
    private static AccountRepository instance;
    private AccountRepository(){
        // initialize dao stuff
    }
    public static AccountRepository getInstance(){
        if(instance==null){
            instance=new AccountRepository();
        }
        return instance;
    }
}
