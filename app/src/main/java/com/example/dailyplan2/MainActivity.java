package com.example.dailyplan2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.json.JSONException;
import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    String firstName;
    String lastName;
    String email;
    String password;
    EditText firstNameInput;
    EditText lastNameInput;
    EditText emailInput;
    EditText passwordInput;

    Button signUpButton;
    private JsonPlaceHolderApi jsonPlaceHolderApi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        firstNameInput = (EditText) findViewById(R.id.editTextTextPersonName);
        lastNameInput = (EditText) findViewById(R.id.editTextTextPersonName2);
        emailInput = (EditText) findViewById(R.id.editTextTextEmailAddress);
        passwordInput = (EditText) findViewById(R.id.editTextTextPassword);
        signUpButton = (Button) findViewById(R.id.button4);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://localhost:8090/api/v1/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);


        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                firstName = firstNameInput.getText().toString();
                lastName = lastNameInput.getText().toString();
                email = emailInput.getText().toString();
                password = passwordInput.getText().toString();
                JSONObject user = new JSONObject();
                try {
                    user.put("firstname", firstName);
                    user.put("lastname", lastName);
                    user.put("email", email);
                    user.put("password", password);

                } catch (JSONException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                createPost();

            }
        });
    }
    private void createPost(){
        User user = new User(firstName, lastName, email,password);
        Call<User> call = jsonPlaceHolderApi.createPost(user);
    }
}