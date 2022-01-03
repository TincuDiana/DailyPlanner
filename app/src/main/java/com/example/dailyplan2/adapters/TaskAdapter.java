package com.example.dailyplan2.adapters;

import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import com.example.dailyplan2.R;
import com.example.dailyplan2.activity.ToDoListActivity;
import com.example.dailyplan2.model.Task;

import java.util.List;
import java.util.UUID;

public class TaskAdapter extends ArrayAdapter<Task> {

    private Context myContext;
    private int myResource;

    public TaskAdapter(@NonNull Context context, int resource, @NonNull List<Task> objects) {
        super(context, resource, objects);
        this.myContext = context;
        this.myResource = resource;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = LayoutInflater.from(myContext);
        convertView = layoutInflater.inflate(myResource, parent, false);
        TextView txtName = convertView.findViewById(R.id.txtName2);
        TextView location = convertView.findViewById(R.id.txtLocation2);
        TextView time = convertView.findViewById(R.id.time2);
        TextView person = convertView.findViewById(R.id.person);
        txtName.setText(getItem(position).getDate());
        location.setText(getItem(position).getLocation());
        time.setText(getItem(position).getDate());
        //person.setText(userName(getItem(position).getIdUser()));
        person.setText(userName(getItem(position).getFirstName(), getItem(position).getLastName()));
        //person.setText(getItem(position).getFirstName() + " " + getItem(position).getLastName());
        return convertView;
    }

   /* @RequiresApi(api = Build.VERSION_CODES.N)
    private String userName( UUID idUser){
        return ToDoListActivity.currentEvent.getUsersAttending()
                .stream()
                .filter(user -> user.getId().equals(idUser))
                .map(user -> user.toString())
                .reduce(" ",String::concat);
    }*/

    @RequiresApi(api = Build.VERSION_CODES.N)
    private String userName( String firstName, String lastName){
        return ToDoListActivity.currentEvent.getUsersAttending()
                .stream()
                .filter(user -> user.getFirstname().equals(firstName) && user.getLastname().equals(lastName))
                .map(user -> user.toString())
                .reduce(" ",String::concat);
    }
}
