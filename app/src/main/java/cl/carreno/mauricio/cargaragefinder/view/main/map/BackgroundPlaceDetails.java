package cl.carreno.mauricio.cargaragefinder.view.main.map;

import android.util.Log;

import cl.carreno.mauricio.cargaragefinder.models.place.PlaceDetails;
import cl.carreno.mauricio.cargaragefinder.models.place.PlaceDetailsWrapper;
import cl.carreno.mauricio.cargaragefinder.network.GetPlaceDetailsByPlaceId;

public class BackgroundPlaceDetails extends GetPlaceDetailsByPlaceId {

    private CallBackDetails callBackDetails;
    private PlaceDetails placeDetails;

    public BackgroundPlaceDetails(CallBackDetails callBackDetails) {
        this.callBackDetails = callBackDetails;
    }

    @Override
    protected void onPostExecute(PlaceDetailsWrapper placeDetailsWrapper) {

        if (placeDetailsWrapper != null) {

            placeDetails = placeDetailsWrapper.getResult();

            if (placeDetails != null) {
                Log.d("ola", "ola " + placeDetails.getFormatted_phone_number());
                callBackDetails.success(placeDetails);

            }
            else
            {
                Log.d("ola","Viene vacío");
            }
        } else {
            Log.d("Fail", "Respuesta NOK");
            callBackDetails.fail();

        }
    }

    public interface CallBackDetails {
        void success(PlaceDetails placeDetails);

        void fail();
    }

}
