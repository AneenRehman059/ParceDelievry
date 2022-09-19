package com.example.apitask;

import com.google.gson.annotations.SerializedName;

public class LoginRequest {

    @SerializedName("User_Id")
    private String userID;
    @SerializedName("User_Pass")
    private String password;




    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }
}
