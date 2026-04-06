package com.mycompany.parkingapp;

import com.fazecast.jSerialComm.SerialPort;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Scanner;

public class ArduinoLogger {

    private SerialPort comPort;
    private Scanner scanner;
    private PrintWriter writer;
    private StringBuilder serialBuffer;  // Buffer pour accumuler les données

    public ArduinoLogger(String port) throws Exception {
        comPort = SerialPort.getCommPort(port);
        comPort.setBaudRate(9600);
        comPort.openPort();

        serialBuffer = new StringBuilder();  // Initialiser le buffer
        scanner = new Scanner(comPort.getInputStream());
        writer = new PrintWriter(new FileWriter("parking_log.txt", true));
    }

    // Cette fonction retourne le nombre d'emplacements réservés
    public int getOccupiedCount() {
        try {
            if (comPort.bytesAvailable() > 0) {
                // Lire tous les bytes disponibles
                byte[] buffer = new byte[comPort.bytesAvailable()];
                comPort.readBytes(buffer, buffer.length);
                String data = new String(buffer);
                
                // Ajouter les données au buffer existant
                serialBuffer.append(data);
                
                // Traiter toutes les lignes complètes dans le buffer
                String bufferContent = serialBuffer.toString();
                int newlineIndex;
                
                while ((newlineIndex = bufferContent.indexOf("\n")) != -1) {
                    // Extraire une ligne complète
                    String line = bufferContent.substring(0, newlineIndex).trim();
                    bufferContent = bufferContent.substring(newlineIndex + 1);
                    
                    if (!line.isEmpty()) {
                        System.out.println("Arduino: " + line);
                        
                        // Traiter la ligne
                        if (line.contains("Occupied:")) {
                            String number = line.split(":")[1].trim();
                            try {
                                int occupied = Integer.parseInt(number);
                                System.out.println("عدد الأماكن المحجوزة = " + occupied);
                                writer.println("Occupied = " + occupied);
                                writer.flush();
                                
                                // Mettre à jour le buffer avec le reste
                                serialBuffer = new StringBuilder(bufferContent);
                                return occupied;
                            } catch (NumberFormatException e) {
                                System.err.println("Format invalide: " + number);
                            }
                        } else {
                            // Essayer de parser comme un nombre
                            try {
                                int occupied = Integer.parseInt(line);
                                System.out.println("عدد الأماكن المحجوزة = " + occupied);
                                writer.println("Occupied = " + occupied);
                                writer.flush();
                                
                                // Mettre à jour le buffer avec le reste
                                serialBuffer = new StringBuilder(bufferContent);
                                return occupied;
                            } catch (NumberFormatException e) {
                                // Ce n'est pas un nombre, ignorer
                            }
                        }
                    }
                }
                
                // Sauvegarder le reste non complet dans le buffer
                serialBuffer = new StringBuilder(bufferContent);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return -1;
    }

    public void close() {
        try {
            if (scanner != null) {
                scanner.close();
            }
            if (writer != null) {
                writer.close();
            }
            if (comPort != null) {
                comPort.closePort();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}