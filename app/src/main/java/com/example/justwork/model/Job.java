package com.example.justwork.model;

public class Job {
    private String id;
    private double salary;
    private String date;
    private String description;
    private String location;
    private String contactInfo;
    private int amountOfNeededWorkers;
    private boolean takenStatus;
    private String title;
    private String companyName;
    private int companyCvr;
    private String jobType;
    private String startTime;
    private String endTime;
    public Job(){

    }
    public Job( double salary, String date, String description, String location, String contactInfo,
               int amountOfNeededWorkers, boolean takenStatus, String title,
               String startTime, String endTime, String jobType, String companyName, int companyCvr) {
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
        this.jobType = jobType;
        this.companyName = companyName;
        this.companyCvr = companyCvr;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
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
        if(this.getAmountOfNeededWorkers()==0){
            takenStatus = true;
            this.takenStatus = takenStatus;
        }
        else if (this.getAmountOfNeededWorkers()>0){
            takenStatus = false;
            this.takenStatus = takenStatus;
        }

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getJobType() {
        return jobType;
    }

    public void setJobType(String jobType) {
        this.jobType = jobType;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public int getCompanyCvr() {
        return companyCvr;
    }

    public void setCompanyCvr(int companyCvr) {
        this.companyCvr = companyCvr;
    }
}
