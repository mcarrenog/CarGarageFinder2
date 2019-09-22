package cl.carreno.mauricio.cargaragefinder.models.place;

public class GaragePhotos {

    private String photo_reference;
    private int width, height;
    private String[] html_attributions;

    public GaragePhotos() {
    }

    public String getPhoto_reference() {
        return this.photo_reference;
    }

    public void setPhoto_reference(String photo_reference) {
        this.photo_reference = photo_reference;
    }

    public int getWidth() {
        return this.width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public String[] getHtml_attributions() {
        return this.html_attributions;
    }

    public void setHtml_attributions(String[] html_attributions) {
        this.html_attributions = html_attributions;
    }

    public int getHeight() {
        return this.height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

}
