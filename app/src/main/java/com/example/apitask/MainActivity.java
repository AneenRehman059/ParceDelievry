package com.example.apitask;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import com.example.apitask.databinding.ActivityMainBinding;
import com.example.apitask.utils.Utills;
import com.lusfold.spinnerloading.SpinnerLoading;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    String ed_id,ed_password;
    View view;
    SpinnerLoading spl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        binding.loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (isValid()){
                    login();
                }
                binding.spinnerLoading.setVisibility(View.VISIBLE);
                binding.spinnerLoading.setPaintMode(0);
                binding.spinnerLoading.setCircleRadius(7);
            }
        });
    }

    private boolean isValid() {
        ed_id = binding.userID.getText().toString();
        ed_password = binding.password.getText().toString();
        boolean is_valid = true;
        if (ed_id.length() <4 && ed_id.equals("")){
            binding.userID.setError("Please Enter Valid Id");
            is_valid = false;
        }
        if (ed_password.length() <4 && ed_password.equals("")){
            binding.password.setError("Please Enter Valid Password");
            is_valid = false;
        }

        return is_valid;
    }

    public void login() {

        if (!Utills.isConnectingToInternet(this)){
            new CustomToastError().Show_Toast(this,view,"Please make sure your device is connected with internet");
        }

        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setPassword(binding.password.getText().toString());
        loginRequest.setUserID(binding.userID.getText().toString());
        Call<LoginResponse> loginResponseCall = ApiClient.getUserService().userLogin(loginRequest);

        loginResponseCall.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(MainActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(MainActivity.this,Content_Home.class);
                    startActivity(intent);
                }
                    else {
                    Toast.makeText(MainActivity.this, "login Failed", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Throwable" + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }
}