package com.example.justwork.view;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.justwork.Adapters.JobAdapter;
import com.example.justwork.R;
import com.example.justwork.model.Job;
import com.example.justwork.viewmodel.CompanyViewModel;
import com.example.justwork.viewmodel.ListViewModel;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class EmployeeHomeFragment extends Fragment {
    private View employeeHomeView;
    private Button searchButton;
    private NavController navController;
    private ListViewModel listViewModel;
    private RecyclerView popularJobsRecyclerView;
    private RecyclerView recentJobsRecyclerView;
    private JobAdapter jobAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        employeeHomeView = inflater.inflate(R.layout.fragment_employee_home, container, false);
        listViewModel = new ViewModelProvider(this).get(ListViewModel.class);
        setupNavigation();
        initViews();
        return employeeHomeView;
    }

    private void setupNavigation() {
        navController = NavHostFragment.findNavController(this);
    }

    private void initViews() {
        searchButton = employeeHomeView.findViewById(R.id.employee_homeSearchFilter);
        searchButton.setOnClickListener(view -> navController.navigate(R.id.searchFragment));
        popularJobsRecyclerView = employeeHomeView.findViewById(R.id.employee_homeRecyclerPopular);
        popularJobsRecyclerView.hasFixedSize();
        popularJobsRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        jobAdapter = new JobAdapter(listViewModel.getJobs().getValue());
        jobAdapter.setOnClickListener(job -> {
            Bundle bundle = new Bundle();
            bundle.putString("jobID", job.getId());
            navController.navigate(R.id.job_apply_job_info, bundle);
        });
        popularJobsRecyclerView.setAdapter(jobAdapter);
        recentJobsRecyclerView = employeeHomeView.findViewById(R.id.employee_homeRecyclerRecent);
        recentJobsRecyclerView.hasFixedSize();
        recentJobsRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recentJobsRecyclerView.setAdapter(jobAdapter);
        listViewModel.getJobs().observe(getViewLifecycleOwner(), this::updateJobs);
    }

    private void updateJobs(List<Job> jobs) {
        jobAdapter.setJobs(jobs);
        recentJobsRecyclerView.setAdapter(jobAdapter);
        popularJobsRecyclerView.setAdapter(jobAdapter);

    }
}