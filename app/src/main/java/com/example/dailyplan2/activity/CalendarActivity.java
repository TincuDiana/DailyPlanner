package com.example.dailyplan2.activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.dailyplan2.JsonPlaceHolderApi;
import com.example.dailyplan2.R;
import com.example.dailyplan2.RetrofitUser;
import com.example.dailyplan2.model.Event;
import com.example.dailyplan2.model.Task;
import com.example.dailyplan2.model.User;
import com.example.dailyplan2.ui.login.LoginActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CalendarActivity extends AppCompatActivity {
    CalendarView calendarView;
    public static String date;
    TextView myDate;
    public static List<Event> events;
    Button viewActivities;
    Button newEvent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calendarview);

        calendarView = (CalendarView) findViewById(R.id.calendarView);
        myDate = (TextView) findViewById(R.id.myDate);
        viewActivities = (Button) findViewById(R.id.button1);
        newEvent = (Button) findViewById(R.id.addEvent);

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                date = year + "-" + (month + 1) + "-" + dayOfMonth;
                System.out.println("DATA DIN CALENDAR" + date);
                myDate.setText(date);
                getRequest();
            }
        });

        viewActivities.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent myIntent = new Intent(CalendarActivity.this, ToDoListActivity.class);
                startActivity(myIntent);
            }
        });

        newEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(CalendarActivity.this, EventMainActivity.class);
                startActivity(myIntent);
            }

        });

    }
    public void getRequest( ){
        JsonPlaceHolderApi api = RetrofitUser.getRetrofitInstance().create(JsonPlaceHolderApi.class);
        Call<List<Event>> call = api.getEvents();
        call.enqueue(new Callback<List<Event>>() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onResponse(Call<List<Event>> call, Response<List<Event>> response) {
                System.out.println("Works");
                System.out.println(LoginActivity.user.getFirstname());
                List<Event> resp = response.body();
                events = new ArrayList<>();
                List<Event> newTasks = resp.stream()
                        .filter(event -> event.getData().equals(date))
                        .collect(Collectors.toList());

                for (int i = 0; i < newTasks.size(); i++) {
                    for (User userX:newTasks.get(i).getUsersAttending()) {
                        if(userX.getId().equals(LoginActivity.user.getId())){
                            System.out.println(userX.getFirstname());
                            System.out.println(userX.getLastname());
                            events.add(newTasks.get(i));
                            break;
                        }
                    }

                }
                System.out.println("!!!!!TASKS");
                for (int i = 0; i < events.size(); i++) {
                    System.out.println(events.get(i).getData());
                }
            }

            @Override
            public void onFailure(Call<List<Event>> call, Throwable t) {
                System.out.println("Failed");
            }
        });
    }

}
