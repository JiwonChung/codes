package GoodPerson;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class Main4 {
    public static void main(String h[]) throws IOException
    {
        //starting the try block
        try {
            String link_url = "https://www.facebook.com";
            URL obj1 = new URL(link_url); //URL Connection Created...
            HttpURLConnection con1 = (HttpURLConnection) obj1.openConnection(); //Http URL Connection Created...
            System.out.println("https://www.facebook.com");
            System.out.println("Connection Response Message : ");
            con1.disconnect();
        }
        catch (Exception e) {
            System.out.println( e);
        }
    }
}
