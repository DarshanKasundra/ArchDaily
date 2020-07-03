package com.aswdc.archdaily.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class ResData {

//    List event in ResData Model

    @SerializedName("list_event")
    @Expose
    private ArrayList<ListEvent> listEvent = null;

    public ArrayList<ListEvent> getListEvent() {
        return listEvent;
    }

    public void setListEvent(ArrayList<ListEvent> listEvent) {
        this.listEvent = listEvent;
    }



    //    profile detail in ResData Model
    @SerializedName("profile_details")
    @Expose
    private List<ProfileDetail> profileDetails = null;

    public List<ProfileDetail> getProfileDetails() {
        return profileDetails;
    }

    public void setProfileDetails(List<ProfileDetail> profileDetails) {
        this.profileDetails = profileDetails;
    }




    //    Event Details detail in ResData Model


    @SerializedName("event_detail")
    @Expose
    private List<EventDetail> eventDetail = null;
    @SerializedName("user_list")
    @Expose
    private List<UserList> userList = null;

    public List<EventDetail> getEventDetail(int eventID) {
        return eventDetail;
    }

    public void setEventDetail(List<EventDetail> eventDetail) {
        this.eventDetail = eventDetail;
    }

    public List<UserList> getUserList(int userId) {
        return userList;
    }

    public void setUserList(List<UserList> userList) {
        this.userList = userList;
    }



//      Photo List in ResData Model
    @SerializedName("main_files")
    @Expose
    private List<MainFile> mainFiles = null;
    @SerializedName("sub_files")
    @Expose
    private List<SubFile> subFiles = null;

    public List<MainFile> getMainFiles()  {
        return mainFiles;
    }

    public void setMainFiles(List<MainFile> mainFiles) {
        this.mainFiles = mainFiles;
    }

    public List<SubFile> getSubFiles() {
        return subFiles;
    }

    public void setSubFiles(List<SubFile> subFiles) {
        this.subFiles = subFiles;
    }


//  total vote
    @SerializedName("total_votes")
    @Expose
    private List<TotalVote> totalVotes = null;

    public List<TotalVote> getTotalVotes() {
        return totalVotes;
    }

    public void setTotalVotes(List<TotalVote> totalVotes) {
        this.totalVotes = totalVotes;
    }



//  even user detail
@SerializedName("list_user_event")
@Expose
private List<ListUserEvent> listUserEvent = null;


//    get User Detail
@SerializedName("user_details")
@Expose
private List<UserDetail> userDetails = null;

public List<UserDetail> getUserDetails() {
    return userDetails;
}

    public void setUserDetails(List<UserDetail> userDetails) {
        this.userDetails = userDetails;
    }

//      Histroy
    @SerializedName("subfiles_with_user_detail_history")
    @Expose
    private List<SubfilesWithUserDetailHistory> subfilesWithUserDetailHistory = null;

    public List<SubfilesWithUserDetailHistory> getSubfilesWithUserDetailHistory() {
        return subfilesWithUserDetailHistory;
    }

    public void setSubfilesWithUserDetailHistory(List<SubfilesWithUserDetailHistory> subfilesWithUserDetailHistory) {
        this.subfilesWithUserDetailHistory = subfilesWithUserDetailHistory;
    }

// event count and vote count
    @SerializedName("User_total_vote")
    @Expose
    private List<UserTotalVote> userTotalVote = null;
    @SerializedName("total_event_participate")
    @Expose
    private List<TotalEventParticipate> totalEventParticipate = null;
    public List<UserTotalVote> getUserTotalVote() {
        return userTotalVote;
    }

    public void setUserTotalVote(List<UserTotalVote> userTotalVote) {
        this.userTotalVote = userTotalVote;
    }

    public List<TotalEventParticipate> getTotalEventParticipate() {
        return totalEventParticipate;
    }

    public void setTotalEventParticipate(List<TotalEventParticipate> totalEventParticipate) {
        this.totalEventParticipate = totalEventParticipate;
    }
//  all winner list
@SerializedName("all_winner_list")
@Expose
private List<AllWinnerList> allWinnerList = null;

    public List<AllWinnerList> getAllWinnerList() {
        return allWinnerList;
    }

    public void setAllWinnerList(List<AllWinnerList> allWinnerList) {
        this.allWinnerList = allWinnerList;
    }
//  your winning list
@SerializedName("user_winner_list")
@Expose
private List<UserWinnerList> userWinnerList = null;

    public List<UserWinnerList> getUserWinnerList() {
        return userWinnerList;
    }

    public void setUserWinnerList(List<UserWinnerList> userWinnerList) {
        this.userWinnerList = userWinnerList;
    }

//    genaret opt
    @SerializedName("user_id")
    @Expose
    private String userId;
    @SerializedName("otp")
    @Expose
    private Integer otp;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Integer getOtp() {
        return otp;
    }

    public void setOtp(Integer otp) {
        this.otp = otp;
    }

}
