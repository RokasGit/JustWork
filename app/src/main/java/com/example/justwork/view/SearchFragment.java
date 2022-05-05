package com.example.justwork.view;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.justwork.R;

public class SearchFragment extends Fragment {
    private View searchView;
    private Button searchFilterButton;
    private NavController navController;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        searchView = inflater.inflate(R.layout.fragment_search, container, false);
        setupNavigation();
        initViews();
        return searchView;
    }
    private void setupNavigation() {
        navController = NavHostFragment.findNavController(this);
    }

    private void initViews() {
        searchFilterButton = searchView.findViewById(R.id.search_filterButton);
        searchFilterButton.setOnClickListener(view -> {
         navController.navigate(R.id.search_filter);
        });
    }
}