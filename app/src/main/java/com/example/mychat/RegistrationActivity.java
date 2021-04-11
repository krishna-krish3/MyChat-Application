package com.example.mychat;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;


public class RegistrationActivity extends AppCompatActivity {
    private EditText email, password, Repassword;
    private FirebaseAuth mAuth;
    private TextView logtag;
    private Button btnsign;
    private ProgressDialog progressDialog;
    private String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        InstanceComponents();

        btnsign.setOnClickListener(v -> {
            if (validate()) {
                progressDialog.setMessage("Please Wait until Register..!");
                progressDialog.show();
                //upload data to the database

                String email_reg = email.getText().toString().trim();
                String password_reg = password.getText().toString().trim();

                sendRegistrationDetails(email_reg, password_reg);
            }
        });

        logtag.setOnClickListener(v -> {
            startActivity(new Intent(RegistrationActivity.this, LoginActivity.class));
            Toast.makeText(RegistrationActivity.this, "you are back to Login Page..", Toast.LENGTH_SHORT).show();
        });
    }

    private void InstanceComponents(){
        email = findViewById(R.id.ev_email);
        password = findViewById(R.id.tvpassword_reg);
        Repassword = findViewById(R.id.tvPass_reg);
        btnsign = findViewById(R.id.btnSign);
        logtag = findViewById(R.id.tvLog);
        mAuth = FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);
        mAuth = FirebaseAuth.getInstance();

    }

    private void sendRegistrationDetails(String mail, String password){
        mAuth.createUserWithEmailAndPassword(mail, password).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                progressDialog.dismiss();
                sendEmailVerification();
                // Sign in success, update UI with the signed-in user's information
            } else {
                progressDialog.dismiss();
                // If sign in fails, display a message to the user.
                Toast.makeText(RegistrationActivity.this, "Registration Failed..!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private boolean validate(){
        boolean result = false;

        String Email = email.getText().toString();
        String Password = password.getText().toString();
        String Re_passwd = Repassword.getText().toString();

        if(!Email.matches(emailPattern)){
            Toast.makeText(this, "Please Enter Valid Email", Toast.LENGTH_SHORT).show();
        }
        else if(Email.isEmpty() || Password.isEmpty() || Re_passwd.isEmpty()){
            Toast.makeText(this, "Please fill all the details...", Toast.LENGTH_SHORT).show();
        }
        else if(Password.length() < 6){
            Toast.makeText(this, "Length of password must be greater than six characters...", Toast.LENGTH_SHORT).show();
        }
        else{
            if (Password.equals(Re_passwd)){
                result = true;
            }
            else{
                Toast.makeText(this, "Password confirmation Mismatched..!", Toast.LENGTH_SHORT).show();
            }
        }
        return result;
    }
    private void sendEmailVerification(){
        FirebaseUser firebaseUser = mAuth.getCurrentUser();
        if(firebaseUser != null){
            firebaseUser.sendEmailVerification().addOnCompleteListener(task -> {
                if(task.isSuccessful()){
                    checkEmailVerification();
                    Toast.makeText(RegistrationActivity.this, "Successfully Registered, Verification mail sent..!", Toast.LENGTH_SHORT).show();
                    finish();
                }else{
                    Toast.makeText(RegistrationActivity.this, "Verification mail has not been sent..!", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
    private void checkEmailVerification(){
        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        boolean emailFlag = firebaseUser.isEmailVerified();

        if(emailFlag){
            finish();
            sendUserToProfile();
            Toast.makeText(RegistrationActivity.this, "Login Successful.", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(RegistrationActivity.this, "Verify your email", Toast.LENGTH_SHORT).show();
            mAuth.signOut();
        }
    }

    private void sendUserToProfile(){
        Intent intent = new Intent(RegistrationActivity.this, profileActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }
}