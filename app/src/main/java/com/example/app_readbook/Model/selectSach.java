package com.example.app_readbook.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class selectSach {
    @SerializedName("sach")
    @Expose
    private List<Sach> sach;
    @SerializedName("success")
    @Expose
    private String success;
    @SerializedName("message")
    @Expose
    private String message;

    public selectSach(List<Sach> sach) {
        this.sach = sach;
    }

    public List<Sach> getSach() {
        return sach;
    }

    public void setSach(List<Sach> sach) {
        this.sach = sach;
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
