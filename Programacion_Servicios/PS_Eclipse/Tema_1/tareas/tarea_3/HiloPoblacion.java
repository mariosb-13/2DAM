package tarea_3;

import com.google.gson.Gson;

public class HiloPoblacion extends Thread {
    String ciudadSeleccionada;

    public HiloPoblacion(String ciudadSeleccionada) {
        this.ciudadSeleccionada = ciudadSeleccionada;
    }

    @Override
    public void run() {
        String url = "https://countriesnow.space/api/v0.1/countries/population/cities";
        String body = "{ \"city\": \"" + ciudadSeleccionada + "\" }";

        String json = HttpHelper.postJson(url, body);
        Gson gson = new Gson();

        PoblacionGson respuesta = gson.fromJson(json, PoblacionGson.class);

        // Obtener la población más reciente
        if (respuesta.data != null && respuesta.data.populationCounts != null
                && !respuesta.data.populationCounts.isEmpty()) {
            int poblacion = respuesta.data.populationCounts.get(0).value;
            System.out.println("Población de " + ciudadSeleccionada + ": " + poblacion);
        } else {
            System.out.println("No se encontró población para " + ciudadSeleccionada);
        }
    }
}
