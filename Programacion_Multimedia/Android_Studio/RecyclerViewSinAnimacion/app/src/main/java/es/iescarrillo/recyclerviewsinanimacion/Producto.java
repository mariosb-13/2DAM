package es.iescarrillo.recyclerviewsinanimacion;

public class Producto {
    private final String name;
    private final double price;
    private final boolean glutenFree;
    private final int imageResId;

    public Producto(String name, double price, boolean glutenFree, int imageResId) {
        this.name = name;
        this.price = price;
        this.glutenFree = glutenFree;
        this.imageResId = imageResId;
    }

    public String getName() { return name; }
    public double getPrice() { return price; }
    public boolean isGlutenFree() { return glutenFree; }
    public int getImageResId() { return imageResId; }
}

