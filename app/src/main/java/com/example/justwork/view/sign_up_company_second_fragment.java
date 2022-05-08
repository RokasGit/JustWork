package com.example.justwork.view;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.justwork.R;
import com.example.justwork.model.Company;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class sign_up_company_second_fragment extends Fragment {
    private FirebaseAuth mAuth;
    private FirebaseDatabase database;
    private DatabaseReference dbRef;

    private EditText companyCVRNo;
    private EditText companyAddress;
    private NavController navController;
    private View view;
    private String companyName;
    private String companyPassword;
    private String companyEmail;
    private Button signup;

    String tempCVRNo;
    String tempAddress;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_sign_up_company_second_fragment, container, false);
        mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        dbRef = database.getReference();

        setupNavigation();
        initViews();

        return view;
    }

    private void initViews() {
        companyCVRNo = view.findViewById(R.id.set_company_cvrNo);
        companyAddress = view.findViewById(R.id.set_company_headQ_address);
        signup = view.findViewById(R.id.button_company_signUp);

        companyName = getArguments().getString("username");
        companyPassword = getArguments().getString("password");
        companyEmail = getArguments().getString("email");

        signup.setOnClickListener(view -> registerCompany());


    }

    private void registerCompany() {

         tempCVRNo =  companyCVRNo.getText().toString().trim();
         tempAddress= companyAddress.getText().toString().trim();

        if(tempCVRNo.isEmpty()){
            companyCVRNo.setError("Home address is required ");
            companyCVRNo.requestFocus();
            return;
        }
        if(tempAddress.isEmpty()){
            companyAddress.setError("Driving licence is required ");
            companyAddress.requestFocus();
            return;
        }

        int finalcvr = Integer.parseInt(tempCVRNo);
        Company company = new Company(finalcvr, companyEmail, companyName, companyPassword, tempAddress);

        FirebaseAuth.getInstance().createUserWithEmailAndPassword(company.getEmail(), company.getPassword())
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        dbRef.child("Companies").child(FirebaseAuth.getInstance().getUid()).setValue(company);
                        UserProfileChangeRequest request = new UserProfileChangeRequest.Builder()
                                .setDisplayName(company.getName())
                                .build();
                        FirebaseAuth.getInstance().getCurrentUser().updateProfile(request);
                        navController.navigate(R.id.company_home);
                    }
                });


    }

    private void setupNavigation(){
        navController = NavHostFragment.findNavController(this);
    }

}