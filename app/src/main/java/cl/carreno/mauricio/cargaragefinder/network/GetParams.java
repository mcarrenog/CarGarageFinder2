package cl.carreno.mauricio.cargaragefinder.network;

import java.util.HashMap;

public class GetParams {

    public static HashMap<String, String> query() {
        HashMap<String, String> map = new HashMap<>();
        map.put("radius", "500");
        map.put("type", "car_repair");
        map.put("key", "AIzaSyCZuj6y8MzY6PJ1LlOkM4eEG9qg1HTKcwY");
        return map;
    }

    public static HashMap<String,String> placeDetailsQuery()
    {
        HashMap<String,String> map = new HashMap<>();
        map.put("fields","name,url,formatted_phone_number");
        map.put("key", "AIzaSyCZuj6y8MzY6PJ1LlOkM4eEG9qg1HTKcwY");
        return map;
    }

}
