package com.aswdc.archdaily.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MainFile {

    @SerializedName("main_file_path")
    @Expose
    private String mainFilePath;

    public String getMainFilePath() {
        return mainFilePath;
    }

    public void setMainFilePath(String mainFilePath) {
        this.mainFilePath = mainFilePath;
    }

}