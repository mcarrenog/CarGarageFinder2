package cl.carreno.mauricio.cargaragefinder.view.main.sheet;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import cl.carreno.mauricio.cargaragefinder.models.place.Garage;

class GarageReceiver extends BroadcastReceiver {

    private ReceiverCallback callback;

    public GarageReceiver(ReceiverCallback callback) {
        this.callback = callback;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent != null) {
            String action = intent.getAction();
            if (BottomSheetFragment.GARAGE.equals(action)) {
                Garage garage = (Garage) intent.getSerializableExtra(BottomSheetFragment.GARAGE_DATA);
                callback.receivedGarage(garage);
            }
        }
    }

    interface ReceiverCallback {

        void receivedGarage(Garage garage);

    }
}
