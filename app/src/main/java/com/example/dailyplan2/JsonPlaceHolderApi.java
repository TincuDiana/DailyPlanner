package com.example.dailyplan2;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface JsonPlaceHolderApi {
    @FormUrlEncoded
    @POST("/api/v1/user/")
    Call<User> getUserInformation(@Field("firstname") String firstname,@Field("lastname") String lastname, @Field("email") String email, @Field("password") String password);


}
