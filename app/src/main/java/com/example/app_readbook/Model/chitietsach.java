package com.example.app_readbook.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class chitietsach {
    @SerializedName("IdChitietsach")
    @Expose
    private String idChitietsach;
    @SerializedName("idSach")
    @Expose
    private String idSach;
    @SerializedName("Luotxem")
    @Expose
    private String luotxem;
    @SerializedName("Feedback")
    @Expose
    private String feedback;
    @SerializedName("sochuong")
    @Expose
    private String sochuong;
    @SerializedName("Favorite")
    @Expose
    private String favorite;

    public String getIdChitietsach() {
        return idChitietsach;
    }

    public void setIdChitietsach(String idChitietsach) {
        this.idChitietsach = idChitietsach;
    }

    public String getIdSach() {
        return idSach;
    }

    public void setIdSach(String idSach) {
        this.idSach = idSach;
    }

    public String getLuotxem() {
        return luotxem;
    }

    public void setLuotxem(String luotxem) {
        this.luotxem = luotxem;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public String getSochuong() {
        return sochuong;
    }

    public void setSochuong(String sochuong) {
        this.sochuong = sochuong;
    }

    public String getFavorite() {
        return favorite;
    }

    public void setFavorite(String favorite) {
        this.favorite = favorite;
    }
}
