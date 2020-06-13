package com.aswdc.archdaily.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SubFile {

    @SerializedName("sub_file_path")
    @Expose
    private String subFilePath;

    public String getSubFilePath() {
        return subFilePath;
    }

    public void setSubFilePath(String subFilePath) {
        this.subFilePath = subFilePath;
    }

}