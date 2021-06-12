package GoodPerson;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class Main3 {
    public static void main(String h[]) throws IOException
    {
        try {
            String link_url = "https://www.javatpoint.com";
            URL obj1 = new URL(link_url); //URL Connection Created...
            HttpURLConnection con1 = (HttpURLConnection) obj1.openConnection(); //Http URL Connection Created...
            System.out.println("https://www.javatpoint.com");
            System.out.println("Connection Response Message : "+con1.getResponseMessage());
            System.out.println("Connection Response Code : "+con1.getResponseCode());
            con1.disconnect();
        }
        catch (Exception e) {
            System.out.println( e);
        }
    }
}
