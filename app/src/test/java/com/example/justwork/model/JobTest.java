package com.example.justwork.model;

import junit.framework.TestCase;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class JobTest extends TestCase {
//    private Job testJob;
//
//    @BeforeAll
//    public void setUp() throws ParseException {
//        testJob = new Job(1212, 124.5, new Date(), "description", "Brabrand", "Call this number: +4552681029", 23, false, "Unloading Containers", new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse("2022-04-28 07:00:00"), new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse("2022-04-28 15:00:00"), null, null);
//    }
//
//    @Test
//    public void setDateEarlierThanToday(){
//        try {
//            assertFalse(testJob.setDate( new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse("2022-04-26 07:00:00")));
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//    }
//
//
//    @Test
//    public void setDateLaterThanToday() throws ParseException {
//        assertFalse(testJob.setDate(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse("2022-04-26 07:00:00")));
//    }
//
//    @Test
//    public void testIsTakenStatus() {
//        testJob.setAmountOfNeededWorkers(0);
//        testJob.setTakenStatus(false);
//        assertTrue(testJob.isTakenStatus());
//    }
//
//
//    @Test
//    public void testSetStartTimeLaterThanEndTime() {
//        try {
//            assertFalse(testJob.setStartTime(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse("2022-04-28 16:00:00")));
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//    }
//    @Test
//    public void testSetStartTimeEarlierThanEndTime() {
//        try {
//            assertTrue(testJob.setStartTime(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse("2022-04-28 08:00:00")));
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//    }
//    @Test
//    public void testSetEndTimeEarlierThanStartTime() {
//        try {
//            assertFalse(testJob.setEndTime(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse("2022-04-28 06:00:00")));
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//    }
//    @Test
//    public void testSetEndTimeLaterThanStartTime() {
//        try {
//            assertTrue(testJob.setEndTime(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse("2022-04-28 10:00:00")));
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//    }
}