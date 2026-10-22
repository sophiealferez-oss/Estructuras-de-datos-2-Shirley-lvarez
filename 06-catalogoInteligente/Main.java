import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Guía 6 - Recorridos en árboles binarios.
 * Situación real: catálogo de una tienda de tecnología organizado en un BST por código (SKU).
 * Construye el árbol con 10 productos, ejecuta los cuatro recorridos,
 * compara Inorden con una lista ordenada y prueba la eliminación segura.
 */
public class Main {

    private static int pruebasOk = 0;
    private static int pruebasTotal = 0;

    public static void main(String[] args) {
        // Orden de inserción (define la forma del árbol)
        List<Producto> productos = List.of(
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

        ArbolBST<Producto> arbol = new ArbolBST<>();
        for (Producto p : productos) arbol.insertar(p);

        System.out.println("=== Catálogo de la tienda (orden de inserción) ===");
        for (Producto p : productos) {
            System.out.printf("Código %d | %-10s | $%,d COP | stock %d%n",
                    p.getCodigo(), p.getNombre(), p.getPrecio(), p.getStock());
        }

        System.out.println("\n=== Recorridos ===");
        System.out.print("Preorden   : "); arbol.preorden(arbol.getRaiz());   System.out.println();
        System.out.print("Inorden    : "); arbol.inorden(arbol.getRaiz());    System.out.println();
        System.out.print("Postorden  : "); arbol.postorden(arbol.getRaiz());  System.out.println();
        System.out.print("Por niveles: "); arbol.porNiveles(arbol.getRaiz()); System.out.println();

        System.out.println("\n=== Reporte de inventario (Inorden = ordenado por código) ===");
        long valorTotal = 0;
        for (Producto p : arbol.inordenLista()) {
            long subtotal = (long) p.getPrecio() * p.getStock();
            valorTotal += subtotal;
            System.out.printf("%3d | %-10s | %,9d x %2d = $%,d%n",
                    p.getCodigo(), p.getNombre(), p.getPrecio(), p.getStock(), subtotal);
        }
        System.out.printf("Valor total del inventario: $%,d COP%n", valorTotal);

        System.out.println("\n=== Pruebas ===");
        probar("Preorden correcto", codigos(arbol.preordenLista()),
                List.of(50, 30, 20, 10, 40, 35, 70, 60, 65, 80));
        probar("Inorden correcto", codigos(arbol.inordenLista()),
                List.of(10, 20, 30, 35, 40, 50, 60, 65, 70, 80));
        probar("Postorden correcto", codigos(arbol.postordenLista()),
                List.of(10, 20, 35, 40, 30, 65, 60, 80, 70, 50));
        probar("Por niveles correcto", codigos(arbol.porNivelesLista()),
                List.of(50, 30, 70, 20, 40, 60, 80, 10, 35, 65));

        // Comparar Inorden con una lista ordenada
        List<Producto> ordenada = new ArrayList<>(productos);
        Collections.sort(ordenada);
        probar("Inorden == lista ordenada", arbol.inordenLista(), ordenada);

        // Los recorridos visitan los mismos 10 nodos
        probar("Preorden visita 10 nodos", arbol.preordenLista().size(), 10);
        probar("Por niveles visita 10 nodos", arbol.porNivelesLista().size(), 10);

        // Duplicados: un código repetido no se inserta dos veces
        arbol.insertar(new Producto(50, "Teclado repetido", 1, 1));
        probar("Código duplicado se ignora", arbol.inordenLista().size(), 10);

        // Árbol vacío: no debe lanzar NullPointerException
        ArbolBST<Producto> vacio = new ArbolBST<>();
        probar("Árbol vacío (preorden)", vacio.preordenLista(), List.of());
        probar("Árbol vacío (por niveles)", vacio.porNivelesLista(), List.of());

        // Un solo nodo
        ArbolBST<Producto> uno = new ArbolBST<>();
        uno.insertar(new Producto(99, "Mousepad", 20000, 25));
        probar("Un solo nodo (postorden)", codigos(uno.postordenLista()), List.of(99));

        // Eliminación segura con postorden
        List<Producto> eliminados = arbol.eliminarTodo();
        System.out.println("\nEliminación (orden de liberación): " + eliminados);
        probar("Eliminación sigue postorden", codigos(eliminados),
                List.of(10, 20, 35, 40, 30, 65, 60, 80, 70, 50));
        probar("Árbol queda vacío tras eliminar", arbol.getRaiz() == null, true);

        System.out.println("\nResultado: " + pruebasOk + "/" + pruebasTotal + " pruebas correctas");
    }

    /** Convierte una lista de productos en su lista de códigos, para comparar secuencias. */
    private static List<Integer> codigos(List<Producto> lista) {
        List<Integer> out = new ArrayList<>();
        for (Producto p : lista) out.add(p.getCodigo());
        return out;
    }

    private static void probar(String nombre, Object obtenido, Object esperado) {
        pruebasTotal++;
        boolean ok = obtenido.equals(esperado);
        if (ok) pruebasOk++;
        System.out.println((ok ? "[OK]    " : "[FALLO] ") + nombre
                + (ok ? "" : " -> obtenido " + obtenido + ", esperado " + esperado));
    }
}
