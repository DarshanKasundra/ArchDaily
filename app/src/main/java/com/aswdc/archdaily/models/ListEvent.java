package com.aswdc.archdaily.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ListEvent {
    @SerializedName("event_id")
    @Expose
    private int eventId;
    @SerializedName("event_name")
    @Expose
    private String eventName;
    @SerializedName("main_banner")
    @Expose
    private String mainBanner;
    @SerializedName("main_banner_path")
    @Expose
    private String mainBannerPath;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("start_date")
    @Expose
    private String startDate;
    @SerializedName("end_date")
    @Expose
    private String endDate;
    @SerializedName("fees")
    @Expose
    private String fees;
    @SerializedName("max_contestant")
    @Expose
    private String maxContestant;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("created_date")
    @Expose
    private String createdDate;
    @SerializedName("updated_Date")
    @Expose
    private String updatedDate;

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    //    public String getEventId() {
//        return eventId;
//    }
//
//    public void setEventId(String eventId) {
//        this.eventId = eventId;
//    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getMainBanner() {
        return mainBanner;
    }

    public void setMainBanner(String mainBanner) {
        this.mainBanner = mainBanner;
    }

    public String getMainBannerPath() {
        return mainBannerPath;
    }

    public void setMainBannerPath(String mainBannerPath) {
        this.mainBannerPath = mainBannerPath;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getFees() {
        return fees;
    }

    public void setFees(String fees) {
        this.fees = fees;
    }

    public String getMaxContestant() {
        return maxContestant;
    }

    public void setMaxContestant(String maxContestant) {
        this.maxContestant = maxContestant;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(String updatedDate) {
        this.updatedDate = updatedDate;
    }
}
//    @SerializedName("event_id")
//    private int event_id;
//
//    @SerializedName("event_name")
//    private String event_name;
//
//    @SerializedName("main_banner")
//    private String main_banner;
//
//    @SerializedName("main_banner_path")
//    private String main_banner_path;
//
//    @SerializedName("description")
//    private String description;
//
//    @SerializedName("start_date")
//    private String start_date;
//
//    @SerializedName("end_date")
//    private String end_date;
//
//    @SerializedName("fees")
//    private String fees;
//
//    @SerializedName("max_contestant")
//    private String max_contestant;
//
//    @SerializedName("status")
//    private String status;
//
//    @SerializedName("created_date")
//    private String created_date;
//
//    public int getEvent_id() {
//        return event_id;
//    }
//
//    public void setEvent_id(int event_id) {
//        this.event_id = event_id;
//    }
//
//    public String getEvent_name() {
//        return event_name;
//    }
//
//    public void setEvent_name(String event_name) {
//        this.event_name = event_name;
//    }
//
//    public String getMain_banner() {
//        return main_banner;
//    }
//
//    public void setMain_banner(String main_banner) {
//        this.main_banner = main_banner;
//    }
//
//    public String getMain_banner_path() {
//        return main_banner_path;
//    }
//
//    public void setMain_banner_path(String main_banner_path) {
//        this.main_banner_path = main_banner_path;
//    }
//
//    public String getDescription() {
//        return description;
//    }
//
//    public void setDescription(String description) {
//        this.description = description;
//    }
//
//    public String getStart_date() {
//        return start_date;
//    }
//
//    public void setStart_date(String start_date) {
//        this.start_date = start_date;
//    }
//
//    public String getEnd_date() {
//        return end_date;
//    }
//
//    public void setEnd_date(String end_date) {
//        this.end_date = end_date;
//    }
//
//    public String getFees() {
//        return fees;
//    }
//
//    public void setFees(String fees) {
//        this.fees = fees;
//    }
//
//    public String getMax_contestant() {
//        return max_contestant;
//    }
//
//    public void setMax_contestant(String max_contestant) {
//        this.max_contestant = max_contestant;
//    }
//
//    public String getStatus() {
//        return status;
//    }
//
//    public void setStatus(String status) {
//        this.status = status;
//    }
//
//    public String getCreated_date() {
//        return created_date;
//    }
//
//    public void setCreated_date(String created_date) {
//        this.created_date = created_date;
//    }
//
//    public String getUpdated_Date() {
//        return updated_Date;
//    }
//
//    public void setUpdated_Date(String updated_Date) {
//        this.updated_Date = updated_Date;
//    }
//
//    @SerializedName("updated_Date")
//    private String updated_Date;
//}
