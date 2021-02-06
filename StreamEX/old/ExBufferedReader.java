package old;

import java.io.BufferedReader;
import java.io.FileReader;


public class ExBufferedReader {
    public static void main(String[] args) {
        String line;

        try {
            BufferedReader in = new BufferedReader(new FileReader(args[0]));
            while ((line = in.readLine()) != null) {
                System.out.println(line);
            }
            in.close();
        
        
        } catch (Exception e) {}

    }
}
