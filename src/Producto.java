public class Producto {

    int id;
    String nombre;
    double precio;
    int stock;

    public Producto(int idProducto, String nombreProducto, double precioProducto, int stockProducto) {
        this.id = idProducto;
        this.nombre = nombreProducto;
        this.precio = precioProducto;
        this.stock = stockProducto;
    }

    public boolean coincideId(int id) {
        return this.id == id;
    }
}
