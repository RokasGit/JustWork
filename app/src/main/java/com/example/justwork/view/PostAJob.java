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
import android.widget.Toast;

import com.example.justwork.R;
import com.example.justwork.model.Job;
import com.example.justwork.viewmodel.CompanyViewModel;

import java.util.concurrent.atomic.AtomicInteger;


public class PostAJob extends Fragment {

    private CompanyViewModel viewModel;
    private View view;
    private NavController navController;

    EditText jobTitle;
    EditText jobLocation;
    EditText jobType;
    EditText jobDescription;
    EditText jobSalary;
    Button nextPage;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_post_a_job, container, false);
        viewModel = new ViewModelProvider(this).get(CompanyViewModel.class);

        jobTitle = view.findViewById(R.id.job_title);
        jobLocation = view.findViewById(R.id.job_location);
        jobType = view.findViewById(R.id.job_type);
        jobDescription = view.findViewById(R.id.job_description);
        jobSalary = view.findViewById(R.id.job_salary);
        nextPage = view.findViewById(R.id.button_post_job_next);

        setupNavigation();

        nextPage.setOnClickListener(v->{
            try {
                Bundle toSend = new Bundle();
                toSend.putString("jobTitle", jobTitle.getText().toString());
                toSend.putString("jobLocation", jobLocation.getText().toString());
                toSend.putString("jobType", jobType.getText().toString());
                toSend.putString("jobDescription", jobDescription.getText().toString());
                toSend.putInt("jobSalary", Integer.parseInt(jobSalary.getText().toString()));

                navController.navigate(R.id.postAJobSecondFragment, toSend);
            } catch (Exception e){
                e.printStackTrace();
            }
        });


        return view;
    }

    private void setupNavigation(){
        navController = NavHostFragment.findNavController(this);
    }

}