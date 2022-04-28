package com.example.justwork.model;

import junit.framework.TestCase;

import org.junit.jupiter.api.BeforeAll;

public class DrivingLicenceListTest extends TestCase {
    private DrivingLicenceList testDrivingLicenceList;

    @BeforeAll
    public void setUp(){
        testDrivingLicenceList = new DrivingLicenceList();
    }

    public void testAddDrivingLicence() {
    testDrivingLicenceList.addDrivingLicence(DrivingLicence.F);
    assertFalse(testDrivingLicenceList.getDrivingLicences().isEmpty());
    }


}