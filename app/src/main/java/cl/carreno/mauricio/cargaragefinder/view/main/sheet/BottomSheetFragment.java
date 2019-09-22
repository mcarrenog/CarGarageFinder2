package cl.carreno.mauricio.cargaragefinder.view.main.sheet;


import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v4.app.Fragment;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.himangi.imagepreview.ImagePreviewActivity;
import com.himangi.imagepreview.PreviewFile;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import cl.carreno.mauricio.cargaragefinder.R;
import cl.carreno.mauricio.cargaragefinder.models.place.Garage;
import cl.carreno.mauricio.cargaragefinder.models.place.GaragePhotos;
import cl.carreno.mauricio.cargaragefinder.models.place.OpeningHours;
import cl.carreno.mauricio.cargaragefinder.models.place.PlaceDetails;
import cl.carreno.mauricio.cargaragefinder.view.main.map.BackgroundPlaceDetails;


public class BottomSheetFragment extends Fragment implements GarageReceiver.ReceiverCallback, BackgroundPlaceDetails.CallBackDetails {

    public static final String GARAGE = "cl.carreno.mauricio.cargaragefinder.view.main.sheet.BottomSheetFragment.action.GARAGE";
    public static final String GARAGE_DATA = "cl.carreno.mauricio.cargaragefinder.view.main.sheet.BottomSheetFragment.key.GARAGE";
    public static final String PHOTO_URL_BASE = "https://maps.googleapis.com/maps/api/place/photo?maxwidth=400&key=AIzaSyCZuj6y8MzY6PJ1LlOkM4eEG9qg1HTKcwY&photoreference=";
    private BottomSheetBehavior sheetBehavior;
    private IntentFilter intentFilter;
    private BroadcastReceiver broadcastReceiver;
    private ImageView photoIv;
    private TextView phoneTv;
    private ProgressDialog progressDialog;


    public BottomSheetFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_bottom_sheet, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        intentFilter = new IntentFilter();
        intentFilter.addAction(GARAGE);
        broadcastReceiver = new GarageReceiver(this);
    }

    /*You have to acces the Acitvity view by overriding onActivityCreated otherwise view is not available and it will crash*/
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        FrameLayout bottomSheet = (FrameLayout) getActivity().findViewById(R.id.bottomSheet);
        sheetBehavior = BottomSheetBehavior.from(bottomSheet);
    }

    /*We are not doing nothing with this listener, this method is here to guide you*/
    private void setBottomSheet() {
        /*Just a random thought, this is one of the few method I have seen being named Callback...*/
        sheetBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {
                if (BottomSheetBehavior.STATE_COLLAPSED == newState) {
                    //TODO do something
                } else {
                    //TODO do something else
                }
            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        LocalBroadcastManager.getInstance(getContext()).registerReceiver(broadcastReceiver, intentFilter);
    }

    @Override
    public void onPause() {
        super.onPause();
        LocalBroadcastManager.getInstance(getContext()).unregisterReceiver(broadcastReceiver);
    }

    @Override
    public void receivedGarage(Garage garage) {

        sheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
        TextView addressTv = getActivity().findViewById(R.id.addressTv2);
        TextView statusTv = getActivity().findViewById(R.id.statusTv2);
        phoneTv = getActivity().findViewById(R.id.phoneTv2);
        photoIv = getActivity().findViewById(R.id.photoIv1);
        RatingBar ratingBar = getActivity().findViewById(R.id.ratingBar);
        String statusString;
        String photoUrl;

        ArrayList<PreviewFile> arrayPreviewFile = new ArrayList<>();
        ArrayList<String> arrayPhotoReference = new ArrayList<>();

        progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("Obteniendo información");
        progressDialog.show();

        if(garage.getPhotos() != null) {

            for (GaragePhotos garagePhotos : garage.getPhotos()) {
                int count = 1;
                photoUrl = String.valueOf(garagePhotos.getPhoto_reference());
                arrayPreviewFile.add(new PreviewFile(PHOTO_URL_BASE + photoUrl, garage.getName() + "_" + count));
                arrayPhotoReference.add(PHOTO_URL_BASE + photoUrl);
                count++;
                Log.d("URL", photoUrl + " Cantidad  Fotos: " + (count - 1));

            }
        }
        else
        {
            arrayPreviewFile = new ArrayList<>();
            arrayPhotoReference = new ArrayList<>();
            arrayPreviewFile.add(new PreviewFile("https://vignette.wikia.nocookie.net/ligahispana/images/1/1f/Imagen_no_disponible.PNG/revision/latest?cb=20100727133902&path-prefix=es","Imagen No Disponible"));
            arrayPhotoReference.add("https://vignette.wikia.nocookie.net/ligahispana/images/1/1f/Imagen_no_disponible.PNG/revision/latest?cb=20100727133902&path-prefix=es");
        }
        Log.d("URL", String.valueOf(arrayPreviewFile.size()));

        OpeningHours openingHours = garage.getOpening_hours();
        Boolean isOpening;

        if(openingHours != null)
        {
            isOpening = garage.getOpening_hours().getOpen_now();
        }
        else
        {
            isOpening = null;
        }


        if (isOpening != null) {
            if (isOpening) {
                statusString = "Abierto";
            } else {
                statusString = "Cerrado";
            }
        } else {
            statusString = "No existe información";
        }


        if (arrayPreviewFile.size() > 0) {

            Picasso.get().load(arrayPhotoReference.get(0)).centerCrop().fit().into(photoIv);
            final ArrayList<PreviewFile> finalArrayPreviewFile = arrayPreviewFile;
            photoIv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(getActivity(), ImagePreviewActivity.class);
                    intent.putExtra(ImagePreviewActivity.IMAGE_LIST, finalArrayPreviewFile);
                    intent.putExtra(ImagePreviewActivity.CURRENT_ITEM, 1);
                    startActivity(intent);
                }
            });
        }

        Log.d("PLACEID",garage.getPlace_id());
        new BackgroundPlaceDetails(this).execute(garage.getPlace_id());
        addressTv.setText("Dirección: " + garage.getVicinity());
        statusTv.setText(statusString);
        ratingBar.setRating((float) garage.getRating());

       //Crashlytics.getInstance().crash();
    }

    @Override
    public void success(PlaceDetails placeDetails) {
        String phoneNumber = String.valueOf(placeDetails.getFormatted_phone_number());
        Log.d("PhoneNumber", phoneNumber);

        if (placeDetails != null) {
            if (!phoneNumber.equals("null")) {
                Log.d("PhoneNumber","Es distinto a nulo");
                phoneTv.setText(placeDetails.getFormatted_phone_number());
            }
            else {
                Log.d("PhoneNumber","Es nulo");
                phoneTv.setText("Sin Información");
            }
        }
        else
        {   phoneTv.setText("Sin Detalle");
        }
        progressDialog.dismiss();
    }

    @Override
    public void fail() {
        Log.d("URL", "Falló");
        Toast.makeText(getContext(), "Falló", Toast.LENGTH_SHORT).show();
    }
}
