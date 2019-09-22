package cl.carreno.mauricio.cargaragefinder.models.place;

import java.util.ArrayList;

public class PlaceDetailsWrapper {
    ArrayList< Object > html_attributions = new ArrayList < Object > ();
    PlaceDetails result;
    private String status;

    public PlaceDetailsWrapper() {
    }

    public PlaceDetails getResult() {
        return this.result;
    }

    public String getStatus() {
        return this.status;
    }


    public void setResult(PlaceDetails placeDetailsObject) {
        this.result = placeDetailsObject;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

