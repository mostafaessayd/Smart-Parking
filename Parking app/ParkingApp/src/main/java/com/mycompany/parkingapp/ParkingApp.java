package com.mycompany.parkingapp;

import java.util.Scanner;

public class ParkingApp {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.println("💻 مشروع Arduino Uploader");

        // إدخال مسار المشروع (المجلد الذي يحتوي على projet.ino)
        System.out.print("أدخل مسار مشروع Arduino (مثال: projet): ");
        String projectPath = input.nextLine().trim();

        // إدخال البورت الذي تتصل به اللوحة
        System.out.print("أدخل البورت المتصل به Arduino (مثال: COM5): ");
        String port = input.nextLine().trim();

        // إنشاء الكائن
        ArduinoUploader uploader = new ArduinoUploader(projectPath, port);

        try {
            // رفع الكود
            uploader.uploadProject();

            System.out.println("✅ الكود أصبح على اللوحة.");

            // بعد رفع الكود، يمكننا التحكم في Arduino Serial إذا أردنا
            System.out.println("يمكنك الآن التحكم باللوحة من Java باستخدام Serial.");

        } catch (Exception e) {
            System.out.println("⚠️ حدث خطأ أثناء compile أو upload:");
            e.printStackTrace();
        }

        input.close();
        System.out.println("تم الانتهاء من البرنامج.");
    }
}