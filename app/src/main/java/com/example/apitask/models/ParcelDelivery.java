package com.example.apitask.models;

import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "parcel_delievry")
public class ParcelDelivery {

    @PrimaryKey(autoGenerate = true)
    int id;

    @ColumnInfo(name = "qrCode")
    public String parcelNo;

    @ColumnInfo(name = "delievry_status")
    public String delievryStatus;

    @ColumnInfo(name = "delievry_time")
    public String delievryTime;

    public ParcelDelivery() {

    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getParcelNo() {
        return parcelNo;
    }

    public void setParcelNo(String parcelNo) {
        this.parcelNo = parcelNo;
    }

    public String getDelievryStatus() {
        return delievryStatus;
    }

    public void setDelievryStatus(String delievryStatus) {
        this.delievryStatus = delievryStatus;
    }

    public String getDelievryTime() {
        return delievryTime;
    }

    public void setDelievryTime(String delievryTime) {
        this.delievryTime = delievryTime;
    }

    //    String qrCode;
//    String deliveryStatus;
//    String deliverTime;
//
//    private Boolean isSameQrCode = false;
//
//
//    public Boolean getSameQrCode() {
//
//        return isSameQrCode;
//    }
//
//    public void setSameQrCode(Boolean sameQrCode) {
//
//        isSameQrCode = sameQrCode;
//    }
//
//    public int getId() {
//
//        return id;
//    }
//
//    public void setId(int id) {
//
//        this.id = id;
//    }
//
//
//    public String getQrCode() {
//        return qrCode;
//    }
//
//    public void setQrCode(String qrCode) {
//
//        this.qrCode = qrCode;
//    }
//
//    public String getDeliveryStatus() {
//        return deliveryStatus;
//    }
//
//    public void setDeliveryStatus(String deliveryStatus) {
//        this.deliveryStatus = deliveryStatus;
//    }
//
//    public String getDeliverTime() {
//        return deliverTime;
//    }
//
//    public void setDeliverTime(String deliverTime) {
//        this.deliverTime = deliverTime;
//    }
}

