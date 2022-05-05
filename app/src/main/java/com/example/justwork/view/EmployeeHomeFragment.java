package com.example.justwork.view;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.justwork.R;

public class EmployeeHomeFragment extends Fragment {
    private View employeeHomeView;
    private Button searchButton;
    private NavController navController;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        employeeHomeView = inflater.inflate(R.layout.fragment_employee_home, container, false);
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
    }
}