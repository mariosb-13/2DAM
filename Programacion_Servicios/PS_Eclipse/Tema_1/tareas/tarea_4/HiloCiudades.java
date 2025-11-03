package tarea_4;

import java.util.ArrayList;
import java.util.Collections;

import com.google.gson.Gson;

public class HiloCiudades extends Thread {
    private ArrayList<String> ciudadesMostradas;

    public ArrayList<String> getCiudadesMostradas() {
        return ciudadesMostradas;
    }

    @Override
    public void run() {
        // Lista de ciudades válidas para la API de población
        String[] ciudadesValidas = {
            "Madrid","Barcelona","Valencia","Sevilla","Zaragoza",
            "Málaga","Murcia","Palma","Bilbao","Alicante"
        };

        String url = "https://countriesnow.space/api/v0.1/countries/cities";
        String body = "{ \"country\": \"Spain\" }";

        String json = HttpHelper.postJson(url, body);
        Gson gson = new Gson();
        CiudadesGson respuesta = gson.fromJson(json, CiudadesGson.class);

        ArrayList<String> ciudades = new ArrayList<>();
        for (String ciudad : respuesta.data) {
            for (String valida : ciudadesValidas) {
                if (ciudad.equalsIgnoreCase(valida)) {
                    ciudades.add(ciudad);
                }
            }
        }

        Collections.shuffle(ciudades);
        // Solo queremos 5
        ciudadesMostradas = new ArrayList<>(ciudades.subList(0, Math.min(5, ciudades.size())));

        System.out.println("Se seleccionaron las siguientes ciudades:");
        for (int i = 0; i < ciudadesMostradas.size(); i++) {
            System.out.println((i + 1) + ". " + ciudadesMostradas.get(i));
        }
    }
}
