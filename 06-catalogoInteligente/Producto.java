/**
 * Producto del catálogo de una tienda de tecnología.
 * Se ordena por código (SKU), que es la clave del árbol.
 */
public class Producto implements Comparable<Producto> {
    private final int codigo;
    private final String nombre;
    private final int precio;   // en pesos colombianos (COP)
    private final int stock;    // unidades disponibles

    public Producto(int codigo, String nombre, int precio, int stock) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.precio = precio;
        this.stock = stock;
    }

    public int getCodigo() { return codigo; }
    public String getNombre() { return nombre; }
    public int getPrecio() { return precio; }
    public int getStock() { return stock; }

    @Override
    public int compareTo(Producto otro) {
        return Integer.compare(this.codigo, otro.codigo);
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof Producto p && p.codigo == this.codigo;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(codigo);
    }

    @Override
    public String toString() {
        return nombre + "(" + codigo + ")";
    }
}
