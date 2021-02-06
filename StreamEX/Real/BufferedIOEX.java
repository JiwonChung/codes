package Real;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.util.Scanner;


/**
 * BufferedIOEX
 */
public class BufferedIOEX {

    public static void main(String[] args) {
        FileInputStream fileReader = null;
        int c;
        try {
            fileReader = new FileInputStream("C:\\Users\\jwchu\\Desktop\\test\\test.txt");
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(System.out, 5);
            while ((c = fileReader.read()) != -1) {
                bufferedOutputStream.write(c);
            }
            new Scanner(System.in).nextLine();
            bufferedOutputStream.flush();
            fileReader.close();
            bufferedOutputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}