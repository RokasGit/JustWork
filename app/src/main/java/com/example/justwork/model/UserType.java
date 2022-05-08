package com.example.justwork.model;

public abstract class UserType {
    private String userType;

    public UserType(String userType) {
        this.userType = userType;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }
}
