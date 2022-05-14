package com.example.justwork.view;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.justwork.Adapters.JobApplicantAdapter;
import com.example.justwork.Adapters.JobApplicationAdapter;
import com.example.justwork.R;
import com.example.justwork.model.JobApplication;
import com.example.justwork.repository.AccountRepository;
import com.example.justwork.viewmodel.AccountViewModel;
import com.example.justwork.viewmodel.CompanyViewModel;
import com.example.justwork.viewmodel.JobViewModel;
import com.example.justwork.viewmodel.ListViewModel;

import java.util.ArrayList;
import java.util.List;


public class JobApplicationsFragment extends Fragment {

    private JobViewModel viewModel;
    private View view;
    private NavController navController;
    private AccountViewModel accountViewModel;


    RecyclerView JobApplicationsRecycler;
    JobApplicantAdapter applicantAdapter;
    JobApplicationAdapter jobApplicationAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        view = inflater.inflate(R.layout.fragment_job_applications, container, false);
        viewModel = new ViewModelProvider(this).get(JobViewModel.class);
        accountViewModel = new ViewModelProvider(this).get(AccountViewModel.class);
        new ViewModelProvider(this).get(ListViewModel.class);


        JobApplicationsRecycler = view.findViewById(R.id.search_resultListView);
        JobApplicationsRecycler.hasFixedSize();
        JobApplicationsRecycler.setLayoutManager(new LinearLayoutManager(getContext()));


        setupNavigation();

        if(accountViewModel.getCompany().getValue() == null){

            List<JobApplication> toGetJobApp = viewModel.getJobAppForUser().getValue();


            jobApplicationAdapter = new JobApplicationAdapter(toGetJobApp);

            for (int i = 0; i<toGetJobApp.size(); i++){
                if(toGetJobApp.get(i).getStatus().equals("Accepted")){
                    NotificationCompat.Builder builder = new NotificationCompat.Builder(getContext(), "JobApplications")
                            .setContentTitle("Application accepted")
                            .setContentText("Your application for " + toGetJobApp.get(i).getCompanyCvr() + " has been accepted")
                            .setSmallIcon(R.drawable.dg_jobs_logo)
                            .setAutoCancel(true);

                    NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(getContext());
                    notificationManagerCompat.notify(1, builder.build());
                }
            }



            viewModel.getJobAppForUser().observe(getViewLifecycleOwner(), new Observer<List<JobApplication>>() {
                @Override
                public void onChanged(List<JobApplication> jobApplications) {
                    jobApplicationAdapter.setJobApplications(jobApplications);
                }
            });


            JobApplicationsRecycler.setAdapter(jobApplicationAdapter);


        } else {

            applicantAdapter = new JobApplicantAdapter(viewModel.getJobAppForCompany().getValue());

            viewModel.getJobAppForCompany().observe(getViewLifecycleOwner(), new Observer<List<JobApplication>>() {
                @Override
                public void onChanged(List<JobApplication> jobApplications) {
                    applicantAdapter.setJobApplications(jobApplications);
                }
            });



            applicantAdapter.setOnClickListener(jobApplication -> {
                try {

                    Bundle toSend = new Bundle();
                    toSend.putString("ApplicationID", jobApplication.getJobApplicationId());

                    navController.navigate(R.id.view_Job_Applicant, toSend);
                } catch (Exception e){

                }
            });

            JobApplicationsRecycler.setAdapter(applicantAdapter);
        }

        return view;
    }


    private void setupNavigation(){
        navController = NavHostFragment.findNavController(this);
    }
}