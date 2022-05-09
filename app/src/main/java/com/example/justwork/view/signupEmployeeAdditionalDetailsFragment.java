package com.example.justwork.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.justwork.DAO.UserDAO;
import com.example.justwork.DAO.UserDAOImpl;
import com.example.justwork.R;
import com.example.justwork.model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Locale;


public class signupEmployeeAdditionalDetailsFragment extends Fragment {

    private FirebaseAuth mAuth;
    private FirebaseDatabase database;
    private DatabaseReference dbRef;

    private NavController navController;
    private View signupEomployeeViewAdditional;
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

    String tempCpr;
    String homeAddressTemp;
    String drivingLicenceTemp;
    String nationalityTemp;
    String genderTemp;





    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        signupEomployeeViewAdditional = inflater.inflate(R.layout.fragment_signup_employee_additional_details, container, false);
        mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        dbRef = database.getReference();

        initViews();
        setupNavigation();
        return signupEomployeeViewAdditional;
    }

    private void initViews(){
        cprNumber = signupEomployeeViewAdditional.findViewById(R.id.signup_employeeCPR);
        homeAddress = signupEomployeeViewAdditional.findViewById(R.id.signup_employeeAddress);
        drivingLicence = signupEomployeeViewAdditional.findViewById(R.id.signup_employeeDrivingLicence);
        nationality = signupEomployeeViewAdditional.findViewById(R.id.signup_employeeNationality);
        gender = signupEomployeeViewAdditional.findViewById(R.id.signup_employeeGender);
        signup =  signupEomployeeViewAdditional.findViewById(R.id.signup_employeeSignUpButton);



        username = getArguments().getString("username");
        password = getArguments().getString("password");
        email = getArguments().getString("email");
        phoneNumber = Integer.parseInt(getArguments().getString("phoneNumber"));

            signup.setOnClickListener(view->registerUser());


    }

    private void registerUser(){

        tempCpr = cprNumber.getText().toString().trim();
        homeAddressTemp = homeAddress.getText().toString().trim();
        drivingLicenceTemp = drivingLicence.getText().toString().trim();
        nationalityTemp = nationality.getText().toString().trim();
        genderTemp = gender.getText().toString().trim();

        if(String.valueOf(tempCpr).isEmpty() || String.valueOf(tempCpr).contains("-") || String.valueOf(tempCpr).length()>10 ){
            cprNumber.setError("Cpr is required and without '-' and less than 10 digits!");
            cprNumber.requestFocus();
            return;
        }
        if(homeAddressTemp.isEmpty()){
            homeAddress.setError("Home address is required ");
            homeAddress.requestFocus();
            return;
        }
        if(drivingLicenceTemp.isEmpty()){
            drivingLicence.setError("Driving licence is required ");
            drivingLicence.requestFocus();
            return;
        }if(nationalityTemp.isEmpty()){
            nationality.setError("Nationality is required ");
            nationality.requestFocus();
            return;
        }if(genderTemp.isEmpty()){
            gender.setError("Gender is required ");
            gender.requestFocus();
            return;
        }

        int finalcpr = Integer.parseInt(tempCpr);
        UserDAO userDAO = new UserDAOImpl();
        userDAO.registerUser(finalcpr,username,email,password,phoneNumber,homeAddressTemp,null,genderTemp,nationalityTemp);
        navController.navigate(R.id.nav_logout);
    }

    private void setupNavigation(){
        navController = NavHostFragment.findNavController(this);
    }
}