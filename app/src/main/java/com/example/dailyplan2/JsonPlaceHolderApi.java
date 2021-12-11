package com.example.dailyplan2;

import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface JsonPlaceHolderApi {
    //@FormUrlEncoded
    @POST("/api/v1/user/addUser/")
    Call<User> getUserInformation(@Body User user);

    @FormUrlEncoded
    @POST("/api/v1/event/")
    Call<Event> getEventInformation(@Field("eventname") String eventName, @Field("description") String description, @Field("data") String data, @Field("location") String location);


}
