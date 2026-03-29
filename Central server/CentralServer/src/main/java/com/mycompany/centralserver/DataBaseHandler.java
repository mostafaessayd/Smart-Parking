/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.centralserver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.sql.SQLException;

public class DataBaseHandler {

    private Connection conn;

    public DataBaseHandler() {
        try {
            conn = DriverManager.getConnection(
                "jdbc:oracle:thin:@localhost:1521/XEPDB1",
                "system",
                "123456789"
            );
            System.out.println("Connected to Oracle");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void exeReq(String req) {
    try {
        Statement stmt = conn.createStatement();

        // تحقق مما إذا كان الاستعلام SELECT
        if (req.trim().toUpperCase().startsWith("SELECT")) {
            ResultSet rs = stmt.executeQuery(req); // executeQuery لـ SELECT
            ResultSetMetaData meta = rs.getMetaData();
            int columnCount = meta.getColumnCount();

            // طباعة أسماء الأعمدة
            for (int i = 1; i <= columnCount; i++) {
                System.out.print(meta.getColumnName(i) + "\t");
            }
            System.out.println("\n---------------------------------");

            // طباعة البيانات
            while (rs.next()) {
                for (int i = 1; i <= columnCount; i++) {
                    System.out.print(rs.getString(i) + "\t");
                }
                System.out.println();
            }

            rs.close();
        } else {
            int rowsAffected = stmt.executeUpdate(req);
            System.out.println("Executed successfully, rows affected: " + rowsAffected);
        }

        stmt.close();
    } catch (SQLException e) {
        e.printStackTrace();
    }
}
}