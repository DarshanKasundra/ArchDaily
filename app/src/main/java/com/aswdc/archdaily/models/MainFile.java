package com.aswdc.archdaily.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MainFile {

    @SerializedName("event_file_id")
    @Expose
    private String eventFileId;
    @SerializedName("main_file")
    @Expose
    private String mainFile;
    @SerializedName("main_file_path")
    @Expose
    private String mainFilePath;

    public String getEventFileId() {
        return eventFileId;
    }

    public void setEventFileId(String eventFileId) {
        this.eventFileId = eventFileId;
    }

    public String getMainFile() {
        return mainFile;
    }

    public void setMainFile(String mainFile) {
        this.mainFile = mainFile;
    }

    public String getMainFilePath() {
        return mainFilePath;
    }

    public void setMainFilePath(String mainFilePath) {
        this.mainFilePath = mainFilePath;
    }

}