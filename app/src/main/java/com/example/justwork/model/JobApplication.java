package com.example.justwork.model;

import android.os.Parcelable;

public class JobApplication extends Job  {

    private String status;
    private String userName;
    private String Email;

    public JobApplication(int id, double salary, String date, String description, String location, String contactInfo,
                          int amountOfNeededWorkers, boolean takenStatus, String title, String startTime,
                          String endTime, String jobType, String companyName, String status, String userName, String email) {
        super(id, salary, date, description, location, contactInfo, amountOfNeededWorkers, takenStatus, title, startTime, endTime, jobType, companyName);
        this.status = status;
        this.userName = userName;
        this.Email = email;
    }


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }
}
