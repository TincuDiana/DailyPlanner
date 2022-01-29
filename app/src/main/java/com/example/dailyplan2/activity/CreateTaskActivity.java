package com.example.dailyplan2.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.dailyplan2.JsonPlaceHolderApi;
import com.example.dailyplan2.R;
import com.example.dailyplan2.RetrofitUser;
import com.example.dailyplan2.model.Event;
import com.example.dailyplan2.model.Task;
import com.example.dailyplan2.model.User;
import com.example.dailyplan2.ui.login.LoginActivity;

import java.sql.SQLOutput;
import java.util.Timer;
import java.util.TimerTask;
import java.util.UUID;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreateTaskActivity extends AppCompatActivity {
    private static final String TAG = "CreateTaskActivity" ;
    String firstName;
    String lastName;
    String data;
    String description;
    String location;
    EditText firstNameInput;
    EditText lastNameInput;
    EditText descriptionInput;
    EditText dataInput;
    EditText locationInput;
    Button addTaskButton;
    Button doneButton;

    UUID id_user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_task);
        firstNameInput = findViewById(R.id.firstnametext);
        lastNameInput = findViewById(R.id.lastnametext);
        descriptionInput = findViewById(R.id.descriptiontext);
        dataInput = findViewById(R.id.datetext);
        dataInput.setText(CalendarActivity.date);
        System.out.println("DATA CALENDAR " + CalendarActivity.date);
        locationInput = findViewById(R.id.locationtext);
        addTaskButton = findViewById(R.id.createTaskButton);
        doneButton = findViewById(R.id.doneButton);
        doneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(CreateTaskActivity.this, CalendarActivity.class);
                startActivity(myIntent);
            }
        });

        addTaskButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firstName = firstNameInput.getText().toString();
                lastName = lastNameInput.getText().toString();
                description = descriptionInput.getText().toString();
                data = dataInput.getText().toString();
                location = locationInput.getText().toString();
                sendPostRequestOnClick(v);
                Intent myIntent = new Intent(CreateTaskActivity.this, CreateTaskActivity.class);
                setUsersAttendingEvent(v);
                startActivity(myIntent);
            }
        });
    }
    private void sendPostRequestOnClick( View v){

        JsonPlaceHolderApi api = RetrofitUser.getRetrofitInstance().create(JsonPlaceHolderApi.class);
        Task newTask = new Task(firstName, lastName, data, description,location);
        //EventMainActivity.event.getTaskList().add(newTask);
        Call<Task> call = api.getTaskInformation(newTask);
        call.enqueue(new Callback<Task>() {

            @Override
            public void onResponse(Call<Task> call, Response<Task> response) {
                Log.e(TAG,"Task Works ");
            }

            @Override
            public void onFailure(Call<Task> call, Throwable t) {
                Log.e(TAG,"on failure: " + t.getMessage());

            }
        });

        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                // this code will be executed after 2 seconds
                UUID idEvent;
                Call<Task> callForSecondTable;
                if(EventMainActivity.event != null)
                {
                    callForSecondTable =  api.populateTaskEventTable(newTask.getIdTask(),EventMainActivity.event.getIdEvent());

                }
                else
                {
                    callForSecondTable =  api.populateTaskEventTable(newTask.getIdTask(),ToDoListActivity.currentEvent.getIdEvent());

                }
                callForSecondTable.enqueue(new Callback<Task>() {
                    @Override
                    public void onResponse(Call<Task> call, Response<Task> response) {

                    }

                    @Override
                    public void onFailure(Call<Task> call, Throwable t) {

                    }
                });
            }
        }, 2000);

    }


    private void setUsersAttendingEvent(View v){
        JsonPlaceHolderApi api = RetrofitUser.getRetrofitInstance().create(JsonPlaceHolderApi.class);
        Call<User> user = api.getUserByName(firstName, lastName);
        System.out.println("{FIRSTNAME}=" + firstName);
        final UUID[] idUser2 = new UUID[1];
        user.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {

                idUser2[0] = response.body().getId();
                System.out.println("[ID user task]" + idUser2[0]);
                UUID idEvent;
                if(EventMainActivity.event != null)
                    idEvent =  EventMainActivity.event.getIdEvent();
                else
                    idEvent =ToDoListActivity.currentEvent.getIdEvent();
                Call<User> eventCall = api.populateUserEventTable(idUser2[0], idEvent);
                eventCall.enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {
                    }
                    @Override
                    public void onFailure(Call<User> call, Throwable t) {
                    }
                });
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

            }
        });


    }
}
