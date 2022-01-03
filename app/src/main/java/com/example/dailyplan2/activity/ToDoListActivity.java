package com.example.dailyplan2.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.dailyplan2.JsonPlaceHolderApi;
import com.example.dailyplan2.R;
import com.example.dailyplan2.RetrofitUser;
import com.example.dailyplan2.adapters.EventAdapter;
import com.example.dailyplan2.model.Event;
import com.example.dailyplan2.model.Task;

import java.util.List;
import java.util.UUID;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ToDoListActivity extends AppCompatActivity {
    ListView listView;
    private static final String TAG = "To Do List" ;
    public static Event currentEvent;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activitiesview);
        listView = findViewById(R.id.listView);
        // getRequest(idU, CalendarActivity.date);
        if(CalendarActivity.events != null) {
            EventAdapter adapter = new EventAdapter(this, R.layout.list_row, CalendarActivity.events);
            listView.setAdapter(adapter);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    currentEvent = CalendarActivity.events.get(position);
                    Intent myIntent = new Intent(ToDoListActivity.this, ViewEventActivity.class);
                    startActivity(myIntent);
                }
            });
        }
        else
            System.out.println("No events");
    }

    public void getRequest(UUID idUser, String date){
        JsonPlaceHolderApi api = RetrofitUser.getRetrofitInstance().create(JsonPlaceHolderApi.class);
        Call<List<Task>> call = api.getTasks();
        call.enqueue(new Callback<List<Task>>() {

            @Override
            public void onResponse(Call<List<Task>> call, Response<List<Task>> response) {
                Log.e(TAG,"Works ");
                List<Task> resp = response.body();
            }

            @Override
            public void onFailure(Call<List<Task>> call, Throwable t) {
                Log.e(TAG,"on failure: ");
            }
        });
    }

}
