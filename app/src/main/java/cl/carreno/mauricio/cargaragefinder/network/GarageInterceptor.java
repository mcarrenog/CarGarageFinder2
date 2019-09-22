package cl.carreno.mauricio.cargaragefinder.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class GarageInterceptor {

    private static final String BASE_URL = "https://maps.googleapis.com/maps/api/place/nearbysearch/";
    private static final String BASE_URL_PLACE_DETAILS = "https://maps.googleapis.com/maps/api/place/details/";

    public GarageInterface getBasic() {
        Retrofit interceptor = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        GarageInterface garageRequest = interceptor.create(GarageInterface.class);

        return garageRequest;
    }

    public PlaceDetailsInterface getPlaceDetails() {
        Retrofit interceptor = new Retrofit.Builder()
                .baseUrl(BASE_URL_PLACE_DETAILS)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        PlaceDetailsInterface placeDetailsInterfaceRequest = interceptor.create(PlaceDetailsInterface.class);

        return placeDetailsInterfaceRequest;
    }
}
