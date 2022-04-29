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


public class Login extends Fragment {
    private View loginView;
    private Button loginButton;
    private NavController navController;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        loginView = inflater.inflate(R.layout.fragment_login, container, false);
        setupNavigation();
        initViews();
        return loginView;
    }
    private void setupNavigation(){
        navController = NavHostFragment.findNavController(this);
    }
    private void initViews(){
        loginButton = loginView.findViewById(R.id.loginButton);
        loginButton.setOnClickListener(view -> navController.navigate(R.id.employeeHomeFragment));
    }
}