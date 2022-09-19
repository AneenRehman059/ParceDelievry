package com.example.apitask.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Utills {
    public static String getTimeString(long timeMills){
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy hh:mm a");
        return formatter.format(new Date(timeMills));
    }

    public static boolean isConnectingToInternet(Context context) {
        try {
            if (context != null) {
                ConnectivityManager connectivity = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
                if (connectivity != null) {
                    NetworkInfo info = connectivity.getActiveNetworkInfo();
                    if (info != null)
                        return info.getState() == NetworkInfo.State.CONNECTED;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
