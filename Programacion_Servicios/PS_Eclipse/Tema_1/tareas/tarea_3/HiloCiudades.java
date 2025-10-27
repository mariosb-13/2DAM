package tarea_3;

import java.util.ArrayList;

import com.google.gson.Gson;

public class HiloCiudades extends Thread {
	ArrayList<String> ciudades;

	@Override
	public void run() {
		// Hacer peticion HTTPHelper
		String url = "https://countriesnow.space/api/v0.1/countries/cities";
		String body = "{ \"country\": \"Spain\" }";
			

		// Llamar al método httpHelper
		String json=HttpHelper.postJson(url, body);
		

        // Parsear JSON con Gson
        Gson gson = new Gson();
        CiudadesResponse respuesta = gson.fromJson(json, CiudadesResponse.class);

        // Guardar los datos en el ArrayList
        ciudades = new ArrayList<>(respuesta.data);
		
		
	}
	
	

}
