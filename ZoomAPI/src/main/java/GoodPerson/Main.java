package GoodPerson;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Main {
    public static void main(String[] args) throws Exception {
        // jimmychung00@naver.com
        String userId = "tZ8D53GSQRuVp9rJAM1gyQ";
        String meetingId = "9554944367";
//        URL url = new URL("https://api.zoom.us/v2/chat/users/" + userId + "/messages?date=2021-06-12");
        URL url = new URL("https://api.zoom.us/v2/users");
//        URL url = new URL("https://api.zoom.us/v2/chat/users/tZ8D53GSQRuVp9rJAM1gyQ/messages?date=2021-06-12");


        HttpURLConnection http = (HttpURLConnection)url.openConnection();
        http.setRequestProperty("Authorization", "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOm51bGwsImlzcyI6InJRNkRwNU5LU29lc2c4cjBOSHNnZEEiLCJleHAiOjE2MjQxMDAyOTMsImlhdCI6MTYyMzQ5NTQ5NX0.64wHTUd5eNgrzdUqf0Ps_W4twpDWBe00suZ1GZxv9hU");

        System.out.println(http.getResponseCode());
        System.out.println(http.getHeaderFields());
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(http.getInputStream()));
        String line = "";

        StringBuilder builder = new StringBuilder();
        while ((line = bufferedReader.readLine()) != null) {
            builder.append(line);
            System.out.println(line);
        }
        System.out.println(builder);

        bufferedReader.close();
        http.disconnect();
    }
}
