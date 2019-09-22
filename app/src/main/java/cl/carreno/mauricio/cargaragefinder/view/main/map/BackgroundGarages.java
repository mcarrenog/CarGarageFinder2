package cl.carreno.mauricio.cargaragefinder.view.main.map;

import cl.carreno.mauricio.cargaragefinder.models.place.Garage;
import cl.carreno.mauricio.cargaragefinder.models.place.PlaceWrapper;
import cl.carreno.mauricio.cargaragefinder.network.GetGarageByLatLng;

class BackgroundGarages extends GetGarageByLatLng {

    private Callback callback;

    public BackgroundGarages(Callback callback) {
        this.callback = callback;
    }

    @Override
    protected void onPostExecute(PlaceWrapper placeWrapper) {
        if (placeWrapper != null) {
            Garage[] garages = placeWrapper.getResults();
            callback.success(garages);
        } else {
            callback.fail();
        }

    }


    public interface Callback {
        void success(Garage[] garages);
        void fail();
    }
}
