package com.example.justwork.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.justwork.Adapters.JobAdapter;
import com.example.justwork.R;
import com.example.justwork.viewmodel.CompanyViewModel;


public class company_home extends Fragment {

    private CompanyViewModel viewModel;
    private View view;

    EditText searchBar;
    ImageView menuButton;
    ImageView filterOptions;
    ImageView companyLogo;
    Button postAJob;
    RecyclerView JobRecyclerView;
    JobAdapter jobAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_company_home, container, false);
        viewModel = new ViewModelProvider(this).get(CompanyViewModel.class);

        searchBar = view.findViewById(R.id.company_home_search);
        menuButton = view.findViewById(R.id.Company_home_imageView);
        companyLogo = view.findViewById(R.id.company_home_company_logo_img);
        filterOptions = view.findViewById(R.id.company_home_imageView2);
        postAJob = view.findViewById(R.id.company_home_button_postJob);
        JobRecyclerView = view.findViewById(R.id.company_home_Recycler);

        jobAdapter = new JobAdapter(viewModel.getJobPostings());

        jobAdapter.setOnClickListener(job -> {
            //something
        });

        JobRecyclerView.setAdapter(jobAdapter);

        return view;
    }

    public void filterOptions(View v){

    }

    public void PostAJob(View v){

    }

    public void MenuButton(View v){

    }


}