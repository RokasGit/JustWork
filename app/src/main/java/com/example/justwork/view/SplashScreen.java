package com.example.justwork.view;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.justwork.R;


public class SplashScreen extends Fragment {
    private NavController navController;
    private Button splashButton;
    private View splashView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        splashView = inflater.inflate(R.layout.fragment_splash_screen, container, false);
        setupNavigation();
        initViews();
        return splashView;
    }
    private void setupNavigation(){
        navController = NavHostFragment.findNavController(this);
    }
    private void initViews(){
        splashButton = splashView.findViewById(R.id.splash_button);
        splashButton.setOnClickListener(view -> navController.navigate(R.id.nav_logout));
    }
}