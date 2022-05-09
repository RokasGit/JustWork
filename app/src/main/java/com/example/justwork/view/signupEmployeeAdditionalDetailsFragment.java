package com.example.justwork.view;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.justwork.DAO.UserDAO;
import com.example.justwork.DAO.UserDAOImpl;
import com.example.justwork.R;
import com.example.justwork.viewmodel.AccountViewModel;


public class signupEmployeeAdditionalDetailsFragment extends Fragment {
    private NavController navController;
    private View signupEmployeeViewAdditional;
    private EditText cprNumber;
    private EditText homeAddress;
    private EditText drivingLicence;
    private EditText nationality;
    private EditText gender;
    private Button signup;
    private String username;
    private String password;
    private String email;
    private int phoneNumber;

    private AccountViewModel accountViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        signupEmployeeViewAdditional = inflater.inflate(R.layout.fragment_signup_employee_additional_details, container, false);

        accountViewModel = new ViewModelProvider(this).get(AccountViewModel.class);
        initViews();
        setupNavigation();
        return signupEmployeeViewAdditional;
    }

    private void initViews() {
        cprNumber = signupEmployeeViewAdditional.findViewById(R.id.signup_employeeCPR);
        homeAddress = signupEmployeeViewAdditional.findViewById(R.id.signup_employeeAddress);
        drivingLicence = signupEmployeeViewAdditional.findViewById(R.id.signup_employeeDrivingLicence);
        nationality = signupEmployeeViewAdditional.findViewById(R.id.signup_employeeNationality);
        gender = signupEmployeeViewAdditional.findViewById(R.id.signup_employeeGender);
        signup = signupEmployeeViewAdditional.findViewById(R.id.signup_employeeSignUpButton);


        username = getArguments().getString("username");
        password = getArguments().getString("password");
        email = getArguments().getString("email");
        phoneNumber = Integer.parseInt(getArguments().getString("phoneNumber"));

        signup.setOnClickListener(view -> registerUser());


    }

    private void registerUser() {

        String tempCpr = cprNumber.getText().toString().trim();
        String homeAddressTemp = homeAddress.getText().toString().trim();
        String drivingLicenceTemp = drivingLicence.getText().toString().trim();
        String nationalityTemp = nationality.getText().toString().trim();
        String genderTemp = gender.getText().toString().trim();

        if (tempCpr.isEmpty() || tempCpr.contains("-") || tempCpr.length() > 10) {
            cprNumber.setError("Cpr is required and without '-' and less than 10 digits!");
            cprNumber.requestFocus();
            return;
        }
        if (homeAddressTemp.isEmpty()) {
            homeAddress.setError("Home address is required ");
            homeAddress.requestFocus();
            return;
        }
        if (drivingLicenceTemp.isEmpty()) {
            drivingLicence.setError("Driving licence is required ");
            drivingLicence.requestFocus();
            return;
        }
        if (nationalityTemp.isEmpty()) {
            nationality.setError("Nationality is required ");
            nationality.requestFocus();
            return;
        }
        if (genderTemp.isEmpty()) {
            gender.setError("Gender is required ");
            gender.requestFocus();
            return;
        }

        int finalcpr = Integer.parseInt(tempCpr);
        accountViewModel.registerUser(finalcpr, username, email, password, phoneNumber, homeAddressTemp, null, genderTemp, nationalityTemp);
        navController.navigate(R.id.nav_logout);
    }

    private void setupNavigation() {
        navController = NavHostFragment.findNavController(this);
    }
}