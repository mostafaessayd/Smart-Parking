package com.mycompany.parkingapp;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class ArduinoUploader {

    private String projectPath;
    private String port;
    private String fqbn;
    private String buildPath;

    public ArduinoUploader(String projectPath, String port) {
        this.projectPath = projectPath;
        this.port = port;
        this.fqbn = "arduino:avr:uno"; // يمكنك تغييره لأي لوحة أخرى
        this.buildPath = "build";       // مجلد build داخل المشروع
    }

    private void runCommand(String[] command) throws Exception {
        ProcessBuilder pb = new ProcessBuilder(command);
        pb.redirectErrorStream(true);

        Process process = pb.start();

        BufferedReader reader = new BufferedReader(
                new InputStreamReader(process.getInputStream())
        );

        String line;
        while ((line = reader.readLine()) != null) {
            System.out.println(line);
        }

        int exitCode = process.waitFor();
        if (exitCode != 0) {
            throw new RuntimeException("Command failed with exit code: " + exitCode);
        }
    }

    // Compile مع تحديد build path
    public void compile() throws Exception {
        String[] cmd = {
                "arduino-cli",
                "compile",
                "--fqbn", fqbn,
                "--build-path", buildPath,
                projectPath
        };

        System.out.println("📦 Compiling project...");
        runCommand(cmd);
        System.out.println("✅ Compile finished successfully.");
    }

    // Upload مع تحديد build path
    public void upload() throws Exception {
        String[] cmd = {
                "arduino-cli",
                "upload",
                "-p", port,
                "--fqbn", fqbn,
                "--build-path", buildPath,
                projectPath
        };

        System.out.println("📤 Uploading to Arduino...");
        runCommand(cmd);
        System.out.println("✅ Upload finished successfully.");
    }

    // Compile + Upload
    public void uploadProject() throws Exception {
        compile();
        upload();
        System.out.println("🎉 تم رفع الكود على Arduino بنجاح!");
    }

    // Setter لتغيير Board إذا أحببت
    public void setFqbn(String fqbn) {
        this.fqbn = fqbn;
    }

    // Setter لتغيير Build Path
    public void setBuildPath(String buildPath) {
        this.buildPath = buildPath;
    }

}