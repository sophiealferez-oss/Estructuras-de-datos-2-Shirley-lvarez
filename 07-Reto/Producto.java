/**
 * Producto del inventario de la tienda de tecnología.
 * El código (SKU) es la clave del BST: no se repite y define la posición del nodo.
 */
public class Producto {
    private final int codigo;
    private final String nombre;
    private final int precio;    // en pesos colombianos (COP)
    private final int cantidad;  // unidades en stock

    public Producto(int codigo, String nombre, int precio, int cantidad) {
        if (codigo <= 0) throw new IllegalArgumentException("El código debe ser mayor que 0");
        if (nombre == null || nombre.isBlank()) throw new IllegalArgumentException("El nombre no puede estar vacío");
        if (precio < 0) throw new IllegalArgumentException("El precio no puede ser negativo");
        if (cantidad < 0) throw new IllegalArgumentException("La cantidad no puede ser negativa");
        this.codigo = codigo;
        this.nombre = nombre.trim();
        this.precio = precio;
        this.cantidad = cantidad;
    }

    public int getCodigo() { return codigo; }
    public String getNombre() { return nombre; }
    public int getPrecio() { return precio; }
    public int getCantidad() { return cantidad; }

    @Override
    public String toString() {
        return String.format("%d - %s - $%,d COP - stock: %d", codigo, nombre, precio, cantidad);
    }
}
