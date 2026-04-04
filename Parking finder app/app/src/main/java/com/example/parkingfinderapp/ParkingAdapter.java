package com.example.parkingfinderapp;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;

import java.util.ArrayList;

public class ParkingAdapter extends ArrayAdapter<Parking> {

    private Activity context;
    private ArrayList<Parking> parkings;
    private double userLat, userLng;

    public ParkingAdapter(Activity context, ArrayList<Parking> parkings, double userLat, double userLng) {
        super(context, R.layout.item_parking, parkings);
        this.context = context;
        this.parkings = parkings;
        this.userLat = userLat;
        this.userLng = userLng;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View view = inflater.inflate(R.layout.item_parking, null, true);

        TextView name = view.findViewById(R.id.txtName);
        TextView places = view.findViewById(R.id.txtPlaces);
        TextView distance = view.findViewById(R.id.txtDistance);
        Button btn = view.findViewById(R.id.btnGo);

        Parking p = parkings.get(position);

        name.setText(p.getName());
        places.setText("Places: " + p.getNumberOfPlacesAvailable());

        float dist = p.getDistance(userLat, userLng);
        distance.setText(String.format("Distance: %.2f km", dist / 1000));

        btn.setOnClickListener(v -> p.getWay(context));

        return view;
    }
}