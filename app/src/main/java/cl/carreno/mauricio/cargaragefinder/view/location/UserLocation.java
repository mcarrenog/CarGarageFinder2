package cl.carreno.mauricio.cargaragefinder.view.location;

import android.location.Location;

import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationResult;

public class UserLocation extends LocationCallback {

    private Callback callback;

    public UserLocation(Callback callback) {
        this.callback = callback;
    }

    @Override
    public void onLocationResult(LocationResult locationResult) {
        super.onLocationResult(locationResult);
        if (locationResult != null) {
            Location location = locationResult.getLocations().get(0);
            callback.located(location.getLatitude(), location.getLongitude());
        }
    }

    public interface Callback {
        void located(double latitude, double longitude);
    }
}
