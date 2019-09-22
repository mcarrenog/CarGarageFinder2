package cl.carreno.mauricio.cargaragefinder.view.main.map;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import cl.carreno.mauricio.cargaragefinder.R;
import cl.carreno.mauricio.cargaragefinder.models.place.Coordinates;
import cl.carreno.mauricio.cargaragefinder.models.place.Garage;
import cl.carreno.mauricio.cargaragefinder.view.main.sheet.BottomSheetFragment;

public class GarageMapFragment extends SupportMapFragment implements OnMapReadyCallback, BackgroundGarages.Callback, GoogleMap.OnMarkerClickListener {

    private Callback callback;
    private Marker marker;
    private ProgressDialog progressDialog;
    private static final LatLng SANTIAGO = new LatLng(-33.4532908,-70.714213);


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        callback = (Callback) context;
    }

    protected GoogleMap googleMap;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getMapAsync(this);

        progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("Buscando Talleres Cercanos");
        progressDialog.show();
    }

    @SuppressLint("MissingPermission")
    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.googleMap = googleMap;
        googleMap.setMyLocationEnabled(true);
        googleMap.setOnMarkerClickListener(this);
        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(SANTIAGO)
                .zoom(20)
                .build();
        googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));

        callback.mapReady();
    }

    public void setMap(double latitude, double longitude) {
        LatLng latLng = new LatLng(latitude,longitude);
        googleMap.moveCamera(
                CameraUpdateFactory
                        .newLatLngZoom(latLng, 14));
        new BackgroundGarages(this).execute(latLng);
    }

    @Override
    public void success(Garage[] garages) {
        Log.d("Prueba","LLegué" + garages.length);
        progressDialog.dismiss();
        Coordinates coordinates;

        for (Garage garage : garages) {
            coordinates = garage.getGeometry().getLocation();
            LatLng latLng = new LatLng(coordinates.getLat(), coordinates.getLng());

            googleMap.addMarker(new MarkerOptions().
                    position(latLng)
                    .title(garage.getName())
                    .icon(bitmapDescriptorFromVector(getContext(), R.drawable.ic_map_marker))
            ).setTag(garage);
        }
    }

    @Override
    public void fail() {
        progressDialog.dismiss();
        Toast.makeText(getContext(), "No se han encontrado talleres cercanos :(", Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onMarkerClick(Marker marker) {

        Garage garage = (Garage) marker.getTag();
        Intent intent = new Intent(BottomSheetFragment.GARAGE);
        intent.putExtra(BottomSheetFragment.GARAGE_DATA, garage);
        LocalBroadcastManager.getInstance(getContext()).sendBroadcast(intent);
        Log.d("Garage", String.valueOf(garage.getName()));
        return false;
    }

    public interface Callback {
        void mapReady();
    }

    private BitmapDescriptor bitmapDescriptorFromVector(Context context, @DrawableRes int vectorDrawableResourceId) {
        Drawable background = ContextCompat.getDrawable(context, R.drawable.ic_map_marker);
        background.setBounds(0, 0, background.getIntrinsicWidth(), background.getIntrinsicHeight());
        Drawable vectorDrawable = ContextCompat.getDrawable(context, vectorDrawableResourceId);
        vectorDrawable.setBounds(0, 0, vectorDrawable.getIntrinsicWidth(), vectorDrawable.getIntrinsicHeight());
        Bitmap bitmap = Bitmap.createBitmap(background.getIntrinsicWidth(), background.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        background.draw(canvas);
        vectorDrawable.draw(canvas);
        return BitmapDescriptorFactory.fromBitmap(bitmap);
    }

}
