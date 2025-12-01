package tarea3;

public class ITV2 {
    public static void main(String[] args) {
        //Semáforo con 3 permisos (3 líneas de inspección)
        LineaInspeccion linea = new LineaInspeccion(3);
        
        //Cola de 64 vehículos
        Thread[] cola = new Thread[64];

        // Crear los vehículos
        for (int i = 0; i < cola.length; i++) {
            // CAMBIO 3: Ahora elegimos entre 4 tipos (0, 1, 2, 3)
            int tipo = (int)(Math.random() * 4); 
            
            if (tipo == 0) {
                cola[i] = new Coche(linea);
            } else if (tipo == 1) {
                cola[i] = new Motocicleta(linea);
            } else if (tipo == 2) {
                cola[i] = new Camion(linea);
            } else {
                // Nuevo tipo de vehículo
                cola[i] = new VehiculoAgricola(linea);
            }
        }

        // Iniciar todos los hilos
        for (Thread vehiculo : cola) {
            vehiculo.start();
        }

        // Esperar a que terminen
        for (Thread vehiculo : cola) {
            try {
                vehiculo.join();
            } catch (Exception e) {}
        }

        System.out.println("--- SIMULACIÓN AMPLIADA FINALIZADA ---");
    }
}