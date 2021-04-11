package com.example.mychat.ui.feed;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class FeedViewModel extends ViewModel {

    private String Name_feed;
    private String profile_feed;
    private String post_feed;
    private String Likes_feed;

    public FeedViewModel(String name_feed, String profile_feed, String post_feed) {
        this.Name_feed = name_feed;
        this.profile_feed = profile_feed;
        this.post_feed = post_feed;
        //this.Likes_feed = likes_feed;
    }

    public String getName_feed() {
        return Name_feed;
    }

    public void setName_feed(String name_feed) {
        this.Name_feed = name_feed;
    }

    public String getProfile_feed() {
        return profile_feed;
    }

    public void setProfile_feed(String profile_feed) {
        this.profile_feed = profile_feed;
    }

    public String getPost_feed() {
        return post_feed;
    }

    public void setPost_feed(String post_feed) {
        this.post_feed = post_feed;
    }

    public String getLikes_feed() {
        return Likes_feed;
    }

    public void setLikes_feed(String likes_feed) {
        this.Likes_feed = likes_feed;
    }

    /*private MutableLiveData<String> mText;

    public FeedViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is feed fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }*/
}