package com.example.justwork.repository;

public class CompanyRepository {
    private static CompanyRepository instance;
    private CompanyRepository(){
        // initialize dao stuff
    }
    public static CompanyRepository getInstance(){
        if(instance==null){
            instance=new CompanyRepository();
        }
        return instance;
    }
}
