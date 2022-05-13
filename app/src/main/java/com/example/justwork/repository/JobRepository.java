package com.example.justwork.repository;

import androidx.lifecycle.LiveData;

import com.example.justwork.DAO.DAO;
import com.example.justwork.DAO.DAOImpl;
import com.example.justwork.model.Job;
import com.example.justwork.model.JobApplication;

import java.util.ArrayList;
import java.util.List;

public class JobRepository{

    private DAO dao;
    private LiveData<List<Job>> AllJobs;
    private LiveData<List<JobApplication>> AllJobApplications;
    private static JobRepository instance;

    private JobRepository(){
        dao = DAOImpl.getInstance();
        if(dao.getCompany().getValue()!=null || dao.getEmployee().getValue() != null){
            AllJobs = dao.getAllJobs();
            AllJobApplications = dao.getAllJobApplications();
        }


    }

    public static JobRepository getInstance(){
        if(instance==null){
            instance=new JobRepository();
        }
        return instance;
    }

    public LiveData<List<Job>> getAllJobs(){
        return AllJobs;
    }

    public LiveData<List<Job>> getJobsForLoggedCompany(){
        List<Job> toGather = new ArrayList<>();
        MutableLiveData<List<Job>> toGet = new MutableLiveData<>();

        for (int i = 0; i<AllJobs.getValue().size(); i++){
            if (AllJobs.getValue().get(i).getCompanyCvr() == dao.getCompany().getValue().getCvr()){
                toGather.add(AllJobs.getValue().get(i));
            }
        }

        toGet.setValue(toGather);

        return toGet;
    }

    public LiveData<List<JobApplication>> getJobAppForCompany(){

        List<JobApplication> toGather = new ArrayList<>();
        MutableLiveData<List<JobApplication>> toGet = new MutableLiveData<>();

        for (int i = 0; i<AllJobApplications.getValue().size(); i++){
                if (AllJobApplications.getValue().get(i).getCompanyCvr() == dao.getCompany().getValue().getCvr()){
                    toGather.add(AllJobApplications.getValue().get(i));
                }
        }


        toGet.setValue(toGather);

        return toGet;
    }

    public LiveData<List<JobApplication>> getJobAppForUser(){

        List<JobApplication> toGather = new ArrayList<>();
        MutableLiveData<List<JobApplication>> toGet = new MutableLiveData<>();

        if(AllJobApplications.getValue() != null){
            for (int i = 0; i < AllJobApplications.getValue().size(); i++){
                if (AllJobApplications.getValue().get(i).getUserCpr() == dao.getEmployee().getValue().getCpr()){
                    toGather.add(AllJobApplications.getValue().get(i));
                }
            }
        }

        toGet.setValue(toGather);

        return toGet;
    }

    public void addJob(Job job){
        dao.PostJob(job);
    }

    public void updateJobApplication(JobApplication jobApplication){ dao.updateJobApplication(jobApplication);}

    public Job getJobById(String id) {
            return dao.findJobByID(id).getValue();

    }

    public LiveData<Job> findJobByID(String id){
        return dao.findJobByID(id);
    }

    public JobApplication getJobApplicationById(String id){
        return dao.findJobApplicationByID(id).getValue();
    }

    public LiveData<List<JobApplication>> getJobApplications() {
        return AllJobApplications;
    }

    public void addJobApplication(JobApplication jobApplication) {
       dao.AddJobApplication(jobApplication);
    }
    public void applyForJob(int userCpr, int companyCvr, String jobId, String firstName,
                            String lastName, String email, String message, String country, String status){
        dao.applyForJob(userCpr, companyCvr, jobId, firstName, lastName, email, message, country, status);
    }
}
