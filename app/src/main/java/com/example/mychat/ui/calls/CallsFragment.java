package com.example.mychat.ui.calls;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.mychat.R;

public class CallsFragment extends Fragment {

    private CallsViewModel callsViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        callsViewModel =
                new ViewModelProvider(this).get(CallsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_calls, container, false);
        final TextView textView = root.findViewById(R.id.text_calls);
        callsViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}