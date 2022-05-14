package com.example.justwork.view;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Menu;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.justwork.R;
import com.example.justwork.model.Company;
import com.example.justwork.model.User;
import com.example.justwork.viewmodel.AccountViewModel;
import com.google.android.material.navigation.NavigationView;

import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

import com.example.justwork.databinding.ActivityMainBinding;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private NavController navController;
    private ActivityMainBinding binding;
    private DrawerLayout drawerLayout;
    private TextView navName;
    private TextView navEmail;
    private ImageView logout;
    private AccountViewModel accountViewModel;


    private NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        accountViewModel = new ViewModelProvider(this).get(AccountViewModel.class);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        initViews();
        setupNavigation();
        createNotificationChannel();

    }
    private void initViews(){
        drawerLayout = binding.drawerLayout;
        setSupportActionBar(binding.appBarMain.toolbar);
        navigationView = binding.navView;
        navigationView.setItemIconTintList(null);
        try{
            accountViewModel.getCompany().observe(this, this::setCompanyName);
        }catch (Exception e){
            System.out.println(e);
        }
        try{
            accountViewModel.getEmployee().observe(this, this::setEmployeeName);
        }
        catch (Exception e){
            System.out.println(e);
        }
//        View headView = navigationView.getHeaderView(0);
//        logout = navigationView2.findViewById(R.id.nav_logout);
        setupNavigation();
//        System.out.println(navigationView2.findViewById(R.id.nav_logout) + "AAAAAAAAAAAAAAAAAAAA");
//        logout.setOnClickListener(view->{
//            FirebaseAuth.getInstance().signOut();
//            navController.navigate(R.id.action_splashScreen_to_login);
//            System.out.println("Loggin out as user");
//        });


    }


    private void setEmployeeName(User user){
        View headView = navigationView.getHeaderView(0);
        ((TextView) headView.findViewById(R.id.nav_header_fullName)).setText(user.getUserName());
        ((TextView) headView.findViewById(R.id.nav_header_email)).setText(user.getEmail());

    }
    private void setCompanyName(Company company){
        View headView = navigationView.getHeaderView(0);
        ((TextView) headView.findViewById(R.id.nav_header_fullName)).setText(company.getName());
        ((TextView) headView.findViewById(R.id.nav_header_email)).setText(company.getEmail());
    }

    private void setupNavigation(){
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.


        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_edit_profile, R.id.nav_applications, R.id.nav_notification_settings,R.id.employeeHomeFragment, R.id.company_home, R.id.nav_logout)
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

    private void createNotificationChannel(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel("JobApplications", "JobApp", NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
}