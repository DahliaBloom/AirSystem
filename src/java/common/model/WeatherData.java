package common.model;


import org.json.JSONObject;

import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class WeatherData {
    private double temperature;
    private double humidity;
    private String condition;
    private final static String key = "505b64f90130447a93995902222606";

    public WeatherData(String query) {
        try {
            URL url = new URL("https://api.weatherapi.com/v1/current.json?key=" + key + "&q=" + query + "&aqi=no");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();
            if (conn.getResponseCode() == 200) {
                Scanner scan = new Scanner(url.openStream());
                while (scan.hasNext()) {
                    String temp = scan.nextLine();
                    JSONObject tempJson = new JSONObject(temp);
                    temperature = ((BigDecimal)tempJson.getJSONObject("current").get("temp_c")).doubleValue();
                    humidity = (double) (int) tempJson.getJSONObject("current").get("humidity");
                    condition = (String) tempJson.getJSONObject("current").getJSONObject("condition").get("text");

                }
            } else {
                temperature = 0;
                humidity = 0;
                condition = "No Weather Available";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public double getHumidity() {
        return humidity;
    }

    public void setHumidity(double humidity) {
        this.humidity = humidity;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    @Override
    public String toString() {
        return "WeatherData{" +
                "temperature=" + temperature +
                ", humidity=" + humidity +
                ", condition='" + condition + '\'' +
                '}';
    }


}
