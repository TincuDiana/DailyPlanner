package com.example.dailyplan2.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.dailyplan2.JsonPlaceHolderApi;
import com.example.dailyplan2.R;
import com.example.dailyplan2.RetrofitUser;
import com.example.dailyplan2.model.Task;

import java.util.Observable;
import java.util.UUID;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Edit_task_pageActivity extends AppCompatActivity {
    Button save;
    Task task ;
    EditText date;
    EditText description;
    EditText location;
    Observable observable = new Observable();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_task_page);
        save = findViewById(R.id.save);
        date = findViewById(R.id.editTextDate);
        description = findViewById(R.id.descriptionField);
        location = findViewById(R.id.locationField);

        save.setOnClickListener(new View.OnClickListener()  {
            @Override
            public void onClick(View v) {
                // task = new Task(date.getText().toString(),description.getText().toString(),location.getText().toString());
                sendUpdateRequestOnClick(v,task);
                observable.notifyObservers();
            }
        });
    }

    private void sendUpdateRequestOnClick(View v,Task newTask){
        JsonPlaceHolderApi api = RetrofitUser.getRetrofitInstance().create(JsonPlaceHolderApi.class);
        //Log.e(TAG,newUser.toString());
        UUID id = UUID.fromString("95d70bef-6df0-4b63-ac41-5ad6d2ea31f5");
        Call<Task> call = api.updateTask(id,newTask);
        call.enqueue(new Callback<Task>() {

            @Override
            public void onResponse(Call<Task> call, Response<Task> response) {
                System.out.println("Works ");
            }

            @Override
            public void onFailure(Call<Task> call, Throwable t) {
                System.out.println( "on failure: ");

            }
        });
    }
}
