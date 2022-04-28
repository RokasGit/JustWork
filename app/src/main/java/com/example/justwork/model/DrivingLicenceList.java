package com.example.justwork.model;

import java.util.ArrayList;
import java.util.List;

public class DrivingLicenceList {
    private List<DrivingLicence> drivingLicences;

    public DrivingLicenceList(){
        drivingLicences = new ArrayList<>();
    }
    public void addDrivingLicence(DrivingLicence e){
        drivingLicences.add(e);
    }
    public List<DrivingLicence> getDrivingLicences(){
        return drivingLicences;
    }

}
