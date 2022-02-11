public class WeatherPojo {
    private String date;
    private String city;
    private String region;
    private String country;
    private String temperature;

    public WeatherPojo(String date, String city, String region, String country, String temperature) {
        this.date = date;
        this.city = city;
        this.region = region;
        this.country = country;
        this.temperature = temperature;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    @Override
    public String toString() {
        return
                "Fecha: " + date + "\n" +
                "Ciudad: " + city + "\n" +
                "Región: " + region + "\n" +
                "País: " + country + "\n" +
                "Temperatura: " + temperature + "\n";
    }
}
