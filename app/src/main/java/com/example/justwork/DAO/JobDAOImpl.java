package com.example.justwork.DAO;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.example.justwork.model.Job;
import com.example.justwork.model.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class JobDAOImpl implements JobDAO{

    private DatabaseReference databaseReference;
    private MutableLiveData<List<Job>> Jobs;
    private Job toGet;
    private static JobDAO jobDAOInstance;


    public JobDAOImpl(){
        databaseReference = FirebaseDatabase.getInstance().getReference();
        List<Job> ifNull = new ArrayList<>();
        Jobs = new MutableLiveData<>();
        Jobs.setValue(ifNull);
    }

    public static JobDAO getInstance() {
        if(jobDAOInstance==null){
            jobDAOInstance = new JobDAOImpl();
        }
        return jobDAOInstance;
    }

    @Override
    public void PostJob(Job job) {
        databaseReference.child("Jobs").child(job.getId()+"").setValue(job);
    }

    @Override
    public MutableLiveData<List<Job>> getCompanyJobs(int cvr) {

        databaseReference.child("Jobs").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                List<Job> jobsToGet = new ArrayList<>();

                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                    Job tempJob = dataSnapshot.getValue(Job.class);

                    if (tempJob.getCompanyCvr() == cvr){
                        jobsToGet.add(tempJob);
                    }
                }

                Jobs.setValue(jobsToGet);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        return Jobs;
    }

    @Override
    public Job getCompanyJobById(int id) {


        databaseReference.child("Jobs").child(id+"").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                toGet = snapshot.getValue(Job.class);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        return toGet;
    }
}
