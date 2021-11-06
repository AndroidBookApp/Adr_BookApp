package com.example.app_readbook.activity;

import android.text.TextUtils;
import android.util.Patterns;

import java.io.Serializable;

public class User implements Serializable {
    private String idMember;
    private String Name;
    private String Username;
    private String Pass;
    private String email;
    private String old;
    private String date;

    public User(String idMember, String name, String username, String pass, String email, String old, String date) {
        this.idMember = idMember;
        Name = name;
        Username = username;
        Pass = pass;
        this.email = email;
        this.old = old;
        this.date = date;
    }

    public String getIdMember() {
        return idMember;
    }

    public void setIdMember(String idMember) {
        this.idMember = idMember;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getPass() {
        return Pass;
    }

    public void setPass(String pass) {
        Pass = pass;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getOld() {
        return old;
    }

    public void setOld(String old) {
        this.old = old;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
    public boolean isValidEmail()
    {
        return !TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }
    public boolean isValidPass()
    {
        return !TextUtils.isEmpty(Pass) && Pass.length() >=6;
    }
}
