package com.example.mychat.ui.chat;

import android.content.Intent;

import androidx.lifecycle.ViewModel;

public class ChatViewModel extends ViewModel {

    private String Names;
    private String ImageUrls;
    private String Description;


    public ChatViewModel(String names, String imageUrls) {
        this.Names = names;
        this.ImageUrls = imageUrls;
       // this.Description = description;
    }


    public void setNames(String names) {
        Names = names;
    }

    public void setImageUrls(String imageUrls) {
        ImageUrls = imageUrls;
    }

    public void setDescription(String description) { Description = description; }

    public String getNames() {
        return Names;
    }
    public String getImageUrls() {
        return ImageUrls;
    }

    public String getDescription() { return Description; }


   /* private MutableLiveData<String> mText;

    public ChatViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is Chat fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }*/
}