package practica1;

import java.io.File;

public class LanzadorChrome {

public void LanzarProceso(String[] comando) {

        try {
            ProcessBuilder pb= new ProcessBuilder(comando);
            pb.directory(new File("C:\\"));
            pb.start();
        }catch(Exception e) {
                e.printStackTrace();
        }finally {

        }
    }

    public static void main(String[] args) {
        try {
                LanzadorChrome l1= new LanzadorChrome();
                l1.LanzarProceso(args);

        }catch(Exception e) {
            e.printStackTrace();
        }
    }


}