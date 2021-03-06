package com.example.justwork.view;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.justwork.R;
import com.example.justwork.model.Job;
import com.example.justwork.viewmodel.CompanyViewModel;
import com.example.justwork.viewmodel.JobViewModel;

import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.concurrent.atomic.AtomicInteger;


public class PostAJobSecondFragment extends Fragment {

    private JobViewModel jobviewModel;
    private CompanyViewModel companyViewModel;
    private View view;
    private NavController navController;

    EditText jobDate;
    EditText startTime;
    EditText endTime;
    EditText contactInfo;
    EditText nrOfEmployees;
    Button postJob;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view =  inflater.inflate(R.layout.fragment_post_a_job_second, container, false);
        companyViewModel = new ViewModelProvider(this).get(CompanyViewModel.class);
        jobviewModel = new ViewModelProvider(this).get(JobViewModel.class);

        jobDate = view.findViewById(R.id.job_date);
        startTime = view.findViewById(R.id.job_start_time);
        endTime = view.findViewById(R.id.job_end_time);
        contactInfo = view.findViewById(R.id.job_contact_person);
        nrOfEmployees = view.findViewById(R.id.job_required_employees);
        postJob = view.findViewById(R.id.button_post_job);


        setupNavigation();

        postJob.setOnClickListener(v ->{
            try {

                Job toPost = new Job(getArguments().getInt("jobSalary"), jobDate.getText().toString(),
                        getArguments().getString("jobDescription"), getArguments().getString("jobLocation"), contactInfo.getText().toString(),
                        Integer.parseInt(nrOfEmployees.getText().toString()), false, getArguments().getString("jobTitle")
                        ,startTime.getText().toString(), endTime.getText().toString(),
                        getArguments().getString("jobType"), companyViewModel.getCompanyName(), companyViewModel.getCompanyCvr());


                jobviewModel.addJob(toPost);

                navController.navigate(R.id.company_home);
            } catch (Exception e){
                e.printStackTrace();
            }
        });


        return view;
    }

    private void setupNavigation(){
        navController = NavHostFragment.findNavController(this);
    }

}