package com.example.justwork.model;

import java.util.ArrayList;

public class User {
    private int cpr;
    private String userName;
    private String email;
    private String password;
    private int phoneNumber;
    private String address;
    private String gender;
    private String nationality;
    private String picture;
    private DrivingLicenceList drivingLicences;
    private ArrayList<JobApplication> jobApplications;

    public User(int cpr, String userName, String email, String password, int phoneNumber, String address, String gender, String nationality, String picture) {
        this.cpr = cpr;
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.gender = gender;
        this.nationality = nationality;
        this.picture = picture;
        this.drivingLicences = new DrivingLicenceList();
        jobApplications = new ArrayList<>();
    }

    public int getCpr() {
        return cpr;
    }

    public void setCpr(int cpr) {
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

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public DrivingLicenceList getDrivingLicences() {
        return drivingLicences;
    }

    public void setDrivingLicences(DrivingLicenceList drivingLicences) {
        this.drivingLicences = drivingLicences;
    }

    public ArrayList<JobApplication> getJobApplications() {
        return jobApplications;
    }

    public JobApplication getJobApplicationByID(String id){
        for (JobApplication jobApplication: jobApplications) {
            if(id.equals(jobApplication.getJobApplicationID())){
                return jobApplication;
            }
        }
        return null;
    }

    public void addJobApplication(Job job, String status){
        JobApplication toAdd = new JobApplication(job.getId(), job.getSalary(), job.getDate(), job.getDescription(),
                job.getLocation(), job.getContactInfo(), job.getAmountOfNeededWorkers(), job.isTakenStatus(), job.getTitle(), job.getStartTime(),
                job.getEndTime(), job.getJobType(), job.getCompanyName(), status, userName, email);
        jobApplications.add(toAdd);
    }
}

