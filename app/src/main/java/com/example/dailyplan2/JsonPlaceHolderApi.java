package com.example.dailyplan2;

import com.example.dailyplan2.model.Event;
import com.example.dailyplan2.model.Task;
import com.example.dailyplan2.model.User;

import java.util.List;
import java.util.UUID;

import kotlin.ParameterName;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface JsonPlaceHolderApi {
    //@FormUrlEncoded
    @POST("/api/v1/user/addUser/")
    Call<User> getUserInformation(@Body User user);

    @FormUrlEncoded
    @POST("/api/v1/event/")
    Call<Event> getEventInformation(@Field("eventname") String eventName, @Field("description") String description, @Field("data") String data, @Field("location") String location);

    @GET("/api/v1/calendar/getToDoList")
    Call<List<Task>> getToDoList(UUID idUser, String date);

    @GET("/api/v1/task/getTasks/")
    Call<List<Task>> getTasks();

    @GET("/api/v1/event/getEvents/")
    Call<List<Event>> getEvents();

    @PUT("/api/v1/task/update/{idTask}")
    Call<Task> updateTask(@Path("idTask") UUID idTask, @Body Task task);

    @GET("/api/v1/user/getUserName/{firstname}/{password}/" )
    Call<User> getUserByNameAndPassword(@Path("firstname") String firstname,@Path("password") String password);

}
