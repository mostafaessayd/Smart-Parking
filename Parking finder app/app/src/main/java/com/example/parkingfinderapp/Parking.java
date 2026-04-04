package com.example.parkingfinderapp;

import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.net.Uri;

public class Parking {
    private String name;
    private String location; // Google Maps URL
    private int numberOfPlacesAvailable;

    public Parking(String name, String location, int npa) {
        this.name = name;
        this.location = location;
        this.numberOfPlacesAvailable = npa;
    }

    public String getName() {
        return name;
    }

    public int getNumberOfPlacesAvailable() {
        return numberOfPlacesAvailable;
    }

    // فتح Google Maps للوصول للموقف
    public void getWay(Context context) {
        String coordinates = location.split("q=")[1];
        Uri uri = Uri.parse("google.navigation:q=" + coordinates);
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        intent.setPackage("com.google.android.apps.maps");
        context.startActivity(intent);
    }

    // احسب المسافة بالمتر
    public float getDistance(double userLat, double userLng) {
        String coordinates = location.split("q=")[1];
        String[] parts = coordinates.split(",");

        double parkingLat = Double.parseDouble(parts[0]);
        double parkingLng = Double.parseDouble(parts[1]);

        float[] results = new float[1];
        Location.distanceBetween(userLat, userLng, parkingLat, parkingLng, results);

        return results[0];
    }
}