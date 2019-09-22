package cl.carreno.mauricio.cargaragefinder.models.place;

import java.io.Serializable;

public class Garage implements Serializable {

    private String reference, icon, name, vicinity, id, place_id;
    private OpeningHours opening_hours;
    private double rating;
    private GaragePhotos[] photos;
    private Geometry geometry;

    public Garage() {
    }

    public String getReference() {
        return this.reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getIcon() {
        return this.icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public OpeningHours getOpening_hours() {
        return this.opening_hours;
    }

    public void setOpening_hours(OpeningHours opening_hours) {
        this.opening_hours = opening_hours;
    }

    public double getRating() {
        return this.rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public String getVicinity() {
        return this.vicinity;
    }

    public void setVicinity(String vicinity) {
        this.vicinity = vicinity;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public GaragePhotos[] getPhotos() {
        return this.photos;
    }

    public void setPhotos(GaragePhotos[] photos) {
        this.photos = photos;
    }

    public String getPlace_id() {
        return this.place_id;
    }

    public void setPlace_id(String place_id) {
        this.place_id = place_id;
    }

    public Geometry getGeometry() {
        return geometry;
    }

    public void setGeometry(Geometry geometry) {
        this.geometry = geometry;
    }
}
