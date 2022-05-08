package com.example.justwork.model;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

public class UserTest extends TestCase {

    private User testUser;

    @Before
    public void setUp(){
        testUser = new User(231101, "asoldan", "mail@.com", "password", 52681020, "kamtjatka 10", "male", "Romania");
    }

    @Test
    public void testGetDrivingLicencesEmptyList() {
        assertTrue(testUser.getDrivingLicences().getDrivingLicences().isEmpty());
    }
    @Test
    public void testGetDrivingLicencesNotEmpty(){
        testUser.getDrivingLicences().addDrivingLicence(DrivingLicence.E);
        testUser.getDrivingLicences().addDrivingLicence(DrivingLicence.A);
        assertTrue(testUser.getDrivingLicences().getDrivingLicences().size() == 2);
    }

  @Test
    public void testUserHasEmailAddress(){
        assertTrue(testUser.getEmail().contains("@"));
  }
}