package com.example.justwork.model;

public class JobApplication {

    private String jobApplicationId;
    private int userCpr;
    private int companyCvr;
    private String jobId;
    private String status;

    public JobApplication(int userId, String jobId, String status, int companyCvr, String JobApplicationId){
        this.jobId = jobId;
        this.userCpr = userId;
        this.status = status;
        this.companyCvr = companyCvr;
        this.jobApplicationId = JobApplicationId;
    }


    public int getUserId() {
        return userCpr;
    }

    public void setUserId(int userId) {
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
