package com.example.justwork.view;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.justwork.R;


public class signUpCompany extends Fragment {

    private NavController navController;
    private View view;
    private EditText companyName;
    private EditText companyPassword;
    private EditText companyEmail;
    private Button next;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view =  inflater.inflate(R.layout.fragment_sign_up_company, container, false);
        setupNavigation();
        initViews();
        return  view;
    }


    private void initViews() {
        companyName = view.findViewById(R.id.set_company_name);
        companyPassword = view.findViewById(R.id.set_company_password);
        companyEmail = view.findViewById(R.id.set_company_email);
        next = view.findViewById(R.id.button_company_next);

        next.setOnClickListener(view -> {
            if(registerCompany()){
                Bundle registerInfo = new Bundle();
                registerInfo.putString("Name", companyName.getText().toString().trim());
                registerInfo.putString("password", companyPassword.getText().toString().trim());
                registerInfo.putString("email", companyEmail.getText().toString().trim());

                navController.navigate(R.id.sign_up_company_second_fragment, registerInfo);

            }
        });


    }

    private boolean registerCompany(){
        String tempEmail = companyEmail.getText().toString().trim();
        String passwordTemp =  companyPassword.getText().toString().trim();
        String nameTemp = companyName.getText().toString().trim();
        if(nameTemp.isEmpty()){
            companyName.setError("Name is required!");
            companyName.requestFocus();
            return false;
        }
        if(passwordTemp.isEmpty()){
            companyPassword.setError("Password is required!");
            companyPassword.requestFocus();
            return false;
        }
        if(tempEmail.isEmpty()){
            companyEmail.setError("Email is required!");
            companyPassword.requestFocus();
            return false;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(tempEmail).matches()){
            companyEmail.setError("Must be valid email");
            companyEmail.requestFocus();
            return false;
        }
        if(passwordTemp.length()<6){
            companyPassword.setError("Must be more than 6 characters");
            companyPassword.requestFocus();
            return false;
        }
        return  true;
    }

    private void setupNavigation(){
        navController = NavHostFragment.findNavController(this);
    }

}