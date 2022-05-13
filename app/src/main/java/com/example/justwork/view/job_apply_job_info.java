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
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.justwork.R;
import com.example.justwork.model.Company;
import com.example.justwork.model.Job;
import com.example.justwork.viewmodel.CompanyViewModel;
import com.example.justwork.viewmodel.JobViewModel;


public class job_apply_job_info extends Fragment {
    private View jobApplyInfoView;
    private String jobID;

    private JobViewModel jobViewModel;
    private CompanyViewModel companyViewModel;

    private Job jobToDisplay;
    private Company companyToDisplay;
    private NavController navController;
    private TextView jobTitle;
    private TextView companyName;
    private TextView companyAddress;
    private TextView jobType;
    private TextView salary;
    private Button descriptionButton;
    private Button companyButton;
    private Button reviewButton;
    private TextView jobDescription;
    private Button applyNowButton;
    private ImageButton contactButton;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        jobApplyInfoView = inflater.inflate(R.layout.fragment_job_apply_job_info, container, false);
        jobID = getArguments().getString("jobID");
        jobViewModel = new ViewModelProvider(this).get(JobViewModel.class);
        companyViewModel = new ViewModelProvider(this).get(CompanyViewModel.class);
        jobToDisplay = jobViewModel.findJobByID(jobID).getValue();
        companyToDisplay = companyViewModel.findCompanyByCVR(jobToDisplay.getCompanyCvr()).getValue();
        setupNavigation();
        initViews();
        return jobApplyInfoView;
    }

    private void initViews() {
        jobTitle = jobApplyInfoView.findViewById(R.id.view_job_job_title);
        jobTitle.setText(jobToDisplay.getTitle());
        companyName = jobApplyInfoView.findViewById(R.id.job_details_company_name);
        companyName.setText(companyToDisplay.getName());
        companyAddress = jobApplyInfoView.findViewById(R.id.job_details_company_address);
        companyAddress.setText(companyToDisplay.getAddress());
        jobType = jobApplyInfoView.findViewById(R.id.job_details_job_type);
        jobType.setText(jobToDisplay.getJobType());
        salary = jobApplyInfoView.findViewById(R.id.job_details_job_salary);
        String salaryString = jobToDisplay.getSalary() + " dkk/h";
        salary.setText(salaryString);
        descriptionButton = jobApplyInfoView.findViewById(R.id.job_details_job_description_btn);
        companyButton = jobApplyInfoView.findViewById(R.id.job_details_company_btn);
        reviewButton = jobApplyInfoView.findViewById(R.id.job_details_company_reviews);
        jobDescription = jobApplyInfoView.findViewById(R.id.job_apply_info_description);
        jobDescription.setText(jobToDisplay.getDescription());
        applyNowButton = jobApplyInfoView.findViewById(R.id.apply_now_next_page_btn);
        contactButton = jobApplyInfoView.findViewById(R.id.ask_questions_btn);
        contactButton.setOnClickListener(view -> noImplementation());
        companyButton.setOnClickListener(view -> noImplementation());
        reviewButton.setOnClickListener(view->noImplementation());
        applyNowButton.setOnClickListener(view ->applyNow());
    }
    private void applyNow(){
        Bundle bundle = new Bundle();
        bundle.putString("jobID",jobID);
        bundle.putInt("companyCVR",companyToDisplay.getCvr());
        navController.navigate(R.id.job_apply_form,bundle);
    }
    private void noImplementation(){
        Toast.makeText(getActivity(),"NOT IMPLEMENTED",Toast.LENGTH_SHORT).show();
    }
    private void setupNavigation() {
        navController = NavHostFragment.findNavController(this);
    }
}