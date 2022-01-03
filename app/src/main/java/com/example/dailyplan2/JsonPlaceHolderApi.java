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
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface JsonPlaceHolderApi {
    //@FormUrlEncoded
    @POST("/api/v1/user/addUser/")
    Call<User> getUserInformation(@Body User user);

    //    @FormUrlEncoded
    @POST("/api/v1/event/addEvent/")
    Call<Event> getEventInformation(@Body Event event);

    @GET("/api/v1/calendar/getToDoList")
    Call<List<Task>> getToDoList(UUID idUser, String date);

    @GET("/api/v1/task/getTasks/")
    Call<List<Task>> getTasks();

    @POST("/api/v1/task/addTask/")
    Call<Task> getTaskInformation(@Body Task task);

    @GET("/api/v1/task/getTaskByUserAndDate/{firstname}/{lastname}/{data}/")
    Call<List<Task>> getTaskByUserAndDate(@Path("firstname") String firstname,@Path("lastname") String lastname,@Path("data") String data);

    @FormUrlEncoded
    @POST("api/v1/task/addTaskEvent/")
    Call<Task> populateTaskEventTable(@Field("idTask") UUID idUser, @Field("idEvent") UUID idEvent);

    @GET("/api/v1/event/getEvents/")
    Call<List<Event>> getEvents();

    @GET("/api/v1/calendar/getToDoList")
    Call<List<Task>> getToDoList(String firstname, String lastname, String date);


    @PUT("/api/v1/task/update/{idTask}")
    Call<Task> updateTask(@Path("idTask") UUID idTask, @Body Task task);

    @PUT("/api/v1/event/update/{idevent}/")
    Call<Event> updateEvent(@Path("idevent") UUID idEvent, @Body Event event);



    @GET("/api/v1/user/getUserName/{firstname}/{password}/" )
    Call<User> getUserByNameAndPassword(@Path("firstname") String firstname,@Path("password") String password);


    @GET("/api/v1/user/getUserByName/{firstname}/{lastname}/" )
    Call<User> getUserByName(@Path("firstname") String firstname,@Path("lastname") String lastname);

    @FormUrlEncoded
    @POST("/api/v1/user/addUserEvent/")
    Call<User> populateUserEventTable(@Field("idUser") UUID idUser, @Field("idEvent") UUID idEvent);
}
