package com.example.dailyplan2;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface JsonPlaceHolderApi {
    @POST("user")
    Call<User> createPost(@Body User user);

}
