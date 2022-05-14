package com.example.justwork.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.justwork.Adapters.JobApplicantAdapter;
import com.example.justwork.Adapters.JobApplicationAdapter;
import com.example.justwork.R;
import com.example.justwork.model.JobApplication;
import com.example.justwork.repository.AccountRepository;
import com.example.justwork.viewmodel.AccountViewModel;
import com.example.justwork.viewmodel.CompanyViewModel;
import com.example.justwork.viewmodel.JobViewModel;
import com.example.justwork.viewmodel.ListViewModel;

import java.util.ArrayList;
import java.util.List;


public class JobApplicationsFragment extends Fragment {

    private JobViewModel viewModel;
    private View view;
    private NavController navController;
    private AccountViewModel accountViewModel;


    RecyclerView JobApplicationsRecycler;
    JobApplicantAdapter applicantAdapter;
    JobApplicationAdapter jobApplicationAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_job_applications, container, false);
        viewModel = new ViewModelProvider(this).get(JobViewModel.class);
        accountViewModel = new ViewModelProvider(this).get(AccountViewModel.class);
        new ViewModelProvider(this).get(ListViewModel.class);


        JobApplicationsRecycler = view.findViewById(R.id.search_resultListView);
        JobApplicationsRecycler.hasFixedSize();
        JobApplicationsRecycler.setLayoutManager(new LinearLayoutManager(getContext()));


        setupNavigation();

        if (accountViewModel.getCompany().getValue() == null) {
            jobApplicationAdapter = new JobApplicationAdapter(viewModel.getJobAppForUser().getValue());
            viewModel.getJobAppForUser().observe(getViewLifecycleOwner(), new Observer<List<JobApplication>>() {
                @Override
                public void onChanged(List<JobApplication> jobApplications) {
                    jobApplicationAdapter.setJobApplications(jobApplications);
                    JobApplicationsRecycler.setAdapter(jobApplicationAdapter);
                }
            });
        } else {

            applicantAdapter = new JobApplicantAdapter(viewModel.getJobAppForCompany().getValue());

            viewModel.getJobAppForCompany().observe(getViewLifecycleOwner(), new Observer<List<JobApplication>>() {
                @Override
                public void onChanged(List<JobApplication> jobApplications) {
                    applicantAdapter.setJobApplications(viewModel.getJobAppForCompany().getValue());
                    JobApplicationsRecycler.setAdapter(applicantAdapter);
                }
            });


            applicantAdapter.setOnClickListener(jobApplication -> {
                try {

                    Bundle toSend = new Bundle();
                    toSend.putString("ApplicationID", jobApplication.getJobApplicationId());

                    navController.navigate(R.id.view_Job_Applicant, toSend);
                } catch (Exception e) {

                }
            });

            JobApplicationsRecycler.setAdapter(applicantAdapter);
        }

        return view;
    }

    private void setupNavigation() {
        navController = NavHostFragment.findNavController(this);
    }
}