package com.example.justwork.model;

public class Company {
    private int cvr;
    private String email;
    private String name;
    private String password;
    private String address;
    private String logo;

    public Company(int cvr, String email, String name, String password, String address, String logo) {
        this.cvr = cvr;
        this.email = email;
        this.name = name;
        this.password = password;
        this.address = address;
        this.logo = logo;
    }

    public int getCvr() {
        return cvr;
    }

    public void setCvr(int cvr) {
        this.cvr = cvr;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }
}
