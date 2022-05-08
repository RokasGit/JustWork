package com.example.justwork.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
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

import com.example.justwork.R;
import com.example.justwork.model.UserType;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class Login extends Fragment {
    private View loginView;
    private Button loginButton;
    private NavController navController;
    private TextView createAccountAsUser;
    private TextView createAccountAsCompany;
    private EditText personEmail;
    private EditText personPassword;
    private Button registerCompany;
    private FirebaseAuth mAuth;

    private FirebaseDatabase database;
    private DatabaseReference dbRefUsers;
    private DatabaseReference dbRefCompanies;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        loginView = inflater.inflate(R.layout.fragment_login, container, false);
        setupNavigation();
        initViews();
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            reload();
        }

        database = FirebaseDatabase.getInstance();
        dbRefUsers = database.getReference().child("Users");
        dbRefCompanies = database.getReference().child("Companies");

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
            mAuth.signInWithEmailAndPassword(personEmail.getText().toString(), personPassword.getText().toString())
                    .addOnCompleteListener( getActivity(), new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                Log.d("Logged in", "signInWithEmail:success");
                                FirebaseUser user = mAuth.getCurrentUser();
                                if(dbRefUsers.child("email").equals(user.getEmail())){
                                    System.out.println("This is an emplyee logging in");
                                    navController.navigate(R.id.employeeHomeFragment);
                                }
                                else if (dbRefCompanies.child("email").equals(user.getEmail())){
                                    System.out.println("This is an company logging in");
                                    navController.navigate(R.id.company_home);
                                }
                            }

                                else {
                                // If sign in fails, display a message to the user.
                                Log.w("not logged in", "signInWithEmail:failure", task.getException());
                                Toast.makeText(getActivity(), "Authentication failed.",
                                        Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

    }

    private void reload() {
    }
}