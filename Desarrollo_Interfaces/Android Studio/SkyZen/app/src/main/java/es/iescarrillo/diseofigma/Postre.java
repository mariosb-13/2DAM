package es.iescarrillo.diseofigma;

public class Postre {
    private String nombre;
    private String ingredientes;
    private double precio;
    private int imagen; // ID del recurso drawable (R.drawable.tu_imagen)

    // Alérgenos
    private boolean tieneGluten;
    private boolean tieneLactosa;
    private boolean tieneHuevo;
    private boolean tieneCacahuete;

    public Postre(String nombre, String ingredientes, double precio, int imagen,
                  boolean tieneGluten, boolean tieneLactosa, boolean tieneHuevo, boolean tieneCacahuete) {
        this.nombre = nombre;
        this.ingredientes = ingredientes;
        this.precio = precio;
        this.imagen = imagen;
        this.tieneGluten = tieneGluten;
        this.tieneLactosa = tieneLactosa;
        this.tieneHuevo = tieneHuevo;
        this.tieneCacahuete = tieneCacahuete;
    }

    // Getters
    public String getNombre() { return nombre; }
    public String getIngredientes() { return ingredientes; }
    public double getPrecio() { return precio; }
    public int getImagen() { return imagen; }
    public boolean isTieneGluten() { return tieneGluten; }
    public boolean isTieneLactosa() { return tieneLactosa; }
    public boolean isTieneHuevo() { return tieneHuevo; }
    public boolean isTieneCacahuete() { return tieneCacahuete; }
}