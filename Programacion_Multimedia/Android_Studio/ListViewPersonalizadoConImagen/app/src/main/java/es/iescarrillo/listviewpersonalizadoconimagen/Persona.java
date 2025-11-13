package es.iescarrillo.listviewpersonalizadoconimagen;



public class Persona {
    private String nombre;
    private int edad;
    private int imagenResId; // referencia al recurso drawable

    public Persona(String nombre, int edad, int imagenResId) {
        this.nombre = nombre;
        this.edad = edad;
        this.imagenResId = imagenResId;
    }

    public String getNombre() {
        return nombre;
    }

    public int getEdad() {
        return edad;
    }

    public int getImagenResId() {
        return imagenResId;
    }
}

