package cl.carreno.mauricio.cargaragefinder.models.place;

public class Geometry {

    private Coordinates location;
    private Viewport viewport;

    public Geometry() {
    }

    public Coordinates getLocation() {
        return location;
    }

    public void setLocation(Coordinates location) {
        this.location = location;
    }

    public Viewport getViewport() {
        return viewport;
    }

    public void setViewport(Viewport viewport) {
        this.viewport = viewport;
    }
}
