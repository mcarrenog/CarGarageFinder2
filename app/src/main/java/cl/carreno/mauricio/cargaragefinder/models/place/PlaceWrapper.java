package cl.carreno.mauricio.cargaragefinder.models.place;

public class PlaceWrapper {

    private Garage[] results;
    private String status;

    public PlaceWrapper() {
    }

    public Garage[] getResults() {
        return results;
    }

    public void setResults(Garage[] results) {
        this.results = results;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
