package com.example.justwork.DAO;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.justwork.model.Company;
import com.example.justwork.model.DrivingLicenceList;
import com.example.justwork.model.User;
import com.example.justwork.model.UserType;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserInfo;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class UserDAOImpl implements UserDAO{
    private DatabaseReference databaseReference;
    private FirebaseAuth mAuth;
    private MutableLiveData<User> employee;
    private MutableLiveData<Company> company;
    private static UserDAO userDAOInstance;
    private UserDAOImpl(){
        databaseReference = FirebaseDatabase.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();
        User ifNullEmployee = new User();
        Company ifNullCompany = new Company();
        employee = new MutableLiveData<>();
//        employee.setValue(ifNullEmployee);
        company = new MutableLiveData<>();
//        company.setValue(ifNullCompany);
    }

    public static UserDAO getInstance() {
        if(userDAOInstance==null){
            userDAOInstance = new UserDAOImpl();
        }
        return userDAOInstance;
    }

    @Override
    public void registerUser(long cpr, String username, String email, String password, int phoneNumber, String address, DrivingLicenceList drivingLicences, String gender, String nationality) {
        employee.setValue(new User(cpr, username, email, password, phoneNumber, address, gender,nationality));
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        databaseReference.child("Users").child(mAuth.getUid()).setValue( employee.getValue());
                        UserProfileChangeRequest request = new UserProfileChangeRequest.Builder()
                                .setDisplayName(employee.getValue().getUserName())
                                .build();
                        mAuth.getCurrentUser().updateProfile(request);
                        System.out.println("REGISTER AS EMPLOYEE");
                    }
                });
    }

    @Override
    public void login(String email, String password) {
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener( new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser user = mAuth.getCurrentUser();

                            databaseReference.child("Users").child(user.getUid()).addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot snapshot) {
                                    if(snapshot.getValue(User.class)!=null){
                                        employee.setValue(snapshot.getValue(User.class));
                                        System.out.println("Logging in as employee");

                                    }else{
                                        databaseReference.child("Companies").child(user.getUid()).addValueEventListener(new ValueEventListener() {
                                            @Override
                                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                                company.setValue(snapshot.getValue(Company.class));
                                                System.out.println("Logging in as company");
                                            }

                                            @Override
                                            public void onCancelled(@NonNull DatabaseError error) {

                                            }
                                        });
                                    }
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError error) {

                                }
                            });



                        }

                        else {
                            // If sign in fails, display a message to the user.
                            Log.w("not logged in", "signInWithEmail:failure", task.getException());
                        }
                    }
                });
    }

    @Override
    public void registerCompany(int cvr, String email, String name, String password, String address) {
         company.setValue(new Company(cvr, email, name, password, address));
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        databaseReference.child("Companies").child(mAuth.getUid()).setValue( company.getValue());
                        UserProfileChangeRequest request = new UserProfileChangeRequest.Builder()
                                .setDisplayName(company.getValue().getName())
                                .build();
                        mAuth.getCurrentUser().updateProfile(request);
                    }
                });
    }

    @Override
    public MutableLiveData<User> getEmployee() {
        String userId = "";
        if(FirebaseAuth.getInstance().getCurrentUser().getUid() != null){
            userId = FirebaseAuth.getInstance().getCurrentUser().getUid();
        }
        else{
            userId = "No user found";
        }
        final User[] tempUser = {new User()};

        databaseReference.child("Users").child(userId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                tempUser[0] = snapshot.getValue(User.class);
                employee.setValue(tempUser[0]);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        return employee;
    }

    @Override
    public MutableLiveData<Company> getCompany() {
        return company;
    }

    @Override
    public void updateEmployeeInfo(String userName, String password) {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String currentUser = FirebaseAuth.getInstance().getCurrentUser().getUid();
       DatabaseReference ref =  databaseReference.child("Users").child(currentUser);
       Map<String, Object> profileUpdates = new HashMap<>();
       profileUpdates.put("userName", userName);
        profileUpdates.put("password", password);
        System.out.println("going to put it in now");
        ref.updateChildren(profileUpdates);

        user.updatePassword(password)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Log.d("Passowrd", "User email address updated.");
                        }
                    }
                });
        UserProfileChangeRequest profileUpdates2 = new UserProfileChangeRequest.Builder()
                .setDisplayName(userName).build();
        user.updateProfile(profileUpdates2)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Log.d("Profile Update", "User profile updated.");
                        }
                    }
                });
    }

    @Override
    public LiveData<User> getEmptyEmployee() {
        return employee;
    }

    @Override
    public LiveData<Company> getEmptyCompany() {
        return company;
    }

}
