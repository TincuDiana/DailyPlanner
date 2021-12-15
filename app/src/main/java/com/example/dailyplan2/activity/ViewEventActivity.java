package com.example.dailyplan2.activity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.dailyplan2.R;
import com.example.dailyplan2.adapters.TaskAdapter;
import com.example.dailyplan2.adapters.UserAdapter;

public class ViewEventActivity extends AppCompatActivity {
    EditText description;
    Button edit;
    Button delete;
    ListView tasks;
    ListView users;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.event_view);
        description = (EditText) findViewById(R.id.descriptionFieldEvent);
        description.setText(ToDoListActivity.currentEvent.getDescription());
        description.setEnabled(false);
        tasks = (ListView) findViewById(R.id.tasksList);
        users = (ListView) findViewById(R.id.usersList);
        TaskAdapter taskAdapter = new TaskAdapter(this,R.layout.row_task_list,ToDoListActivity.currentEvent.getTaskList());
        tasks.setAdapter(taskAdapter);

        UserAdapter userAdapter = new UserAdapter(this,R.layout.row_user_list,ToDoListActivity.currentEvent.getUsersAttending());
        users.setAdapter(userAdapter);
    }
}
