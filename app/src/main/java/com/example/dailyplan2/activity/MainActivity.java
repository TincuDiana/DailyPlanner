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
import com.example.dailyplan2.model.User;
import com.example.dailyplan2.ui.login.LoginActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity" ;
    EditText firstNameInput;
    EditText lastNameInput;
    EditText emailInput;
    EditText passwordInput;
    TextView textViewResult;
    Button signUpButtonn;
    public static User user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        firstNameInput = (EditText) findViewById(R.id.editTextTextPersonName);
        textViewResult = findViewById(R.id.editTextTextPersonName);
        lastNameInput = (EditText) findViewById(R.id.editTextTextPersonName2);
        emailInput = (EditText) findViewById(R.id.editTextTextEmailAddress);
        passwordInput = (EditText) findViewById(R.id.editTextTextPassword);
        signUpButtonn = (Button) findViewById(R.id.button4);
        signUpButtonn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);
                user = new User(firstNameInput.getText().toString(),lastNameInput.getText().toString(),emailInput.getText().toString(),passwordInput.getText().toString());
                Log.e(TAG,user.toString());
                Intent myIntent = new Intent(MainActivity.this, LoginActivity.class);
                sendPostRequestOnClick(user,v);
                startActivity(myIntent);

            }
        });
    }
    private void sendPostRequestOnClick(User newUser,View v){
        JsonPlaceHolderApi api = RetrofitUser.getRetrofitInstance().create(JsonPlaceHolderApi.class);
        Log.e(TAG,newUser.toString());
        Call<User> call = api.getUserInformation(newUser);
        call.enqueue(new Callback<User>() {

            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                Log.e(TAG,"Works " + newUser.getFirstname()+ newUser.getLastname());
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.e(TAG,"on failure: ");

            }
        });
    }
}