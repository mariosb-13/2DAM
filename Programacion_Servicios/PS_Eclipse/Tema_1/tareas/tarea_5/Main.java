package tarea_5;

import java.io.File;
import java.io.IOException;

public class Main {

	public static void main(String[] args) {
		try {
			ProcessBuilder pb = new ProcessBuilder("java","tarea_5.Caracteres");
			pb.directory(new File("bin"));
			pb.inheritIO();
			Process p = pb.start();
			p.waitFor();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
