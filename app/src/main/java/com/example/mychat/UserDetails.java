package com.example.mychat;

public class UserDetails {
    public String uid, name, status, imageUrl;

    public UserDetails() {
    }


    public UserDetails(String imageUrl, String name, String status, String uid) {
        this.uid = uid;
        this.name = name;
        this.status = status;
        this.imageUrl = imageUrl;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
