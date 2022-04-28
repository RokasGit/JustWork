package com.example.justwork.view;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.justwork.R;
import com.example.justwork.model.Job;
import com.example.justwork.viewmodel.CompanyViewModel;

import java.util.concurrent.atomic.AtomicInteger;


public class PostAJob extends Fragment {

    private CompanyViewModel viewModel;
    private View view;

    private static AtomicInteger ID_GENERATOR = new AtomicInteger(0);

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

        nextPage.setOnClickListener(v->{
            Job tempJob = new Job(ID_GENERATOR.getAndIncrement(),
                    Integer.parseInt(jobSalary.getText().toString()), null,  jobDescription.getText().toString(), jobLocation.getText().toString(), null, 0,
                    false, jobTitle.getText().toString(), null, null, jobType.getText().toString(), null);
            viewModel.setTempJob(tempJob);
            // next page
        });


        return view;
    }

}