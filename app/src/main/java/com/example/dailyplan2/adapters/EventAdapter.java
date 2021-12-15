package com.example.dailyplan2.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.dailyplan2.R;
import com.example.dailyplan2.model.Event;
import java.util.List;

public class EventAdapter extends ArrayAdapter<Event> {
    private Context myContext;
    private int myResource;

    public EventAdapter(@NonNull Context context, int resource, @NonNull List<Event> objects) {
        super(context, resource, objects);
        this.myContext = context;
        this.myResource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = LayoutInflater.from(myContext);
        convertView = layoutInflater.inflate(myResource, parent, false);
        TextView txtName = convertView.findViewById(R.id.txtName);
        TextView location = convertView.findViewById(R.id.txtLocation);
        TextView time = convertView.findViewById(R.id.time);
        txtName.setText(getItem(position).getEventName());
        location.setText(getItem(position).getLocation());
        time.setText(getItem(position).getData());
        return convertView;
    }
}
