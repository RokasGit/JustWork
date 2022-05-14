package com.example.justwork.view;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.justwork.R;
import com.example.justwork.model.User;
import com.example.justwork.viewmodel.AccountViewModel;


public class Employee_Profile extends Fragment {

    private EditText username;
    private EditText email;
    private EditText password;
    private Button save;
    private TextView nameAndLastName;
    private View view;
    private AccountViewModel accountViewModel;
    private NavController navController;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view =  inflater.inflate(R.layout.fragment_employee__profile, container, false);
        initViews();
        setupNavigation();
        accountViewModel = new ViewModelProvider(this).get(AccountViewModel.class);
        accountViewModel.getEmployee().observe(getViewLifecycleOwner(), new Observer<User>() {
            @Override
            public void onChanged(User user) {
                username.setText(user.getUserName());
                email.setText(user.getEmail());
                password.setText(user.getPassword());
                nameAndLastName.setText(user.getUserName());
            }
        });




        return  view;

    }

    private void initViews() {
        username =  view.findViewById(R.id.Profile_editTextPersonName);
        email = view.findViewById(R.id.Profile_editTextEmail);
        password = view.findViewById(R.id.Profile_editTextPassword);
        save = view.findViewById(R.id.saveChangesButton);
        nameAndLastName = view.findViewById(R.id.Profile_Name);

        save.setOnClickListener(view-> {
            accountViewModel.updateEmployeeInfo(username.getText().toString(),  password.getText().toString());
            navController.navigate(R.id.employeeHomeFragment);
        });
    }
    private void setupNavigation(){
        navController = NavHostFragment.findNavController(this);
    }

}