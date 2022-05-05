package com.example.justwork.view;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.justwork.R;


public class signupEmployeeAdditionalDetailsFragment extends Fragment {


    private NavController navController;





    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

//        Bundle extras =
        return inflater.inflate(R.layout.fragment_signup_employee_additional_details, container, false);
    }

    private void setupNavigation(){
        navController = NavHostFragment.findNavController(this);
    }
}