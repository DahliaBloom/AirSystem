package common.model;

import common.util.WorldMap;
import org.json.JSONArray;
import org.json.JSONObject;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;


public class PlaceOfInterest {
    //Benno war hier
    private WeatherData weatherData;
    private final String name;
    private String information = "";
    private final double[] coordinates;
    private final String xid;

    final static String apiKey = "5ae2e3f221c38a28845f05b6072243912c2200a5ab393883b9c55874";
    final static String type = "cultural";
    final static String radius = "1000";
    final static String limit = "10";


    public void setWeatherData(WeatherData weatherData) {
        this.weatherData = weatherData;
    }

    public PlaceOfInterest(double[] coordinates, String name, String xid) {
        this.name = name;
        this.coordinates = coordinates;
        this.xid = xid;

    }

    public String toString() {
        return "PointOfInterest{" +
                "coordinates=" + Arrays.toString(coordinates) +
                ", name='" + name + '\'' +
                ", xid='" + xid + '\'' +
                '}';
    }

    public WeatherData getWeatherData() {
        return weatherData;
    }

    public double[] getCoordinates() {
        return coordinates;
    }

    public String getXid() {
        return xid;
    }

    public String getName() {
        return name;
    }


    public String getInformation() {
        if (information.equals("")) {
            String[] json = new String[15];
            try {
                URL url = new URL("https://api.opentripmap.com/0.1/en/places/xid/" + xid + "?apikey=" + apiKey);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("GET");
                conn.connect();
                if (conn.getResponseCode() == 200) {
                    Scanner scan = new Scanner(url.openStream());
                    while (scan.hasNext()) {
                        String temp = scan.nextLine();
                        json = temp.split(",");
                        JSONObject tempJson = new JSONObject(temp);
                        try {
                            this.information = tempJson.getJSONObject("wikipedia_extracts").getString("text");
                        } catch (Exception e) {
                            this.information = "No Information available";
                        }
                    }
                } else {
                    information = "No Information available about this place";
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return information;

        } else {
            return this.information;
        }
    }

    public static List<PlaceOfInterest> getPOIs(String query) {
        // catches the first URL to get the coords
        String[] json = new String[5];
        List<PlaceOfInterest> pois = new ArrayList<>();
        try {
            URL url = new URL("https://api.opentripmap.com/0.1/en/places/geoname?name=" + query + "&apikey=" + apiKey);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();
            if (conn.getResponseCode() == 200) {
                Scanner scan = new Scanner(url.openStream());
                while (scan.hasNext()) {
                    String temp = scan.nextLine();
                    System.out.println(temp);
                    json = temp.split(",");
                    System.out.println("Lat:" + json[2]);
                    System.out.println("Lon:" + json[3]);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        //catches the second url to get POIs
        try {
            URL url = new URL("https://api.opentripmap.com/0.1/en/places/radius?radius=" + radius + "&lon=" + json[3].split(":")[1] + "&lat=" + json[2].split(":")[1] + "&kinds=" + type + "&limit=" + limit + "&apikey=" + apiKey);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();
            if (conn.getResponseCode() == 200) {
                Scanner scan = new Scanner(url.openStream());
                while (scan.hasNext()) {
                    String temp = scan.nextLine();
                    System.out.println(temp);
                    JSONObject jsonObj = new JSONObject(temp);
                    System.out.println(jsonObj.getJSONArray("features"));
                    JSONArray allFeatures = jsonObj.getJSONArray("features");
                    for (int i = 0; i < allFeatures.length(); i++) {
                        System.out.println(allFeatures.get(i));
                        JSONObject tempJson = new JSONObject(allFeatures.get(i).toString());
                        String tempJson2 = tempJson.getJSONObject("geometry").getJSONArray("coordinates").toString();
                        String s1 = tempJson2.split(",")[0].substring(1);
                        String s2 = tempJson2.split(",")[1].substring(0, tempJson2.split(",")[1].length() - 1);
                        System.out.println(s1 + s2);
                        Double d1 = Double.parseDouble(s1);
                        Double d2 = Double.parseDouble(s2);
                        pois.add(new PlaceOfInterest(new double[]{d1, d2},
                                tempJson.getJSONObject("properties").getString("name"),
                                tempJson.getJSONObject("properties").getString("xid")
                        ));
                    }
                    pois.forEach(p -> System.out.println(p.toString()));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        PlaceOfInterest[] maprelevant = new PlaceOfInterest[pois.size()];
        pois.toArray(maprelevant);
        WorldMap.adjustpoints(maprelevant);
        return pois;
    }

    public static List<PlaceOfInterest> getPOIsCultural(String query) {
        String actualType = "cultural";
        // catches the first URL to get the coords
        String[] json = new String[5];
        List<PlaceOfInterest> pois = new ArrayList<>();
        try {
            URL url = new URL("https://api.opentripmap.com/0.1/en/places/geoname?name=" + query + "&apikey=" + apiKey);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();
            if (conn.getResponseCode() == 200) {
                Scanner scan = new Scanner(url.openStream());
                while (scan.hasNext()) {
                    String temp = scan.nextLine();
                    System.out.println(temp);
                    json = temp.split(",");
                    System.out.println("Lat:" + json[2]);
                    System.out.println("Lon:" + json[3]);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        //catches the second url to get POIs
        try {
            URL url = new URL("https://api.opentripmap.com/0.1/en/places/radius?radius=" + radius + "&lon=" + json[3].split(":")[1] + "&lat=" + json[2].split(":")[1] + "&kinds=" + actualType + "&limit=" + limit + "&apikey=" + apiKey);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();
            if (conn.getResponseCode() == 200) {
                Scanner scan = new Scanner(url.openStream());
                while (scan.hasNext()) {
                    String temp = scan.nextLine();
                    System.out.println(temp);
                    JSONObject jsonObj = new JSONObject(temp);
                    System.out.println(jsonObj.getJSONArray("features"));
                    JSONArray allFeatures = jsonObj.getJSONArray("features");
                    for (int i = 0; i < allFeatures.length(); i++) {
                        System.out.println(allFeatures.get(i));
                        JSONObject tempJson = new JSONObject(allFeatures.get(i).toString());
                        String tempJson2 = tempJson.getJSONObject("geometry").getJSONArray("coordinates").toString();
                        String s1 = tempJson2.split(",")[0].substring(1);
                        String s2 = tempJson2.split(",")[1].substring(0, tempJson2.split(",")[1].length() - 1);
                        System.out.println(s1 + s2);
                        Double d1 = Double.parseDouble(s1);
                        Double d2 = Double.parseDouble(s2);
                        pois.add(new PlaceOfInterest(new double[]{d1, d2},
                                tempJson.getJSONObject("properties").getString("name"),
                                tempJson.getJSONObject("properties").getString("xid")
                        ));
                    }
                    pois.forEach(p -> System.out.println(p.toString()));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        PlaceOfInterest[] maprelevant = new PlaceOfInterest[pois.size()];
        pois.toArray(maprelevant);
        WorldMap.adjustpoints(maprelevant);
        return pois;
    }

    public static List<PlaceOfInterest> getPOIsNature(String query) {
        String actualType = "natural";
        // catches the first URL to get the coords
        String[] json = new String[5];
        List<PlaceOfInterest> pois = new ArrayList<>();
        try {
            URL url = new URL("https://api.opentripmap.com/0.1/en/places/geoname?name=" + query + "&apikey=" + apiKey);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();
            if (conn.getResponseCode() == 200) {
                Scanner scan = new Scanner(url.openStream());
                while (scan.hasNext()) {
                    String temp = scan.nextLine();
                    System.out.println(temp);
                    json = temp.split(",");
                    System.out.println("Lat:" + json[2]);
                    System.out.println("Lon:" + json[3]);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        //catches the second url to get POIs
        try {
            URL url = new URL("https://api.opentripmap.com/0.1/en/places/radius?radius=" + radius + "&lon=" + json[3].split(":")[1] + "&lat=" + json[2].split(":")[1] + "&kinds=" + actualType + "&limit=" + limit + "&apikey=" + apiKey);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();
            if (conn.getResponseCode() == 200) {
                Scanner scan = new Scanner(url.openStream());
                while (scan.hasNext()) {
                    String temp = scan.nextLine();
                    System.out.println(temp);
                    JSONObject jsonObj = new JSONObject(temp);
                    System.out.println(jsonObj.getJSONArray("features"));
                    JSONArray allFeatures = jsonObj.getJSONArray("features");
                    for (int i = 0; i < allFeatures.length(); i++) {
                        System.out.println(allFeatures.get(i));
                        JSONObject tempJson = new JSONObject(allFeatures.get(i).toString());
                        String tempJson2 = tempJson.getJSONObject("geometry").getJSONArray("coordinates").toString();
                        String s1 = tempJson2.split(",")[0].substring(1);
                        String s2 = tempJson2.split(",")[1].substring(0, tempJson2.split(",")[1].length() - 1);
                        System.out.println(s1 + s2);
                        Double d1 = Double.parseDouble(s1);
                        Double d2 = Double.parseDouble(s2);
                        pois.add(new PlaceOfInterest(new double[]{d1, d2},
                                tempJson.getJSONObject("properties").getString("name"),
                                tempJson.getJSONObject("properties").getString("xid")
                        ));
                    }
                    pois.forEach(p -> System.out.println(p.toString()));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        PlaceOfInterest[] maprelevant = new PlaceOfInterest[pois.size()];
        pois.toArray(maprelevant);
        WorldMap.adjustpoints(maprelevant);
        return pois;
    }

    public static List<PlaceOfInterest> getPOIsArchitecture(String query) {
        String actualType = "architecture";
        // catches the first URL to get the coords
        String[] json = new String[5];
        List<PlaceOfInterest> pois = new ArrayList<>();
        try {
            URL url = new URL("https://api.opentripmap.com/0.1/en/places/geoname?name=" + query + "&apikey=" + apiKey);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();
            if (conn.getResponseCode() == 200) {
                Scanner scan = new Scanner(url.openStream());
                while (scan.hasNext()) {
                    String temp = scan.nextLine();
                    System.out.println(temp);
                    json = temp.split(",");
                    System.out.println("Lat:" + json[2]);
                    System.out.println("Lon:" + json[3]);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        //catches the second url to get POIs
        try {
            URL url = new URL("https://api.opentripmap.com/0.1/en/places/radius?radius=" + radius + "&lon=" + json[3].split(":")[1] + "&lat=" + json[2].split(":")[1] + "&kinds=" + actualType + "&limit=" + limit + "&apikey=" + apiKey);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();
            if (conn.getResponseCode() == 200) {
                Scanner scan = new Scanner(url.openStream());
                while (scan.hasNext()) {
                    String temp = scan.nextLine();
                    System.out.println(temp);
                    JSONObject jsonObj = new JSONObject(temp);
                    System.out.println(jsonObj.getJSONArray("features"));
                    JSONArray allFeatures = jsonObj.getJSONArray("features");
                    for (int i = 0; i < allFeatures.length(); i++) {
                        System.out.println(allFeatures.get(i));
                        JSONObject tempJson = new JSONObject(allFeatures.get(i).toString());
                        String tempJson2 = tempJson.getJSONObject("geometry").getJSONArray("coordinates").toString();
                        String s1 = tempJson2.split(",")[0].substring(1);
                        String s2 = tempJson2.split(",")[1].substring(0, tempJson2.split(",")[1].length() - 1);
                        System.out.println(s1 + s2);
                        Double d1 = Double.parseDouble(s1);
                        Double d2 = Double.parseDouble(s2);
                        pois.add(new PlaceOfInterest(new double[]{d1, d2},
                                tempJson.getJSONObject("properties").getString("name"),
                                tempJson.getJSONObject("properties").getString("xid")
                        ));
                    }
                    pois.forEach(p -> System.out.println(p.toString()));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        PlaceOfInterest[] maprelevant = new PlaceOfInterest[pois.size()];
        pois.toArray(maprelevant);
        WorldMap.adjustpoints(maprelevant);

        return pois;
    }


    public static List<PlaceOfInterest> sortPOIsByName(List<PlaceOfInterest> to_sort){
       return to_sort.stream().sorted((p1,p2)->p1.getName().compareTo(p2.getName())).toList();
    }

    public static void main(String[] args) {
        //sortPOIsByName( getPOIsArchitecture("tokyo")).stream().forEach(p-> System.out.println("-----------"+p.toString()));
        getPOIs("helsinki");
       // WorldMap.setTravelroute( 0,0,11.6875,   48.1360 );
        // TODO removed WorldMap.main(args);
    }
}
