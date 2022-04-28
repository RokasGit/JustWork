package com.example.justwork.model;

import java.util.Date;

public class Job {
    private int id;
    private double salary;
    private Date date;
    private String description;
    private String location;
    private String contactInfo;
    private int amountOfNeededWorkers;
    private boolean takenStatus;
    private String title;
    private Date startTime;
    private Date endTime;

    public Job(int id, double salary, Date date, String description, String location, String contactInfo, int amountOfNeededWorkers, boolean takenStatus, String title, Date startTime, Date endTime) {
        this.id = id;
        this.salary = salary;
        this.date = date;
        this.description = description;
        this.location = location;
        this.contactInfo = contactInfo;
        this.amountOfNeededWorkers = amountOfNeededWorkers;
        this.takenStatus = takenStatus;
        this.title = title;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo;
    }

    public int getAmountOfNeededWorkers() {
        return amountOfNeededWorkers;
    }

    public void setAmountOfNeededWorkers(int amountOfNeededWorkers) {
        this.amountOfNeededWorkers = amountOfNeededWorkers;
    }

    public boolean isTakenStatus() {
        return takenStatus;
    }

    public void setTakenStatus(boolean takenStatus) {
        this.takenStatus = takenStatus;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }
}
