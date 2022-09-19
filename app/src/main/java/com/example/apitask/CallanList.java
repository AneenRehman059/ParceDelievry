package com.example.apitask;

import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class CallanList extends AppCompatActivity {
    TextView date_txt,time_txt;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.challan_list_design);

//        date_txt = findViewById(R.id.challan_date);
        time_txt = findViewById(R.id.challan_time);

        Calendar calendar = Calendar.getInstance();
        String currentDate = DateFormat.getDateInstance().format(calendar.getTime());
//        date_txt.setText(currentDate);

        time_txt.setText(getCurrentTime());
    }

    private String getCurrentTime(){
        return new SimpleDateFormat("hh:mm", Locale.getDefault()).format(new Date());
    }
}
