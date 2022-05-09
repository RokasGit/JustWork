package com.example.justwork.DAO;

import android.util.Log;

import androidx.annotation.NonNull;
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
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class UserDAOImpl implements UserDAO{
    private DatabaseReference databaseReference;
    private FirebaseAuth mAuth;
    private MutableLiveData<User> employee;
    private MutableLiveData<Company> company;
    public UserDAOImpl(){
        databaseReference = FirebaseDatabase.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();
        employee = new MutableLiveData<>();
        company = new MutableLiveData<>();
    }
    @Override
    public void registerUser(int cpr, String username, String email, String password, int phoneNumber, String address, DrivingLicenceList drivingLicences, String gender, String nationality) {
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
                        mAuth.signOut();
                        employee.setValue(null);
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
                                    }else{
                                        databaseReference.child("Companies").child(user.getUid()).addValueEventListener(new ValueEventListener() {
                                            @Override
                                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                                company.setValue(snapshot.getValue(Company.class));
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
                        company.setValue(null);
                        mAuth.signOut();
                    }
                });
    }

    @Override
    public MutableLiveData<User> getEmployee() {
        return employee;
    }

    @Override
    public MutableLiveData<Company> getCompany() {
        return company;
    }

}
