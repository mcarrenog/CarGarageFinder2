package cl.carreno.mauricio.cargaragefinder.network;

import android.os.AsyncTask;
import android.util.Log;

import cl.carreno.mauricio.cargaragefinder.models.place.PlaceDetailsWrapper;
import retrofit2.Call;
import retrofit2.Response;

public class GetPlaceDetailsByPlaceId extends AsyncTask<String, Void, PlaceDetailsWrapper> {

    @Override
    protected PlaceDetailsWrapper doInBackground(String... strings) {

        PlaceDetailsInterface details = new GarageInterceptor().getPlaceDetails();
        String placeId = strings[0];
        Log.d("ola", placeId);

        Call<PlaceDetailsWrapper> placeCall = details.placeDetail(placeId, GetParams.placeDetailsQuery());

        try {
            Response<PlaceDetailsWrapper> response = placeCall.execute();
            if(response.code() == 200 && response.isSuccessful()){
                Log.d("ola", "Respuesta: " + response.body().getStatus());
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
