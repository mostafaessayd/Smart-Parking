/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.parkingapp;

import com.fazecast.jSerialComm.SerialPort;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Scanner;

public class ArduinoLogger {

    private SerialPort comPort;
    private Scanner scanner;
    private PrintWriter writer;

    public ArduinoLogger(String port) throws Exception {
        comPort = SerialPort.getCommPort(port);
        comPort.setBaudRate(9600);
        comPort.openPort();

        scanner = new Scanner(comPort.getInputStream());
        writer = new PrintWriter(new FileWriter("parking_log.txt", true));
    }

    // هذه الدالة ترجع عدد الأماكن المحجوزة
    public int getOccupiedCount() {
        try {
            if (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                System.out.println("Arduino: " + line);

                if (line.startsWith("Occupied:")) {
                    String number = line.split(":")[1].trim();
                    int occupied = Integer.parseInt(number);

                    // نكتب في الملف
                    writer.println("Occupied = " + occupied);
                    writer.flush();

                    return occupied;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return -1; // إذا لم تصل بيانات
    }

    public void close() {
        try {
            writer.close();
            comPort.closePort();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
