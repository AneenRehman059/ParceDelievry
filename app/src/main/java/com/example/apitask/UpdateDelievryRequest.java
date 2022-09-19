package com.example.apitask;

import com.google.gson.annotations.SerializedName;

public class UpdateDelievryRequest {

    @SerializedName("Delivery_By")
    private String delieverdBY;
    @SerializedName("Delivery_Reason")
    private String delieverdReason;
    @SerializedName("Delivery_Remarks")
    private String delievryREMARKS;
    @SerializedName("Challan_Code")
    private String challanCODE;
    @SerializedName("Delivery_Location")
    private String delievryLOCATION;
    @SerializedName("Delivery_Lat")
    private String delievryLAT;
    @SerializedName("Delivery_Long")
    private String DelievryLONG;
    @SerializedName("Delivery_Image_String")
    private String delievrySTRING;


    public String getDelieverdBY() {
        return delieverdBY;
    }

    public void setDelieverdBY(String delieverdBY) {
        this.delieverdBY = delieverdBY;
    }

    public String getDelieverdReason() {
        return delieverdReason;
    }

    public void setDelieverdReason(String delieverdReason) {
        this.delieverdReason = delieverdReason;
    }

    public String getDelievryREMARKS() {
        return delievryREMARKS;
    }

    public void setDelievryREMARKS(String delievryREMARKS) {
        this.delievryREMARKS = delievryREMARKS;
    }

    public String getChallanCODE() {
        return challanCODE;
    }

    public void setChallanCODE(String challanCODE) {
        this.challanCODE = challanCODE;
    }

    public String getDelievryLOCATION() {
        return delievryLOCATION;
    }

    public void setDelievryLOCATION(String delievryLOCATION) {
        this.delievryLOCATION = delievryLOCATION;
    }

    public String getDelievryLAT() {
        return delievryLAT;
    }

    public void setDelievryLAT(String delievryLAT) {
        this.delievryLAT = delievryLAT;
    }

    public String getDelievryLONG() {
        return DelievryLONG;
    }

    public void setDelievryLONG(String delievryLONG) {
        DelievryLONG = delievryLONG;
    }

    public String getDelievrySTRING() {
        return delievrySTRING;
    }

    public void setDelievrySTRING(String delievrySTRING) {
        this.delievrySTRING = delievrySTRING;
    }
}
