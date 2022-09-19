package com.example.apitask;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Content_Home extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    LinearLayout scan_parcel;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content_home);

        bottomNavigationView = findViewById(R.id.bottomNavigationBar);
        scan_parcel = findViewById(R.id.btnFuelly);

        Animation animation = AnimationUtils.loadAnimation(Content_Home.this, R.anim.bounce_animation);
        scan_parcel.startAnimation(animation);

        scan_parcel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Content_Home.this, EnterChallan.class));
            }
        });

        loadFragment(new ParcelFragment());
        bottomNavigationView = findViewById(R.id.bottomNavigationBar);
        //set home selected
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragmentContainerr, new ParcelFragment());
        transaction.commit();


        bottomNavigationView.setOnItemSelectedListener(item -> {
            if (item.getItemId() == R.id.parcel)
                loadFragment(new ParcelFragment());

            else if (item.getItemId() == R.id.setting) {
                loadFragment(new SettingFragment());
            }

            return true;
        });
    }

    private void loadFragment(Fragment fragment) {

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragmentContainerr, fragment)
                .commit();
    }
}
