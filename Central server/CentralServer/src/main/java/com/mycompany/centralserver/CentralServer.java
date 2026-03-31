/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.centralserver;

/**
 *
 * @author essay
 */
public class CentralServer {

    public static void main(String[] args) {
        DataBaseHandler db = new DataBaseHandler();
        if (!db.tableExists("parkings")) {
            db.exeReq("CREATE TABLE parkings (id NUMBER GENERATED ALWAYS AS IDENTITY PRIMARY KEY, name VARCHAR2(50), location VARCHAR2(200), ipAdresse VARCHAR2(20), numberOfPlaces NUMBER)");
        }
    }
}
