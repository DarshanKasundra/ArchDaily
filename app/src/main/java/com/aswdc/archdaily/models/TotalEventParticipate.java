package com.aswdc.archdaily.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TotalEventParticipate {

    @SerializedName("total_participate")
    @Expose
    private String totalParticipate;

    public String getTotalParticipate() {
        return totalParticipate;
    }

    public void setTotalParticipate(String totalParticipate) {
        this.totalParticipate = totalParticipate;
    }

}
