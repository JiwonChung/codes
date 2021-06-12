package hotGoodBye;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Iterator;
import java.util.List;

public class Main {

    public static void main(String[] args) throws Exception {
        URL url = new URL("http://example.com");
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        System.out.println(FullResponseBuilder.getFullResponse(con));
    }

    public static class FullResponseBuilder {
        public static String getFullResponse(HttpURLConnection con) throws Exception {
            StringBuilder returnValue = new StringBuilder();

            returnValue.append(con.getResponseCode())
                    .append(" ")
                    .append(con.getResponseMessage())
                    .append("\n");

            con.getHeaderFields()
                    .entrySet()
                    .stream()
                    .filter(entry -> entry.getKey() != null)
                    .forEach(entry -> {

                        returnValue.append(entry.getKey())
                                .append(": ");

                        List<String> headerValues = entry.getValue();
                        Iterator<String> it = headerValues.iterator();
                        if (it.hasNext()) {
                            returnValue.append(it.next());

                            while (it.hasNext()) {
                                returnValue.append(", ")
                                        .append(it.next());
                            }
                        }

                        returnValue.append("\n");
                    });

            Reader streamReader = null;

            if (con.getResponseCode() > 299) {
                streamReader = new InputStreamReader(con.getErrorStream());
            } else {
                streamReader = new InputStreamReader(con.getInputStream());
            }

            BufferedReader in = new BufferedReader(streamReader);
            String inputLine;
            StringBuilder content = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }

            in.close();

            returnValue.append("Response: ")
                    .append(content);

            return returnValue.toString();
        }
    }
}
