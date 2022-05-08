package com.example.justwork.model;

import junit.framework.TestCase;

import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;

public class CompanyTest extends TestCase {

    private Company testCompany;

    @BeforeAll
    public void setUp(){
        testCompany = new Company(121234, "company@email.com", "companyname", "password", "Via 39, Horsens");
    }

    @Test
    public void testCompanyhasEmail(){
        assertTrue(testCompany.getEmail().contains("@"));
    }

}