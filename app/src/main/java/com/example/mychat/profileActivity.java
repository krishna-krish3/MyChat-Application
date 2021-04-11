package com.example.mychat;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.HashMap;
import java.util.concurrent.atomic.AtomicMarkableReference;

import de.hdodenhof.circleimageview.CircleImageView;

public class profileActivity extends AppCompatActivity {

    private CircleImageView circleImageView;
    private EditText name, status;
    private Button update;
    private FirebaseAuth mAuth;
    private ProgressDialog progressDialog;
    private Uri imageUri;
    private FirebaseDatabase firebaseDatabase;
    private FirebaseStorage firebaseStorage;
    private String ImageUri;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        InstanceComponents();
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog.setMessage("Please Wait until details Upload..!");
                progressDialog.show();
                updateUserdetails();
            }
        });

        circleImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), 6);
            }
        });

    }

    private void InstanceComponents() {
        circleImageView = findViewById(R.id.profile_image);
        name = findViewById(R.id.profile_name);
        status = findViewById(R.id.profile_status);
        update = findViewById(R.id.profile_update);
        mAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        firebaseStorage = FirebaseStorage.getInstance();
        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);
    }

    private void updateUserdetails() {
        String Name = name.getText().toString();
        String Status = status.getText().toString();

        if (TextUtils.isEmpty(Name)) {
            progressDialog.dismiss();
            Toast.makeText(this, "Please enter your full name..", Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(Status)){
            Toast.makeText(this, "Please tell something about you..", Toast.LENGTH_SHORT).show();
        }
        else {
            /*HashMap<String, String> ProfileMap = new HashMap<>();
                ProfileMap.put("uid", currentUserID);
                ProfileMap.put("Name", Name);
                ProfileMap.put("Status", Status);
                myRef.child("users").child(currentUser).setValues(ProfileMap)*/

            UploadDetails(imageUri, Name, Status);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 6) {
            if (data != null) {
                imageUri = data.getData();
                circleImageView.setImageURI(imageUri);
            }else {
                Toast.makeText(this, "Upload Image failed..!", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void UploadDetails(Uri imageUri, String name, String status) {
        DatabaseReference reference = firebaseDatabase.getReference().child("User").child(mAuth.getUid());
        StorageReference storageReference = firebaseStorage.getReference().child("upload").child(mAuth.getUid());
        if (imageUri != null) {
            storageReference.putFile(imageUri).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                    if (task.isSuccessful()) {
                        storageReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {
                                ImageUri = uri.toString();
                                UserDetails users = new UserDetails(mAuth.getUid(), name, status, ImageUri);
                                reference.setValue(users).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if (task.isSuccessful()) {
                                            progressDialog.dismiss();
                                            Toast.makeText(profileActivity.this, "Successfully uploaded", Toast.LENGTH_SHORT).show();
                                            sendUserToMain();
                                        }
                                        else{
                                            Toast.makeText(profileActivity.this, "Uploading failed, Please Check your network connection..", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });
                            }
                        });
                    }
                }
            });
        }else{
            ImageUri = "https://firebasestorage.googleapis.com/v0/b/mychat-e6ddc.appspot.com/o/Profile_Image.jpg?alt=media&token=bcd1341a-4108-4f7d-8606-5b185786ea93";
            UserDetails users = new UserDetails(mAuth.getUid(), name, status, ImageUri);
            reference.setValue(users).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (task.isSuccessful()) {
                        progressDialog.dismiss();
                        Toast.makeText(profileActivity.this, "Successfully uploaded", Toast.LENGTH_SHORT).show();
                        sendUserToMain();
                    }
                    else{
                        Toast.makeText(profileActivity.this, "Uploading failed, Please Check your network connection..", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    private void sendUserToMain() {
        Intent MainIntent = new Intent(profileActivity.this, MainActivity.class);
        MainIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(MainIntent);
        finish();
    }
}