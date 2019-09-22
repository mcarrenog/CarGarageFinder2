package cl.carreno.mauricio.cargaragefinder.models.place;

public class PlaceDetails {

    private String formatted_phone_number;
    private String name;
    private String url;

    public PlaceDetails() {
    }

    public String getFormatted_phone_number() {
        return this.formatted_phone_number;
    }

    public String getName() {
        return this.name;
    }

    public String getUrl() {
        return this.url;
    }


    public void setFormatted_phone_number(String formatted_phone_number) {
        this.formatted_phone_number = formatted_phone_number;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
