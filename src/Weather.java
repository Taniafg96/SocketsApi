import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Weather {
    private String city;
    private URL url;
    private static final SimpleDateFormat FORMATDATE = new SimpleDateFormat("yyyy-MM-dd");

    private String region;
    private String country;
    private String temperature;
    private String cityName;

    private WeatherPojo weather;

    public Weather(String city){
        this.city = city;
        getJason();
    }

    private void getJason(){
        String URL= "https://api.weatherapi.com/v1/current.json?key=96c0765ed9bd4e0d8f2174629220702&q=" +
                city + "&dt=" + dateTomorrow();

        try {
            url = new URL(URL);

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();

            int answer = connection.getResponseCode();

            if(answer != 200) throw new RuntimeException("HttpResponseCode: " + answer);
            else{
                String text = "";
                Scanner scanner = new Scanner(url.openStream());

                while(scanner.hasNext()){
                    text += scanner.nextLine();
                }
                scanner.close();


                JSONParser parse = new JSONParser();
                JSONObject data = (JSONObject) parse.parse(text);

                JSONObject objectWeather = (JSONObject) data.get("current");
                JSONObject objectCity = (JSONObject) data.get("location");

                getCityInformation(objectCity);
                getWeatherInformation(objectWeather);

                weather = new WeatherPojo(dateTomorrow(), cityName, region, country, temperature);
            }
        } catch (MalformedURLException ex) {
            System.out.println("ERROR: " + ex.getMessage());
        } catch (IOException | NullPointerException | ParseException ex) {
            System.out.println("ERROR: " + ex.getMessage());
        }
    }

    private void getWeatherInformation(JSONObject obj){
        JSONArray arr = new JSONArray();
        arr.add(obj);
        for(int i = 0; i < arr.size(); i++){
            JSONObject object = (JSONObject) arr.get(i);

            temperature = object.get("temp_c") + " ºC";
        };
    }

    private void getCityInformation(JSONObject obj){
        JSONArray arr = new JSONArray();
        arr.add(obj);
        for(int i = 0; i < arr.size(); i++){
            JSONObject object = (JSONObject) arr.get(i);

            cityName = "Nombre: " + object.get("name");
            region = "Región: " + object.get("region");
            country = "País: " + object.get("country");
        };
    }

    private String dateTomorrow() {
        Calendar dateToday = Calendar.getInstance();
        dateToday.add(Calendar.DATE, +1);

        Date date = dateToday.getTime();
        String tomorrowDate = FORMATDATE.format(date);
        return tomorrowDate;
    }

    public WeatherPojo getWeather(){
        return weather;
    }
}
