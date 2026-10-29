package es.iesisidra.datastock.modelo;

/**
 * Clase Producto: Representa un artículo en el inventario de DataStock.
 * Incluye atributos para su gestión y métodos getters/setters con validación.
 */
public class Producto {

    private String id;
    private String nombre;
    private double precio;
    private int stock;
    private String categoria;

    public Producto() {
    }

    public Producto(String id, String nombre, double precio, int stock, String categoria) {
        this.id = id;
        this.nombre = nombre;
        setPrecio(precio);
        setStock(stock);
        this.categoria = categoria;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public double getPrecio() { return precio; }
    public void setPrecio(double precio) {
        // Control de errores: No permitimos precios negativos
        this.precio = (precio >= 0) ? precio : 0.0;
    }

    public int getStock() { return stock; }
    public void setStock(int stock) {
        // Control de errores: El stock no puede ser negativo
        this.stock = (stock >= 0) ? stock : 0;
    }

    public String getCategoria() { return categoria; }
    public void setCategoria(String categoria) { this.categoria = categoria; }

    @Override
    public String toString() {
        return String.format("Producto [ID: %s | Nombre: %s | Precio: %.2f€ | Stock: %d | Cat: %s]",
                id, nombre, precio, stock, (categoria != null ? categoria : "Sin categoría"));
    }
}
