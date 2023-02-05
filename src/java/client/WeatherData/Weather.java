package client.WeatherData;

import client.Location;

import static client.WeatherData.WeatherState.*;

public class Weather {

    public final String degrees;
    public final WeatherState weatherState;

    private Weather(String degrees, WeatherState weatherState) {
        this.degrees = degrees;
        this.weatherState = weatherState;
    }

    public static Weather getInstance(Location location) {
        return new Weather("28", (new WeatherState[]{Sunny,
                Cloudy,
                Covered,
                Rainy,
                Storm,
                SunAndRain}[(int) (Math.random() * 6)]));
    }
}
