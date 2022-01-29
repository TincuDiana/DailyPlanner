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

public class EventMainActivity extends AppCompatActivity {
    private static final String TAG = "EventMainActivity" ;
    String eventName;
    String description;
    String data;
    String location;
    EditText eventNameInput;
    EditText descriptionInput;
    EditText dataInput;
    EditText locationInput;
    TextView textViewResult;
    Button addEventButton;
    public static Event event;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_create_event);
        eventNameInput = findViewById(R.id.eventNameField);
        textViewResult = findViewById(R.id.eventNameField);
        descriptionInput = findViewById(R.id.descriptionField);
        dataInput = findViewById(R.id.dateField);
        dataInput.setText(CalendarActivity.date);
        System.out.println("DATA CALENDAR " + CalendarActivity.date);
        locationInput = findViewById(R.id.locationField);
        addEventButton = findViewById(R.id.createEventButton);
        addEventButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                eventName = eventNameInput.getText().toString();
                description = descriptionInput.getText().toString();
                //dataInput.setText(CalendarActivity.date);
                data = dataInput.getText().toString();
                location = locationInput.getText().toString();
                sendPostRequestOnClick();
                Intent myIntent = new Intent(EventMainActivity.this, CreateTaskActivity.class);
                startActivity(myIntent);
            }
        });
    }

    private void sendPostRequestOnClick(){

        JsonPlaceHolderApi api = RetrofitUser.getRetrofitInstance().create(JsonPlaceHolderApi.class);
        event = new Event(eventName,description,data,location);
        CalendarActivity.events.add(event);
        Call<Event> call = api.getEventInformation(event);
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
                Call<User> callForSecondTable =  api.populateUserEventTable(LoginActivity.user.getId(),event.getIdEvent());

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
