package com.example.justwork.view;

import android.os.Bundle;

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

import com.example.justwork.Adapters.JobAdapter;
import com.example.justwork.Adapters.JobApplicationAdapter;
import com.example.justwork.R;
import com.example.justwork.model.JobApplication;
import com.example.justwork.viewmodel.JobViewModel;

import java.util.List;

public class UserApplicationsFragment extends Fragment {
    private JobViewModel jobViewModel;
    private View view;
    private NavController navController;
    private String username = "Dummy username";

    private RecyclerView jobApplicationsRecyclerView;
    private JobApplicationAdapter jobApplicationAdapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_user_applications, container, false);
        jobViewModel = new ViewModelProvider(this).get(JobViewModel.class);
        jobApplicationsRecyclerView = view.findViewById(R.id.job_applications_recycler_view);
        jobApplicationsRecyclerView.hasFixedSize();
        jobApplicationsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

//        username = view.findViewById(R.id.)

//        jobApplicationAdapter = new JobApplicationAdapter(jobViewModel.getJobApplications(username).getValue());
//        jobViewModel.getJobApplications(username).observe(getViewLifecycleOwner(), new Observer<List<JobApplication>>() {
//            @Override
//            public void onChanged(List<JobApplication> jobApplications) {
//                jobApplicationAdapter.setJobApplications(jobApplications);
//            }
//        });


        jobApplicationsRecyclerView.setAdapter(jobApplicationAdapter);
        setupNavigation();
        initViews();

        return view;
    }

    private void setupNavigation(){
        navController = NavHostFragment.findNavController(this);
    }

    private void initViews(){
//        filterOptions.setOnClickListener(view -> navController.navigate(R.id.searchFragment));
//        postAJob.setOnClickListener(view -> navController.navigate(R.id.postAJob));
    }

    public UserApplicationsFragment() {
        // Required empty public constructor
    }
    public static UserApplicationsFragment newInstance(String param1, String param2) {
        UserApplicationsFragment fragment = new UserApplicationsFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }


}