package cl.carreno.mauricio.cargaragefinder.view.main;

import android.os.Bundle;
import android.util.Log;

import com.crashlytics.android.Crashlytics;

import cl.carreno.mauricio.cargaragefinder.R;
import cl.carreno.mauricio.cargaragefinder.view.location.LocationActivity;
import cl.carreno.mauricio.cargaragefinder.view.main.map.GarageMapFragment;
import io.fabric.sdk.android.Fabric;

public class GaragesActivity extends LocationActivity implements GarageMapFragment.Callback {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_garages);
        Fabric.with(this, new Crashlytics());


    }

    @Override
    public void mapReady() {
        startLocation();
    }

    @Override
    protected void locationObtained(double latitude, double longitude) {
        GarageMapFragment mapFragment = (GarageMapFragment) getSupportFragmentManager().findFragmentById(R.id.garagesMapFragment);
        mapFragment.setMap(latitude, longitude);
        Log.d("Location","Latitud: " + latitude + " Longitud: " + longitude);
    }

}
