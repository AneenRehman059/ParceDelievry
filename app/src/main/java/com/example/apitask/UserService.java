package com.example.apitask;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface UserService {

    @POST("Account/Login")
    Call<LoginResponse> userLogin(@Body LoginRequest loginRequest);

    @POST("Account/UpdateDelivery")
    Call<UpdateDelievryResponse> delievryParcel(@Body
                                                UpdateDelievryRequest updateDelievryRequest);
}
