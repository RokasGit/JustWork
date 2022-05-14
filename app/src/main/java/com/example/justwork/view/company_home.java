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
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.justwork.Adapters.JobAdapter;
import com.example.justwork.R;
import com.example.justwork.model.Job;
import com.example.justwork.viewmodel.CompanyViewModel;
import com.example.justwork.viewmodel.JobViewModel;
import com.example.justwork.viewmodel.ListViewModel;

import java.util.ArrayList;
import java.util.List;


public class company_home extends Fragment {

    private JobViewModel viewModel;
    private ListViewModel listViewModel;
    private View view;
    private NavController navController;

    private EditText searchBar;
    private ImageView filterOptions;
    private Button postAJob;
    private RecyclerView JobRecyclerView;
    private JobAdapter jobAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_company_home, container, false);
        viewModel = new ViewModelProvider(this).get(JobViewModel.class);
        listViewModel = new ViewModelProvider(this).get(ListViewModel.class);
        searchBar = view.findViewById(R.id.company_home_search);
        filterOptions = view.findViewById(R.id.company_home_imageView2);
        postAJob = view.findViewById(R.id.company_home_button_postJob);
        JobRecyclerView = view.findViewById(R.id.company_home_Recycler);


        JobRecyclerView.hasFixedSize();
        JobRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        jobAdapter = new JobAdapter(viewModel.getJobsForLoggedCompany().getValue());
        JobRecyclerView.setAdapter(jobAdapter);
        listViewModel.getJobs().observe(getViewLifecycleOwner(),view ->updateJobs());


        jobAdapter.setOnClickListener(job -> {
            //something
        });


        setupNavigation();
        initViews();

        return view;
    }

    private void setupNavigation() {
        navController = NavHostFragment.findNavController(this);
    }

    private void updateJobs() {
        jobAdapter.setJobs(viewModel.getJobsForLoggedCompany().getValue());
        JobRecyclerView.setAdapter(jobAdapter);
    }

    private void initViews() {
        filterOptions.setOnClickListener(view -> navController.navigate(R.id.searchFragment));
        postAJob.setOnClickListener(view -> navController.navigate(R.id.postAJob));
    }


}