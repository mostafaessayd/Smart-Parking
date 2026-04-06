package com.mycompany.parkingapp;

public class ArduinoHandler {

    private ArduinoLogger logger;
    private final int TOTAL_PLACES = 4;

    public ArduinoHandler() {
        try {
            logger = new ArduinoLogger("COM5");

            Thread.sleep(2000);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int getNumberOfOccupiedPlaces() {
        int occupied = logger.getOccupiedCount();

        if (occupied != -1) {
            return occupied;
        }

        return 0;
    }

    public int getNumberOfAvailablePlaces() {
        int occupied = getNumberOfOccupiedPlaces();
        return TOTAL_PLACES - occupied;
    }
}