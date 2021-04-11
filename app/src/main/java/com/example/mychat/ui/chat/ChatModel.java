package com.example.mychat.ui.chat;

public class ChatModel {
    private String message;
    private String name;
    private Long timestamp;

    public ChatModel() {
    }

    ChatModel(String message, String name, Long timestamp) {
        this.message = message;
        this.name = name;
        this.timestamp = timestamp;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

}
