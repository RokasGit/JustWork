package com.example.justwork.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.justwork.DAO.UserDAO;
import com.example.justwork.DAO.UserDAOImpl;
import com.example.justwork.R;
import com.example.justwork.model.Company;
import com.example.justwork.model.User;
import com.example.justwork.model.UserType;
import com.example.justwork.viewmodel.AccountViewModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.EventListener;


public class Login extends Fragment {
    private View loginView;
    private Button loginButton;
    private NavController navController;
    private TextView createAccountAsUser;
    private TextView createAccountAsCompany;
    private EditText personEmail;
    private EditText personPassword;
    private Button registerCompany;

    private AccountViewModel accountViewModel;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        loginView = inflater.inflate(R.layout.fragment_login, container, false);
        setupNavigation();
        initViews();
        accountViewModel = new ViewModelProvider(this).get(AccountViewModel.class);
        accountViewModel.getCompany().observe(getActivity(),this::navUpCompany);
        accountViewModel.getEmployee().observe(getActivity(),this::navUpEmployee);
        if(FirebaseAuth.getInstance().getCurrentUser() != null){
            reload();
        }

        return loginView;
    }



    private void setupNavigation(){
        navController = NavHostFragment.findNavController(this);
    }
    private void initViews(){
        loginButton = loginView.findViewById(R.id.loginButton);
        loginButton.setOnClickListener(view -> loginUser());
        createAccountAsUser = loginView.findViewById(R.id.register_as_user);
        createAccountAsUser.setOnClickListener(view->navController.navigate(R.id.signupEmployeeFragment));
        createAccountAsCompany = loginView.findViewById(R.id.register_as_company);
        createAccountAsCompany.setOnClickListener(view->navController.navigate(R.id.signUpCompany));

        personEmail = loginView.findViewById(R.id.Login_editTextTextPersonEmail);
        personPassword = loginView.findViewById(R.id.Login_editTextTextPassword);
    }

    private void loginUser() {
        accountViewModel.login(personEmail.getText().toString(),personPassword.getText().toString());
    }
    private void navUpEmployee(User user){
        navController.navigate(R.id.employeeHomeFragment);
    }
    private void navUpCompany(Company company){
        navController.navigate(R.id.company_home);
    }
    private void reload() {
    }
}