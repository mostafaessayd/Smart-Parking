package com.example.parkingfinderapp;

public class Parking {
    private String name;
    private String location;
    private int NumberOfPlacesAvailable;

    Parking(String name , String location , int npa) {
        this.name = name;
        this.location = location;
        this.NumberOfPlacesAvailable = npa;
    }

    // Give directions from the current location to this parking space
    public void getWay() {
    }

    // Give the distance from the current location to this position
    public int getDistance() {
        int distance = 0;
        // write your code here
        return distance;
    }
}
