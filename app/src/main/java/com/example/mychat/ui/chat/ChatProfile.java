package com.example.mychat.ui.chat;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.example.mychat.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class ChatProfile extends Fragment {

    private ImageView back_btn;
    private  ImageView chat_profile;
    private TextView textView, chat_name;
    private Context context;

    private String Name;
    private String Profile;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ChatProfile() {
        // Required empty public constructor
    }

    public static ChatProfile newInstance(String param1, String param2) {
        ChatProfile fragment = new ChatProfile();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            Name = getArguments().getString(ARG_PARAM1);
            Profile = getArguments().getString(ARG_PARAM2);

            context = getContext();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.activity_chat, container, false);
        ActionBar actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        @SuppressLint("UseCompatLoadingForDrawables") Drawable drawable= getResources().getDrawable(R.drawable.toolbar_background);
        actionBar.setTitle(Name);
        actionBar.setBackgroundDrawable(drawable);
        actionBar.setSubtitle("online...");
        actionBar.setDisplayUseLogoEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        this.setHasOptionsMenu(true);




        //Glide.with(this).asBitmap().load(Profile).into(chat_profile);
        /*back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new ChatFragment();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.nav_host_fragment, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });*/
        return view;
    }

    @Override
    public void onPrepareOptionsMenu(@NonNull Menu menu) {
        MenuItem user_profile = menu.findItem(R.id.user_profile);
        Glide.with(this).asBitmap().load(Profile).error(R.drawable.ic_baseline_error_outline_24).load(user_profile);
        menu.findItem(R.id.search).setVisible(false);
        menu.findItem(R.id.user_profile).setVisible(true);
        menu.findItem(R.id.Calls_chat).setVisible(true);
        menu.findItem(R.id.Video_chat).setVisible(true);
        menu.findItem(R.id.action_refresh).setVisible(false);
        super.onPrepareOptionsMenu(menu);
    }

}
