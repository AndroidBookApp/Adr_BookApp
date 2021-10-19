package com.example.app_readbook.list_comment;

public class Comment {
    private int idResource;
    private String username;
    private String user_chat;
    private String icon_like;
    private String icon_feedback;
    private String time;

    public Comment(int idResource, String username, String user_chat, String icon_like, String icon_feedback, String time) {
        this.idResource = idResource;
        this.username = username;
        this.user_chat = user_chat;
        this.icon_like = icon_like;
        this.icon_feedback = icon_feedback;
        this.time = time;
    }

    public int getIdResource() {
        return idResource;
    }

    public void setIdResource(int idResource) {
        this.idResource = idResource;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUser_chat() {
        return user_chat;
    }

    public void setUser_chat(String user_chat) {
        this.user_chat = user_chat;
    }

    public String getIcon_like() {
        return icon_like;
    }

    public void setIcon_like(String icon_like) {
        this.icon_like = icon_like;
    }

    public String getIcon_feedback() {
        return icon_feedback;
    }

    public void setIcon_feedback(String icon_feedback) {
        this.icon_feedback = icon_feedback;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
