package com.example.justwork.view;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.justwork.Adapters.CountryAdapter;
import com.example.justwork.R;
import com.example.justwork.viewmodel.AccountViewModel;
import com.example.justwork.viewmodel.JobViewModel;

import java.util.ArrayList;


public class job_apply_form extends Fragment {

    private String jobID;
    private int companyCVR;
    private View jobApplyFormView;
    private JobViewModel jobViewModel;
    private AccountViewModel accountViewModel;
    private NavController navController;
    private CountryAdapter countryAdapter;
    // Views
    private EditText firstName;
    private EditText lastName;
    private EditText email;
    private Spinner country;
    private EditText message;
    private Button uploadCvButton;
    private Button applyNowButton;
    private String countrySelected;
    // Variables
    private ArrayList<String> countryList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        jobApplyFormView = inflater.inflate(R.layout.fragment_job_apply_form, container, false);
        jobID = getArguments().getString("jobID");
        companyCVR = getArguments().getInt("companyCVR");
        jobViewModel = new ViewModelProvider(this).get(JobViewModel.class);
        accountViewModel = new ViewModelProvider(this).get(AccountViewModel.class);
        initView();
        setupNavigation();
        return jobApplyFormView;
    }

    private void initView() {
        firstName = jobApplyFormView.findViewById(R.id.job_apply_first_name_edit_text);
        lastName = jobApplyFormView.findViewById(R.id.job_apply_last_name_edit_text);
        String[] fullname = accountViewModel.getEmployee().getValue().getUserName().split(" ");
        if(fullname.length>1){
            firstName.setText(fullname[0]);
            lastName.setText(fullname[1]);
        }else{
            firstName.setText(fullname[0]);
        }
        email = jobApplyFormView.findViewById(R.id.job_apply_applicants_email);
        email.setText(accountViewModel.getEmployee().getValue().getEmail());
        country = jobApplyFormView.findViewById(R.id.spinner_countries);
        initList();
        countryAdapter = new CountryAdapter(getContext(), countryList);
        country.setAdapter(countryAdapter);
        countrySelected = "Not Selected";
        country.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                countrySelected = (String) adapterView.getItemAtPosition(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        message = jobApplyFormView.findViewById(R.id.job_apply_application_message);
        uploadCvButton = jobApplyFormView.findViewById(R.id.job_apply_upload_cv_btn);
        uploadCvButton.setOnClickListener(view -> noImplementation());
        applyNowButton = jobApplyFormView.findViewById(R.id.applyNowButton);
        applyNowButton.setOnClickListener(view ->applyToJob());
    }

    private void setupNavigation() {
        navController = NavHostFragment.findNavController(this);
    }
    private void applyToJob(){
        jobViewModel.applyForJob(accountViewModel.getEmployee().getValue().getCpr(), companyCVR, jobID,
                firstName.getText().toString(), lastName.getText().toString(),
                email.getText().toString(), message.getText().toString(), countrySelected, "Applied");
        String successMessage = "Successfully applied for the job in the company: " + companyCVR;
        Toast.makeText(getContext(),successMessage,Toast.LENGTH_SHORT);
        navController.navigate(R.id.employeeHomeFragment);
    }

    private void noImplementation() {
        Toast.makeText(getActivity(), "NOT IMPLEMENTED", Toast.LENGTH_SHORT).show();
    }

    private void initList() {
        countryList = new ArrayList<>();
        countryList.add("Not Selected");
        countryList.add("Denmark");
        countryList.add("Moldova");
        countryList.add("Lithuania");
    }
}