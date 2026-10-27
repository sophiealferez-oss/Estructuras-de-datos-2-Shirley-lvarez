import java.util.List;

/**
 * Catálogo inicial de la tienda de tecnología (el mismo de la Guía 6).
 * El orden de la lista es el orden de inserción y define la forma del árbol:
 *
 *                 Teclado(50)
 *               /             \
 *         Mouse(30)            Monitor(70)
 *         /       \            /         \
 *  Cable HDMI(20) Audífonos(40) Webcam(60) Impresora(80)
 *       /             /              \
 *  USB 32GB(10)  Parlante(35)      Router(65)
 */
public final class Catalogo {

    private Catalogo() { }

    public static List<Producto> productosTienda() {
        return List.of(
                new Producto(50, "Teclado", 85000, 14),
                new Producto(30, "Mouse", 45000, 30),
                new Producto(70, "Monitor", 620000, 6),
                new Producto(20, "Cable HDMI", 18000, 50),
                new Producto(40, "Audífonos", 120000, 18),
                new Producto(60, "Webcam", 95000, 11),
                new Producto(80, "Impresora", 480000, 4),
                new Producto(10, "USB 32GB", 25000, 40),
                new Producto(35, "Parlante", 150000, 9),
                new Producto(65, "Router", 130000, 12));
    }

    /** Crea un inventario (BST) con los 10 productos del catálogo ya cargados. */
    public static ArbolBST crearInventario() {
        ArbolBST arbol = new ArbolBST();
        for (Producto p : productosTienda()) arbol.insertar(p);
        return arbol;
    }
}
