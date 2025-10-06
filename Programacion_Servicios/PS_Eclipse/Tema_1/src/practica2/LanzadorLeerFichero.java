package practica2;

public class LanzadorLeerFichero {

    public void lanzar() {
        try {
            // Comando para ejecutar la clase que lee el fichero
            ProcessBuilder pb = new ProcessBuilder(
                "java",
                "-cp", "bin", // carpeta donde están las clases compiladas
                "practica2.LeerFichero"
            );

            pb.inheritIO(); // HEREDA la consola, la salida se ve directamente

            Process proceso = pb.start();
            proceso.waitFor(); // espera a que termine

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        LanzadorLeerFichero lanzador = new LanzadorLeerFichero();
        lanzador.lanzar();
    }
}
