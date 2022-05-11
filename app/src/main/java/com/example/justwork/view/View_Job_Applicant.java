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
import com.example.justwork.viewmodel.CompanyViewModel;
import com.example.justwork.viewmodel.JobViewModel;

import org.w3c.dom.Text;


public class View_Job_Applicant extends Fragment {

    private JobViewModel viewModel;

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

        applicantImage = view.findViewById(R.id.view_jobApplicant_imageView);
        applicantName = view.findViewById(R.id.view_jobApplicant_name);
        applicantGender = view.findViewById(R.id.view_jobApplicant_gender);
        ratingBar = view.findViewById(R.id.ratingBar);
        applicantsAddress = view.findViewById(R.id.view_jobApplicant_address);
        applicantsCrp = view.findViewById(R.id.view_jobApplicant_cpr);
        applicantsDrivingLicence = view.findViewById(R.id.view_jobApplicant_editTextLicence);
        accept = view.findViewById(R.id.view_jobApplicant_button_Accept);
        decline = view.findViewById(R.id.view_jobApplicant_button_Reject);


        User toShow = fetchUser();
        usersApplication = viewModel.getJobApplicationById(getArguments().getString("ApplicationID"));

        //applicantImage.setImageDrawable(toShow.getPicture());
        applicantName.setText(toShow.getUserName());
        applicantGender.setText(toShow.getGender());
        ratingBar.setRating(5); // needs rating
        applicantsAddress.setText(toShow.getAddress());
        applicantsCrp.setText(toShow.getCpr());
        applicantsDrivingLicence.setText(toShow.getDrivingLicences().toString());


        setupNavigation();

        accept.setOnClickListener(v -> {
            try {
                //update application needed
                usersApplication.setStatus("Accepted");
                viewModel.updateJobApplication(usersApplication);
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
                navController.navigate(R.id.jobApplicationsFragment);
            } catch (Exception e){
                e.printStackTrace();
            }
        });


        return view;
    }

    private User fetchUser(){
        //from database fetch a user with corresponding email: getArguments().getString("ApplicantEmail")
        User user = null;
        return user;
    }

    private void setupNavigation(){
        navController = NavHostFragment.findNavController(this);
    }

}