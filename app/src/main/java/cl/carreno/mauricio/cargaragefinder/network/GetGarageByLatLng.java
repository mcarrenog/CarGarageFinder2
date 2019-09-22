package cl.carreno.mauricio.cargaragefinder.network;

import android.os.AsyncTask;
import android.util.Log;

import com.google.android.gms.maps.model.LatLng;

import cl.carreno.mauricio.cargaragefinder.models.place.PlaceWrapper;
import retrofit2.Call;
import retrofit2.Response;

public class GetGarageByLatLng extends AsyncTask<LatLng, Void, PlaceWrapper> {


    @Override
    protected PlaceWrapper doInBackground(LatLng... latLngs) {
        GarageInterface myPlace = new GarageInterceptor().getBasic();

        LatLng latLng = latLngs[0];
        String location = latLng.latitude + "," + latLng.longitude;

        Call<PlaceWrapper> placeWrapperCall = myPlace.garage(location, GetParams.query());

        try {
            Response<PlaceWrapper> response = placeWrapperCall.execute();
            if(response.code() == 200 && response.isSuccessful()){
                return  response.body();
            }
            else{
                Log.d("Mauricio", response.message());
                return null;
            }


        } catch (Exception ex) {
            Log.d("Excepcion","Ha ocurrido una excepción: " + ex);
            return null;
        }

    }


}
