package com.example.apitask;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserDetails {
    @SerializedName("User_Id")
    @Expose
    private String userId;

    @SerializedName("User_Pass")
    @Expose
    private String userPass;

    @SerializedName("User_Name")
    @Expose
    private String userName;

    @SerializedName("Last_Lat")
    @Expose
    private String lastLat;

    @SerializedName("Last_Long")
    @Expose
    private String lastLong;

    @SerializedName("Last_Location")
    @Expose
    private String lastLocation;


    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserPass() {
        return userPass;
    }

    public void setUserPass(String userPass) {
        this.userPass = userPass;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getLastLat() {
        return lastLat;
    }

    public void setLastLat(String lastLat) {
        this.lastLat = lastLat;
    }

    public String getLastLong() {
        return lastLong;
    }

    public void setLastLong(String lastLong) {
        this.lastLong = lastLong;
    }

    public String getLastLocation() {
        return lastLocation;
    }

    public void setLastLocation(String lastLocation) {
        this.lastLocation = lastLocation;
    }
}
