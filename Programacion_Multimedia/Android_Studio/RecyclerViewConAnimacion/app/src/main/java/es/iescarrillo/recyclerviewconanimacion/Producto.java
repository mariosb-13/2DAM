package es.iescarrillo.recyclerviewconanimacion;

public class Producto {
    private String name;
    private double price;
    private int image;
    private boolean glutenFree;

    public Producto(String name, double price, int imageUrl, boolean glutenFree) {
        this.name = name;
        this.price = price;
        this.image = imageUrl;
        this.glutenFree = glutenFree;
    }

    public String getName() { return name; }
    public double getPrice() { return price; }
    public int getImageUrl() { return image; }
    public boolean isGlutenFree() { return glutenFree; }
}
