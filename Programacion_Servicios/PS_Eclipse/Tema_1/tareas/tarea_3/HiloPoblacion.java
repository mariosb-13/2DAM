package tarea_3;

import java.util.ArrayList;

public class HiloPoblacion extends Thread {
	ArrayList<String> pueblos;

	@Override
	public void run() {

		String url = "https://countriesnow.space/api/v0.1/countries/cities";
		String body = "{ \"city\": \"Spain\" }";
		
		
		HttpHelper.postJson(url, body);
		
	}

}
