package com.mycompany.parkingapp;

public class ParkingApp {
    public static void main(String[] args) {

        ArduinoHandler handler = new ArduinoHandler();

        System.out.println("Available places = " + handler.getNumberOfAvailablePlaces());
        System.out.println("Occupied places = " + handler.getNumberOfOccupiedPlaces());
    }
}