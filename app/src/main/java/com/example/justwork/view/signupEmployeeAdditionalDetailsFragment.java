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

import com.example.justwork.R;
import com.example.justwork.model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
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

    private void signUpUser() {
        System.out.println("Beginnign og method1");
//        mAuth.createUserWithEmailAndPassword(email, username)
//                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
//                    @Override
//                    public void onComplete(@NonNull Task<AuthResult> task) {
//                        System.out.println("Beginnign og method1");
//
//                        if (task.isSuccessful()){
//                            System.out.println("Beginnign og method1");
//                            User user = new User(Integer.parseInt(tempCpr), username, email, password, phoneNumber, homeAddressTemp, genderTemp, nationalityTemp);
//                            FirebaseDatabase.getInstance().getReference("Users")
//                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
//                                    .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
//                                @Override
//                                public void onComplete(@NonNull Task<Void> task) {
//                                    if(task.isSuccessful()){
//                                        Log.i("Success registration", "User registered successfully!");
//                                        Toast.makeText(getActivity(), "User registered successfully!", Toast.LENGTH_LONG).show();
//                                        //redirect user to his profile
//                                    }
//                                    else{
//                                        Log.i("Fail registration", "User not registered successfully!");
//                                        Toast.makeText(getActivity(), "User not registered successfully!", Toast.LENGTH_LONG).show();
//
//                                    }
//                                }
//                            });
//
//                        }
//                    }
//                });
    }

    private void registerUser(){

        tempCpr = cprNumber.getText().toString().trim();
        homeAddressTemp = homeAddress.getText().toString().trim();
        drivingLicenceTemp = drivingLicence.getText().toString().trim();
        nationalityTemp = nationality.getText().toString().trim();
        genderTemp = gender.getText().toString().trim();

        if(String.valueOf(tempCpr).isEmpty() || String.valueOf(tempCpr).contains("-")){
            cprNumber.setError("Cpr is required and without '-'!");
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
        User user = new User(finalcpr, username, email, password, phoneNumber, homeAddressTemp, genderTemp, nationalityTemp);
//
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(user.getEmail(), user.getPassword())
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        dbRef.child("Users").child(FirebaseAuth.getInstance().getUid()).setValue(user);

                        UserProfileChangeRequest request = new UserProfileChangeRequest.Builder()
                                .setDisplayName(user.getUserName())
                                .build();
                        FirebaseAuth.getInstance().getCurrentUser().updateProfile(request);
                    }
                });
    }

    private void setupNavigation(){
        navController = NavHostFragment.findNavController(this);
    }
}