package cl.carreno.mauricio.cargaragefinder.network;

import java.util.HashMap;

import cl.carreno.mauricio.cargaragefinder.models.place.PlaceWrapper;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

public interface GarageInterface {
    @GET("json")
    Call<PlaceWrapper> garage (@Query("location") String latLng, @QueryMap HashMap<String, String> map);
}

