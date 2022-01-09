package com.example.app_readbook.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class favoriteDeleteData {
    @SerializedName("success")
    @Expose
    private String success;
    @SerializedName("message")
    @Expose
    private Boolean message;

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public Boolean getMessage() {
        return message;
    }

    public void setMessage(Boolean message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "favoriteDeleteData{" +
                "success='" + success + '\'' +
                ", message=" + message +
                '}';
    }
}
