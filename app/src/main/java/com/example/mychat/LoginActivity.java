package com.example.mychat;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class LoginActivity extends AppCompatActivity {

    private EditText maillog, passlog;
    private TextView signtag;
    private CheckBox rember_check;
    private Button btnlog;
    private FirebaseAuth mAuth;
    private ProgressDialog progressDialog;

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor sharedPreferencesEditer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        maillog = findViewById(R.id.emMail);
        passlog = findViewById(R.id.passLogin);
        rember_check = findViewById(R.id.cblogin);
        btnlog = findViewById(R.id.btnLogin);
        signtag = findViewById(R.id.tvSign);


        mAuth = FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);

        sharedPreferences = getApplicationContext().getSharedPreferences("CredentialsDB", MODE_PRIVATE);
        sharedPreferencesEditer = sharedPreferences.edit();

        FirebaseUser user = mAuth.getInstance().getCurrentUser();
        if(user == null){
            Toast.makeText(LoginActivity.this, "Please register with your email...", Toast.LENGTH_SHORT).show();
        }

        if(sharedPreferences != null){
            String savedUserEmail = sharedPreferences.getString("UserEmail", "" );
            String savedPassword = sharedPreferences.getString("Password", "" );

            if(sharedPreferences.getBoolean("RememberMeCheckBox", false)){
                maillog.setText(savedUserEmail);
                passlog.setText(savedPassword);
                rember_check.setChecked(true);
            }
        }

        rember_check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sharedPreferencesEditer.putBoolean("RememberMeCheckBox", rember_check.isChecked());
                sharedPreferencesEditer.apply();
            }
        });

        btnlog.setOnClickListener(v -> {
            if(validate()){
                progressDialog.setMessage("Please Wait until Login..!");
                progressDialog.show();
                String email_ver = maillog.getText().toString().trim();
                String pass_ver = passlog.getText().toString().trim();

                mAuth.signInWithEmailAndPassword(email_ver, pass_ver).addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        progressDialog.dismiss();
                        sendUserToMain();
                        sharedPreferencesEditer.putString("UserEmail", email_ver);
                        sharedPreferencesEditer.putString("Password", pass_ver);
                        sharedPreferencesEditer.apply();
                        // Sign in success, update UI with the signed-in user's information
                    } else {
                        progressDialog.dismiss();
                        // If sign in fails, display a message to the user.
                        Toast.makeText(LoginActivity.this, "Login failed.", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        signtag.setOnClickListener(v -> startActivity(new Intent(LoginActivity.this, RegistrationActivity.class)));
    }

    private boolean validate(){
        boolean resultLog = false;
        String login_mail = maillog.getText().toString();
        String login_pass = passlog.getText().toString();
        if(login_mail.isEmpty() || login_pass.isEmpty()){
            Toast.makeText(LoginActivity.this,"Please enter email/phone and password...", Toast.LENGTH_SHORT).show();
        }
        else{
            resultLog = true;
        }
        return resultLog;
    }

    private void sendUserToMain() {
        Intent MainIntent = new Intent(this, profileActivity.class);
        MainIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(MainIntent);
        finish();
    }
}