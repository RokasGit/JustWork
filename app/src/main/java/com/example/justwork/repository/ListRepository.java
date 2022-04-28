package com.example.justwork.repository;

public class ListRepository {
    private static ListRepository instance;
    private ListRepository(){
        // initialize dao stuff
    }
    public static ListRepository getInstance(){
        if(instance==null){
            instance = new ListRepository();
        }
        return instance;
    }
}
