package cl.carreno.mauricio.cargaragefinder.models.place;

public class Viewport {

    private Coordinates northeast, southwest;

    public Viewport() {
    }

    public Coordinates getNortheast() {
        return northeast;
    }

    public void setNortheast(Coordinates northeast) {
        this.northeast = northeast;
    }

    public Coordinates getSouthwest() {
        return southwest;
    }

    public void setSouthwest(Coordinates southwest) {
        this.southwest = southwest;
    }
}
