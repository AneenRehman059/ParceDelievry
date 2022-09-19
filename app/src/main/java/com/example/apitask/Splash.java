package com.example.apitask;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ProgressBar;

public class Splash extends AppCompatActivity {
    private static final long SPLASH_SCREEN_TIME_OUT = 2000;
    Context context;
    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        handler();
    }

    private void handler () {


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {






//                        progressBar.setVisibility(View.GONE);
                startActivity(new Intent(Splash.this, MainActivity.class));
                finish();
            }
        }, SPLASH_SCREEN_TIME_OUT);

    }


    @Override
    public void onBackPressed () {
        super.onBackPressed();
        finish();
    }

}