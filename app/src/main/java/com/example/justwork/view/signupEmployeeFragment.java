package com.example.justwork.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.justwork.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link signupEmployeeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class signupEmployeeFragment extends Fragment {

    private FirebaseAuth mAuth;

    private NavController navController;
    private View signupEomployeeView;
    private EditText username;
    private EditText email;
    private EditText password;
    private Button signUpGoogle;
    private Button next;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
     signupEomployeeView = inflater.inflate(R.layout.fragment_signup_employee, container, false);
     setupNavigation();
     initViews();
        return signupEomployeeView;
    }

    private void initViews() {
        mAuth = FirebaseAuth.getInstance();
        next = signupEomployeeView.findViewById(R.id.signup_employeeNextButton1);
        username = signupEomployeeView.findViewById(R.id.signup_employeeUsername);
        email = signupEomployeeView.findViewById(R.id.signup_employeeEmail);
        password = signupEomployeeView.findViewById(R.id.signup_employeePassword);
        signUpGoogle = signupEomployeeView.findViewById(R.id.signup_employeeGoogleImage);


        next.setOnClickListener(view -> {
            if(registerUser()){
                Bundle registerInfo = new Bundle();
                registerInfo.putString("username", username.getText().toString().trim());
                registerInfo.putString("password", password.getText().toString().trim());
                registerInfo.putString("email", email.getText().toString().trim());

                navController.navigate(R.id.signupEmployeeAdditionalDetailsFragment, registerInfo);

            }
        });

    }

        private boolean registerUser(){
            String tempEmail = email.getText().toString().trim();
            String passwordTemp =  password.getText().toString().trim();
            String usernameTemp = username.getText().toString().trim();

            if(usernameTemp.isEmpty()){
                username.setError("Username is required!");
                username.requestFocus();
                return false;
            }
            if(passwordTemp.isEmpty()){
                password.setError("Password is required!");
                password.requestFocus();
                return false;
            }
            if(tempEmail.isEmpty()){
                email.setError("Email is required!");
                email.requestFocus();
                return false;
            }
            if(!Patterns.EMAIL_ADDRESS.matcher(tempEmail).matches()){
                email.setError("Must be valid email");
                email.requestFocus();
                return false;
            }
            if(passwordTemp.length()<6){
                password.setError("Must be more than 6 characters");
                password.requestFocus();
                return false;
            }




        return  true;

//            mAuth.createUserWithEmailAndPassword(tempEmail, passwordTemp)
//                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
//                        @Override
//                        public void onComplete(@NonNull Task<AuthResult> task) {
//                            if(task.isSuccessful()){
//
//                            }
//                        }
//                    })


        }


    public static signupEmployeeFragment newInstance(String param1, String param2) {
        signupEmployeeFragment fragment = new signupEmployeeFragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    private void setupNavigation(){
        navController = NavHostFragment.findNavController(this);
    }

}