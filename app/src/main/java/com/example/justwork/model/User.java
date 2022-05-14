package com.example.justwork.model;

import java.util.ArrayList;

public class User extends UserType{
    private long cpr;
    private String userName;
    private String email;
    private String password;
    private int phoneNumber;
    private String address;
    private String gender;
    private String nationality;
//    private String picture;
    private DrivingLicenceList drivingLicences;


    public User(){
        super("Employee");
    }

    public User(long cpr, String userName, String email, String password, int phoneNumber, String address, String gender, String nationality) {
        super("Employee");
        this.cpr = cpr;
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.gender = gender;
        this.nationality = nationality;
//        this.picture = picture;
        this.drivingLicences = new DrivingLicenceList();
    }

    public long getCpr() {
        return cpr;
    }

    public void setCpr(long cpr) {
        this.cpr = cpr;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }



    public DrivingLicenceList getDrivingLicences() {
        return drivingLicences;
    }

    public void setDrivingLicences(DrivingLicenceList drivingLicences) {
        this.drivingLicences = drivingLicences;
    }

}

