package com.example.justwork.model;

public class JobApplication {

    private int userId;
    private int jobId;
    private String status;

    public JobApplication(int userId, int jobId, String status){
        this.jobId = jobId;
        this.userId = userId;
        this.status = status;
    }


    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getJobId() {
        return jobId;
    }

    public void setJobId(int jobId) {
        this.jobId = jobId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
