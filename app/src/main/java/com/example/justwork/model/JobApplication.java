package com.example.justwork.model;

public class JobApplication {

    private String jobApplicationId;
    private int userCpr;
    private int companyCvr;
    private String jobId;
    private String firstName;
    private String lastName;
    private String email;
    private String message;
    private String country;
    private String status;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public JobApplication(){

    }

    public JobApplication(String jobApplicationId, int userCpr, int companyCvr,String jobId, String firstName,String lastName,
                          String email, String message, String country,String status){
        this.jobApplicationId= jobApplicationId;
        this.userCpr=userCpr;
        this.companyCvr=companyCvr;
        this.jobId=jobId;
        this.firstName=firstName;
        this.lastName=lastName;
        this.email=email;
        this.message=message;
        this.country=country;
        this.status=status;
    }


    public int getUserCpr() {
        return userCpr;
    }

    public void setUserCpr(int userId) {
        this.userCpr = userId;
    }

    public String getJobId() {
        return jobId;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getCompanyCvr() {
        return companyCvr;
    }

    public void setCompanyCvr(int companyCvr) {
        this.companyCvr = companyCvr;
    }

    public String getJobApplicationId() {
        return jobApplicationId;
    }

    public void setJobApplicationId(String jobApplicationId) {
        this.jobApplicationId = jobApplicationId;
    }
}
