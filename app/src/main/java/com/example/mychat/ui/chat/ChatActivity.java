package com.example.mychat.ui.chat;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.bumptech.glide.Glide;
import com.example.mychat.MainActivity;
import com.example.mychat.R;
import com.google.android.material.textfield.TextInputEditText;
import com.jaeger.library.StatusBarUtil;

import de.hdodenhof.circleimageview.CircleImageView;

public class ChatActivity extends AppCompatActivity {

    private ImageView video, voice_call, send_btn, back_btn;
    private CircleImageView profile;
    private TextView profile_name, profile_status;
    private EditText msg_type;
    private RecyclerView recyclerView;
    private String image;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        Intent intent = getIntent();
        String Name = intent.getStringExtra("name");
        image = intent.getStringExtra("image");
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle(Name);
        StatusBarUtil.setTransparent(this);


        instances();


        profile_name.setText(Name);
        Glide.with(this).asBitmap().load(image).into(profile);

    }


    private void instances(){
//        video = findViewById(R.id.video_call);
//        voice_call = findViewById(R.id.voice_call);
        send_btn = findViewById(R.id.send_btn);
        profile = findViewById(R.id.profile_chat);
        profile_name = findViewById(R.id.profile_name);
        profile_status = findViewById(R.id.profile_status_online);
        back_btn = findViewById(R.id.btn_back);
        msg_type = findViewById(R.id.message_box);
        recyclerView = findViewById(R.id.recycle_chatting);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        MenuItem item = menu.findItem(R.id.user_profile);
        Glide.with(this).asBitmap().load(image).into((ImageView) item);
        inflater.inflate(R.menu.chat_toolbar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.user_profile:
                Toast.makeText(ChatActivity.this, "Clicked on user profile", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.Calls_chat:
                Toast.makeText(ChatActivity.this, "Clicked on Voice Calls", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.Video_chat:
                Toast.makeText(ChatActivity.this, "Clicked on Video Calls", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.search:
                Toast.makeText(ChatActivity.this, "You clicked on Search", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.Block:
                Toast.makeText(ChatActivity.this, "You clicked on Block", Toast.LENGTH_SHORT).show();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}