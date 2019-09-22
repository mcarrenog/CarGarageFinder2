package cl.carreno.mauricio.cargaragefinder.network;

import java.util.HashMap;

import cl.carreno.mauricio.cargaragefinder.models.place.PlaceDetailsWrapper;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

public interface PlaceDetailsInterface {
    @GET("json")
    Call<PlaceDetailsWrapper> placeDetail(@Query("placeid") String placeId, @QueryMap HashMap<String, String> map);
}
