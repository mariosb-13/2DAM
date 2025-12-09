package es.iescarrillo.diseofigma;

public class Postre {
    private String nombre;
    private String ingredientes;
    private double precio;
    private int imagenResId;

    // Variables para los alérgenos de tus iconos
    private boolean tieneGluten;
    private boolean tieneLactosa;
    private boolean tieneHuevo;
    private boolean tieneCacahuete;

    public Postre(String nombre, String ingredientes, double precio, int imagenResId,
                  boolean tieneGluten, boolean tieneLactosa, boolean tieneHuevo, boolean tieneCacahuete) {
        this.nombre = nombre;
        this.ingredientes = ingredientes;
        this.precio = precio;
        this.imagenResId = imagenResId;
        this.tieneGluten = tieneGluten;
        this.tieneLactosa = tieneLactosa;
        this.tieneHuevo = tieneHuevo;
        this.tieneCacahuete = tieneCacahuete;
    }

    // Getters
    public String getNombre() { return nombre; }
    public String getIngredientes() { return ingredientes; }
    public double getPrecio() { return precio; }
    public int getImagenResId() { return imagenResId; }

    public boolean isTieneGluten() { return tieneGluten; }
    public boolean isTieneLactosa() { return tieneLactosa; }
    public boolean isTieneHuevo() { return tieneHuevo; }
    public boolean isTieneCacahuete() { return tieneCacahuete; }
}