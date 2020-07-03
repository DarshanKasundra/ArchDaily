package com.aswdc.archdaily.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SubFile {

    @SerializedName("event_file_id")
    @Expose
    private String eventFileId;
    @SerializedName("sub_file")
    @Expose
    private String subFile;
    @SerializedName("sub_file_path")
    @Expose
    private String subFilePath;

    public String getEventFileId() {
        return eventFileId;
    }

    public void setEventFileId(String eventFileId) {
        this.eventFileId = eventFileId;
    }

    public String getSubFile() {
        return subFile;
    }

    public void setSubFile(String subFile) {
        this.subFile = subFile;
    }

    public String getSubFilePath() {
        return subFilePath;
    }

    public void setSubFilePath(String subFilePath) {
        this.subFilePath = subFilePath;
    }
}