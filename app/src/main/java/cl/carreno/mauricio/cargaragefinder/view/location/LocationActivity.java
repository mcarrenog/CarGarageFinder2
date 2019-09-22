package cl.carreno.mauricio.cargaragefinder.view.location;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.location.Location;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;

public abstract class LocationActivity extends AppCompatActivity {

    private static final int RC_GEOLOCATION = 123;
    private FusedLocationProviderClient mFusedLocationClient;


    @SuppressLint("NewApi")
    protected void startLocation() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            String[] permissions = {Manifest.permission.ACCESS_FINE_LOCATION};
            requestPermissions(permissions, RC_GEOLOCATION);
        } else {
            mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
            final LocationRequest locationRequest = new LocationRequest();

            locationRequest.setInterval(3000);
            locationRequest.setFastestInterval(1500);
            locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
            locationRequest.setNumUpdates(1);
            LocationCallback locationCallback = new LocationCallback() {
                @Override
                public void onLocationResult(LocationResult locationResult) {
                    super.onLocationResult(locationResult);
                    if (locationResult == null) {
                        return;
                    }

                    Location location = locationResult.getLastLocation();
                    locationObtained(location.getLatitude(), location.getLongitude());
                }

            };
            mFusedLocationClient.requestLocationUpdates(locationRequest, locationCallback, null);
        }
    }

    @SuppressLint("NewApi")
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (RESULT_OK == requestCode && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            startLocation();
        } else {
            requestPermissions(permissions, RC_GEOLOCATION);
        }
    }

    protected abstract void locationObtained(double latitude, double longitude);
}
