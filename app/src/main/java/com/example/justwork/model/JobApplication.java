package com.example.justwork.model;

import java.util.Date;

public class JobApplication extends Job{

    private String status;

    public JobApplication(int id, double salary, Date date, String description, String location, String contactInfo,
                          int amountOfNeededWorkers, boolean takenStatus, String title, Date startTime,
                          Date endTime, String jobType, String companyName, String status) {
        super(id, salary, date, description, location, contactInfo, amountOfNeededWorkers, takenStatus, title, startTime, endTime, jobType, companyName);
        this.status = status;
    }


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
