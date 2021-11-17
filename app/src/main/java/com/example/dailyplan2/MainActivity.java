package com.example.dailyplan2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity" ;
    String firstName;
    String lastName;
    String email;
    String password;
    EditText firstNameInput;
    EditText lastNameInput;
    EditText emailInput;
    EditText passwordInput;
    TextView textViewResult;
    Button signUpButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        firstNameInput = (EditText) findViewById(R.id.editTextTextPersonName);
         textViewResult = findViewById(R.id.editTextTextPersonName);
        lastNameInput = (EditText) findViewById(R.id.editTextTextPersonName2);
        emailInput = (EditText) findViewById(R.id.editTextTextEmailAddress);
        passwordInput = (EditText) findViewById(R.id.editTextTextPassword);
        signUpButton = (Button) findViewById(R.id.button4);
        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);
                firstName = firstNameInput.getText().toString();
                lastName = lastNameInput.getText().toString();
                email = emailInput.getText().toString();
                password = passwordInput.getText().toString();

                sendPostRequestOnClick();

            }
        });
    }
    private void sendPostRequestOnClick(){
        JsonPlaceHolderApi api = RetrofitUser.getRetrofitInstance().create(JsonPlaceHolderApi.class);
        Call<User> call = api.getUserInformation(firstName,lastName,email,password);
        call.enqueue(new Callback<User>() {


            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                Log.e(TAG,"Works ");
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.e(TAG,"on failure: " + t.getMessage());

            }
        });
    }
    /*private void createUser(){
        User user = new User(firstName, lastName, email,password);
       // Call<User> call = jsonPlaceHolderApi.createUser(user);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if(!response.isSuccessful())
                {
                    textViewResult.setText(response.code());
                    return;
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                textViewResult.setText(t.getMessage());
            }
        });
    }*/
}