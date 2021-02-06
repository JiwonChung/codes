package com.company;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        try
        {
            FileInputStream fileInputStream = new FileInputStream(
                    "C:\\Users\\jwchu\\Videos\\Captures\\Netflix - Chrome 2020-12-17 20-39-46.mp4");
            FileOutputStream fileOutputStream = new FileOutputStream(
                    "C:\\Users\\jwchu\\Desktop\\New folder (2)\\00.mp4");

            int a;
            while ((a = fileInputStream.read()) != -1) {
                fileOutputStream.write(a);
                fileOutputStream.flush();
            }
            fileOutputStream.close();
            fileInputStream.close();

        } catch (IOException e) {
            System.out.println("sysout0");
            e.printStackTrace();
        }
    }
}
