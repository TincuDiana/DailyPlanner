package com.example.dailyplan2.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.dailyplan2.JsonPlaceHolderApi;
import com.example.dailyplan2.R;
import com.example.dailyplan2.RetrofitUser;
import com.example.dailyplan2.model.Event;
import com.example.dailyplan2.model.User;
import com.example.dailyplan2.ui.login.LoginActivity;

import java.util.Timer;
import java.util.TimerTask;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditEventActivity extends AppCompatActivity {
    private static final String TAG = "EditEventActivity" ;
    String eventName;
    String description;
    String data;
    String location;
    EditText eventNameInput;
    EditText descriptionInput;
    EditText dataInput;
    EditText locationInput;
    TextView textViewResult;
    Button editButton;
    Button addTaskButton;
    public static Event event;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_event);
        eventNameInput = findViewById(R.id.editEventNameField);
        eventNameInput.setText(ToDoListActivity.currentEvent.getEventName());
        textViewResult = findViewById(R.id.editEventNameField);
        descriptionInput = findViewById(R.id.editDescriptionField);
        descriptionInput.setText(ToDoListActivity.currentEvent.getDescription());
        dataInput = findViewById(R.id.editDateField);
        dataInput.setText(ToDoListActivity.currentEvent.getData());
        locationInput = findViewById(R.id.editLocationField);
        locationInput.setText(ToDoListActivity.currentEvent.getLocation());
        editButton = findViewById(R.id.save_edit_button);
        addTaskButton = findViewById(R.id.editAddTaskButton);
        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                eventName = eventNameInput.getText().toString();
                description = descriptionInput.getText().toString();
                data = dataInput.getText().toString();
                location = locationInput.getText().toString();
                sendPostRequestOnClick();
                Intent myIntent = new Intent(EditEventActivity.this, CalendarActivity.class);
                startActivity(myIntent);
            }
        });
        addTaskButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(EditEventActivity.this, CreateTaskActivity.class);
                startActivity(myIntent);
            }
        });

    }



    private void sendPostRequestOnClick(){

        JsonPlaceHolderApi api = RetrofitUser.getRetrofitInstance().create(JsonPlaceHolderApi.class);
//        event = new Event(eventName,description,data,location);
        ToDoListActivity.currentEvent.setEventName(eventName);
        System.out.println("name =========== " + eventName);
        System.out.println("name from event ======= " + ToDoListActivity.currentEvent.getEventName());
        ToDoListActivity.currentEvent.setDescription(description);
        ToDoListActivity.currentEvent.setData(data);
        ToDoListActivity.currentEvent.setLocation(location);
        Call<Event> call = api.updateEvent(ToDoListActivity.currentEvent.getIdEvent(),ToDoListActivity.currentEvent);
        call.enqueue(new Callback<Event>() {

            @Override
            public void onResponse(Call<Event> call, Response<Event> response) {
                Log.e(TAG,"Works ");
            }

            @Override
            public void onFailure(Call<Event> call, Throwable t) {
                Log.e(TAG,"on failure: " + t.getMessage());

            }
        });
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                // this code will be executed after 2 seconds
                Call<User> callForSecondTable =  api.populateUserEventTable(LoginActivity.user.getId(),ToDoListActivity.currentEvent.getIdEvent());

                callForSecondTable.enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {

                    }

                    @Override
                    public void onFailure(Call<User> call, Throwable t) {

                    }
                });
            }
        }, 2000);


    }

}
