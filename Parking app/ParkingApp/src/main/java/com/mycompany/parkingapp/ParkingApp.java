package com.mycompany.parkingapp;

import java.util.Scanner;

public class ParkingApp {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.println("💻 Projet Arduino Uploader");

        // Saisie du chemin du projet
        String projectPath = "C:\\Users\\DZ Laptops\\Documents\\Arduino\\projet".trim();

        // Saisie du port
        String port = "COM5".trim();

        // Création de l'objet
        ArduinoUploader uploader = new ArduinoUploader(projectPath, port);

        try {
            // Upload du code
            uploader.uploadProject();

            System.out.println("✅ Le code a été téléchargé sur la carte.");

            // Contrôle après upload
            System.out.println("Vous pouvez maintenant contrôler la carte Arduino via Serial en Java.");

        } catch (Exception e) {
            System.out.println("⚠️ Une erreur s'est produite أثناء la compilation ou le téléchargement :");
            e.printStackTrace();
        }

        input.close();
        System.out.println("Fin du programme.");

        try {
            ArduinoLogger logger = new ArduinoLogger("COM5");

            while (true) {
                int occupied = logger.getOccupiedCount();

                if (occupied != -1) {
                    System.out.println("عدد الأماكن المحجوزة = " + occupied);
                }

                Thread.sleep(1000);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}