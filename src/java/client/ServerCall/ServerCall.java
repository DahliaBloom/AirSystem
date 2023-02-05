package client.ServerCall;

import org.json.JSONObject;

import java.io.*;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class ServerCall {
    private static String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder("{flights: ");
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        sb.append("}");
        return sb.toString();
    }

    public static JSONObject getJsonFrom(String url) {
        try {
            try (InputStream is = new URL(url).openStream()) {
                BufferedReader rd = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
                String jsonText = readAll(rd);
                return new JSONObject(jsonText);
            }
        } catch (IOException e) {
            System.out.println("Server request threw an error");
            return null;
        }
    }
}
