package com.example.justwork.DAO;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.justwork.model.Company;
import com.example.justwork.model.Job;
import com.example.justwork.model.JobApplication;
import com.example.justwork.model.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ListDAOImpl implements ListDAO {
    private DatabaseReference databaseReference;
    private FirebaseAuth mAuth;
    private MutableLiveData<List<Job>> jobs;
    private MutableLiveData<List<JobApplication>> jobApplications;
    private MutableLiveData<List<User>> users;
    private MutableLiveData<List<Company>> companies;
    private static ListDAO listDAOInstance;

    private ListDAOImpl() {
        databaseReference = FirebaseDatabase.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();
        users = new MutableLiveData<>();
        users.setValue(new ArrayList<>());
        companies = new MutableLiveData<>();
        companies.setValue(new ArrayList<>());
        jobs = new MutableLiveData<>();
        jobs.setValue(new ArrayList<>());
        jobApplications = new MutableLiveData<>();
        jobApplications.setValue(new ArrayList<>());
    }

    public static ListDAO getInstance() {
        if (listDAOInstance == null) {
            listDAOInstance = new ListDAOImpl();
        }
        return listDAOInstance;
    }

    @Override
    public LiveData<List<Job>> getAllJobs() {
        databaseReference.child("Jobs").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                List<Job> jobHolder = new ArrayList<>();
                for (DataSnapshot job : snapshot.getChildren()) {
                    jobHolder.add(job.getValue(Job.class));
                }
                jobs.setValue(jobHolder);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        return jobs;
    }

    @Override
    public LiveData<List<JobApplication>> getAllJobApplications() {

        databaseReference.child("JobApplication").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                List<JobApplication> jobHolder = new ArrayList<>();

                for (DataSnapshot job : snapshot.getChildren()) {
                    jobHolder.add(job.getValue(JobApplication.class));
                }

                jobApplications.setValue(jobHolder);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        return jobApplications;
    }

    @Override
    public LiveData<List<Company>> getAllCompanies() {
        databaseReference.child("Companies").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                List<Company> companyHolder = new ArrayList<>();
                for (DataSnapshot company : snapshot.getChildren()) {
                    Company loaded = company.getValue(Company.class);
                    if (loaded != null) {
                        loaded.setPassword("");
                        companyHolder.add(loaded);
                    }
                }
                companies.setValue(companyHolder);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        return companies;
    }

    @Override
    public LiveData<List<User>> getAllUsers() {
        databaseReference.child("Users").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                List<User> userHolder = new ArrayList<>();

                for (DataSnapshot user : snapshot.getChildren()) {
                    User loaded = user.getValue(User.class);
                    if (loaded != null) {
                        loaded.setPassword("");
                        userHolder.add(loaded);
                    }
                }
                users.setValue(userHolder);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        return users;
    }

    @Override
    public MutableLiveData<Company> findCompanyByCVR(int cvr) {
        List<Company> tempCompanies = companies.getValue();
        MutableLiveData<Company> tempCompany = new MutableLiveData<>();
        for(int i=0;i<tempCompanies.size();i++){
            if(tempCompanies.get(i).getCvr()==cvr){
                tempCompany.setValue(tempCompanies.get(i));
                return tempCompany;
            }
        }
        return tempCompany;
    }

    @Override
    public MutableLiveData<Job> findJobByID(String id) {
        List<Job> tempJobs = jobs.getValue();
        MutableLiveData<Job> tempJob = new MutableLiveData<>();

        for(int i=0;i<tempJobs.size();i++){
            if(tempJobs.get(i).getId().equals(id)){
                tempJob.setValue(tempJobs.get(i));
                return tempJob;
            }
        }
        return tempJob;
    }

    @Override
    public MutableLiveData<JobApplication> findJobApplicationByID(String id) {
        List<JobApplication> tempAppJobs = jobApplications.getValue();
        MutableLiveData<JobApplication> tempAppJob = new MutableLiveData<>();

        for(int i=0;i<tempAppJobs.size();i++){
            if(tempAppJobs.get(i).getJobApplicationId().equals(id)){
                tempAppJob.setValue(tempAppJobs.get(i));
                return tempAppJob;
            }
        }
        return tempAppJob;
    }

    @Override
    public MutableLiveData<User> getUserByCpr(long cpr) {
        MutableLiveData<User> user = new MutableLiveData<>();

        for (int i = 0; i< users.getValue().size(); i++) {
            System.out.println(users.getValue().get(i).getCpr());
            if (users.getValue().get(i).getCpr() == cpr){
                user.setValue(users.getValue().get(i));
                return user;
            }
        }
        return user;
    }

    @Override
    public void updateJob(String jobId) {
        Job tempJob = null;
        for (Job job : jobs.getValue()) {
            System.out.println(job.getId());
            if(job.getId().equals(jobId)){
                tempJob = job;
                System.out.println("Matching job found");
                tempJob.setAmountOfNeededWorkers(tempJob.getAmountOfNeededWorkers()-1);
                System.out.println();
                if(tempJob.getAmountOfNeededWorkers()<=0){
                    tempJob.setTakenStatus(true);
                }
                databaseReference.child("Jobs").child(tempJob.getId()).setValue(tempJob);
            }
        }
    }
}
