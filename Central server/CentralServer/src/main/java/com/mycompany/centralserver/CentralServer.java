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
       //db.exeReq("create table dogs(id number primary key , name varchar(50) , weight number)");
       
       
//db.exeReq("INSERT INTO dogs(id, name, weight) VALUES (2, 'Bella', 15)");
db.exeReq("INSERT INTO dogs(id, name, weight) VALUES (3, 'Max', 25)");
db.exeReq("INSERT INTO dogs(id, name, weight) VALUES (4, 'Luna', 18)");
db.exeReq("INSERT INTO dogs(id, name, weight) VALUES (5, 'Charlie', 22)");
db.exeReq("INSERT INTO dogs(id, name, weight) VALUES (6, 'Lucy', 17)");
db.exeReq("INSERT INTO dogs(id, name, weight) VALUES (7, 'Buddy', 23)");
db.exeReq("INSERT INTO dogs(id, name, weight) VALUES (8, 'Daisy', 19)");
db.exeReq("INSERT INTO dogs(id, name, weight) VALUES (9, 'Rocky', 21)");
db.exeReq("SELECT * FROM DOGS");
    }
}
