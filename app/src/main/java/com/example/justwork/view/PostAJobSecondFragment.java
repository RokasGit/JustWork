package com.example.justwork.view;

import android.icu.text.TimeZoneFormat;
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

import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;


public class PostAJobSecondFragment extends Fragment {

    private CompanyViewModel viewModel;
    private View view;

    EditText jobDate;
    EditText startTime;
    EditText endTime;
    EditText contactInfo;
    EditText nrOfEmployees;
    Button postJob;

    SimpleDateFormat dateFormat;
    SimpleDateFormat timeFormat;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view =  inflater.inflate(R.layout.fragment_post_a_job_second, container, false);
        viewModel = new ViewModelProvider(this).get(CompanyViewModel.class);

        jobDate = view.findViewById(R.id.job_date);
        startTime = view.findViewById(R.id.job_start_time);
        endTime = view.findViewById(R.id.job_end_time);
        contactInfo = view.findViewById(R.id.job_contact_person);
        nrOfEmployees = view.findViewById(R.id.job_required_employees);
        postJob = view.findViewById(R.id.button_post_job);

        dateFormat = new SimpleDateFormat("dd-MM-yyyy", Locale.GERMANY);
        timeFormat = new SimpleDateFormat("HH : mm", Locale.GERMANY);

        postJob.setOnClickListener(v ->{
            try {
                Job toPost = viewModel.getTempJob();

                Date date = dateFormat.parse(jobDate.getText().toString());
                Date startD = timeFormat.parse(startTime.getText().toString());
                Date endD = timeFormat.parse(endTime.getText().toString());

                toPost.setDate(date);
                toPost.setStartTime(startD);
                toPost.setEndTime(endD);
                toPost.setContactInfo(contactInfo.getText().toString());
                toPost.setAmountOfNeededWorkers(Integer.parseInt(nrOfEmployees.getText().toString()));

                viewModel.addJob(toPost);

                // return to main menu
            } catch (Exception e){
                e.printStackTrace();
            }
        });


        return view;
    }
}