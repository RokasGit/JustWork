package com.example.justwork.view;

import android.os.Bundle;
import android.view.View;
import android.view.Menu;
import android.view.WindowManager;
import android.widget.Button;

import com.example.justwork.R;
import com.google.android.material.navigation.NavigationView;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

import com.example.justwork.databinding.ActivityMainBinding;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private NavController navController;
    private ActivityMainBinding binding;
    private DrawerLayout drawerLayout;

    private NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        initViews();
        setupNavigation();
    }
    private void initViews(){
        drawerLayout = binding.drawerLayout;
        setSupportActionBar(binding.appBarMain.toolbar);
        navigationView = binding.navView;
        navigationView.setItemIconTintList(null);
    }
    private void setupNavigation(){
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_edit_profile, R.id.nav_applications, R.id.nav_notification_settings,R.id.employeeHomeFragment, R.id.company_home)
                .setOpenableLayout(drawerLayout)
                .build();
       navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
        setupNavigationDrawerVisibility();
    }
    private void setupNavigationDrawerVisibility(){
        navController.addOnDestinationChangedListener((controller,destination,arguments)->{
            final int id = destination.getId();
            if(id== R.id.splashScreen || id==R.id.nav_logout){
                Objects.requireNonNull(this.getSupportActionBar()).hide();
            }else{
                Objects.requireNonNull(this.getSupportActionBar()).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
}