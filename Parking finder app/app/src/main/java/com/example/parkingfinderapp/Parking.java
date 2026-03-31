package com.example.parkingfinderapp;

public class Parking {
    private String name;
    private String location;
    private int NumberOfPlacesAvailable;
    private String mostafa;
 
    Parking(String name , String location , int npa) {
        this.name = name;
        this.location = location;
        this.NumberOfPlacesAvailable = npa;
    }

    public String getName() {
        return name;
    }

    public int getNumberOfPlacesAvailable() {
        return NumberOfPlacesAvailable;
    }

    // Give directions from the current location to this parking space
    public void getWay() {
        // write your code here
    }

    // Give the distance from the current location to this position
    public int getDistance() {
        int distance = 0;
        // write your code here
        return distance;
    }
}
