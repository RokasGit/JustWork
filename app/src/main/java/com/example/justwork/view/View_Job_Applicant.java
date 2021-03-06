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
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.justwork.R;
import com.example.justwork.model.JobApplication;
import com.example.justwork.model.User;
import com.example.justwork.viewmodel.JobViewModel;
import com.example.justwork.viewmodel.ListViewModel;


public class View_Job_Applicant extends Fragment {

    private JobViewModel viewModel;
    private ListViewModel listViewModel;

    private View view;
    private NavController navController;

    JobApplication usersApplication;

    ImageView applicantImage;
    TextView applicantName;
    TextView applicantGender;
    RatingBar ratingBar;
    EditText applicantsAddress;
    EditText applicantsCrp;
    EditText applicantsDrivingLicence;

    Button accept;
    Button decline;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_view__job__applicant, container, false);
        viewModel = new ViewModelProvider(this).get(JobViewModel.class);
        listViewModel = new ViewModelProvider(this).get(ListViewModel.class);

        applicantImage = view.findViewById(R.id.view_jobApplicant_imageView);
        applicantName = view.findViewById(R.id.view_jobApplicant_name);
        applicantGender = view.findViewById(R.id.view_jobApplicant_gender);
        ratingBar = view.findViewById(R.id.ratingBar);
        applicantsAddress = view.findViewById(R.id.view_jobApplicant_editTextAddress);
        applicantsCrp = view.findViewById(R.id.view_jobApplicant_editCpr);
        applicantsDrivingLicence = view.findViewById(R.id.view_jobApplicant_editTextLicence);
        accept = view.findViewById(R.id.view_jobApplicant_button_Accept);
        decline = view.findViewById(R.id.view_jobApplicant_button_Reject);

        usersApplication = viewModel.getJobApplicationById(getArguments().getString("ApplicationID"));
        User toShow = fetchUser();




        applicantName.setText(usersApplication.getFirstName() + " " + usersApplication.getLastName());
        applicantGender.setText(toShow.getGender());
        ratingBar.setRating(5); // needs rating
        applicantsAddress.setText(toShow.getAddress());
        applicantsCrp.setText(toShow.getCpr()+"");
        applicantsDrivingLicence.setText("Not implemented");


        setupNavigation();

        accept.setOnClickListener(v -> {
            try {
                //update application needed
                usersApplication.setStatus("Accepted");
                viewModel.updateJobApplication(usersApplication);
                viewModel.updateJob(usersApplication.getJobId());
                navController.navigate(R.id.jobApplicationsFragment);
            } catch (Exception e){
                e.printStackTrace();
            }
        });

        decline.setOnClickListener(v -> {
            try {
                //update application needed probably also delete.
                usersApplication.setStatus("Rejected");
                viewModel.updateJobApplication(usersApplication);
                viewModel.deleteJobApplication(usersApplication);
                navController.navigate(R.id.jobApplicationsFragment);
            } catch (Exception e){
                e.printStackTrace();
            }
        });


        return view;
    }

    private User fetchUser(){
        User user = listViewModel.getUserByCpr(usersApplication.getUserCpr()).getValue();
        return user;
    }

    private void setupNavigation(){
        navController = NavHostFragment.findNavController(this);
    }

}