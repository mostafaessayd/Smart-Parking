package com.example.parkingfinderapp;

import java.util.ArrayList;

public class ParkingFinder {
    public ArrayList<Parking> getAllParkings () {
        ArrayList<Parking> parkings = new ArrayList<>();

        // define random parkings
        parkings.add(new Parking("Parking Centre Ville",
                "https://maps.google.com/?q=35.6971,-0.6308", 120));

        parkings.add(new Parking("Parking Front de Mer",
                "https://maps.google.com/?q=35.7126,-0.6413", 80));

        parkings.add(new Parking("Parking Gambetta",
                "https://maps.google.com/?q=35.6999,-0.6350", 60));

        parkings.add(new Parking("Parking Akid Lotfi",
                "https://maps.google.com/?q=35.7200,-0.6050", 150));

        parkings.add(new Parking("Parking Es Senia",
                "https://maps.google.com/?q=35.6239,-0.6210", 200));

        parkings.add(new Parking("Parking USTO",
                "https://maps.google.com/?q=35.7050,-0.5860", 90));

        parkings.add(new Parking("Parking Bir El Djir",
                "https://maps.google.com/?q=35.7205,-0.5750", 110));

        parkings.add(new Parking("Parking Hai Sabah",
                "https://maps.google.com/?q=35.6960,-0.6000", 70));

        parkings.add(new Parking("Parking Maraval",
                "https://maps.google.com/?q=35.7040,-0.6200", 50));

        parkings.add(new Parking("Parking Canastel",
                "https://maps.google.com/?q=35.7480,-0.5900", 130));

        
        return parkings;
    }
}
