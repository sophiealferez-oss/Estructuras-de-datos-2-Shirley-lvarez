import java.util.ArrayList;
import java.util.List;

/**
 * Pruebas automáticas del reto. Se ejecutan con: java Pruebas
 * Cubren registro, duplicados, búsqueda (con comparaciones), eliminación en sus tres casos,
 * Inorden, menor/mayor, árbol vacío, validación de datos e inserción aleatoria vs ordenada.
 *
 * Árbol inicial (catálogo de la Guía 6):
 *                 50
 *              /      \
 *            30        70
 *           /  \      /  \
 *         20    40   60   80
 *        /     /       \
 *      10    35         65
 */
public class Pruebas {

    private static int pruebasOk = 0;
    private static int pruebasTotal = 0;

    public static void main(String[] args) {
        System.out.println("=== Carga y registro ===");
        ArbolBST arbol = Catalogo.crearInventario();
        probar("Se cargan los 10 productos", arbol.getTamano(), 10);
        probar("Altura del catálogo = 4 niveles", arbol.altura(), 4);
        probar("Inorden ordenado por código", codigos(arbol.inorden()),
                List.of(10, 20, 30, 35, 40, 50, 60, 65, 70, 80));
        probar("Registrar código nuevo (25)", arbol.insertar(new Producto(25, "Cargador USB-C", 32000, 20)), true);
        probar("Tamaño sube a 11", arbol.getTamano(), 11);
        probar("Código duplicado (50) se rechaza", arbol.insertar(new Producto(50, "Teclado copia", 1, 1)), false);
        probar("Duplicado no cambia el tamaño", arbol.getTamano(), 11);
        probar("Duplicado no reemplaza al original", arbol.buscar(50).getProducto().getNombre(), "Teclado");

        System.out.println("\n=== Búsqueda y comparaciones ===");
        arbol = Catalogo.crearInventario();
        ResultadoBusqueda r = arbol.buscar(65);
        probar("Buscar 65 lo encuentra (Router)", r.getProducto().getNombre(), "Router");
        probar("Buscar 65 hace 4 comparaciones", r.getComparaciones(), 4);
        probar("Camino de 65 = 50 -> 70 -> 60 -> 65", r.getCamino(), List.of(50, 70, 60, 65));
        probar("Buscar la raíz (50) hace 1 comparación", arbol.buscar(50).getComparaciones(), 1);
        r = arbol.buscar(66);
        probar("Buscar 66 (inexistente) no lo encuentra", r.isEncontrado(), false);
        probar("Buscar 66 hace 4 comparaciones", r.getComparaciones(), 4);
        probar("Buscar 1 (inexistente) recorre 50 -> 30 -> 20 -> 10", arbol.buscar(1).getCamino(), List.of(50, 30, 20, 10));
        probar("Ninguna búsqueda supera la altura", maxComparaciones(arbol) <= arbol.altura(), true);

        System.out.println("\n=== Menor y mayor código ===");
        probar("Menor código = 10 (USB 32GB)", arbol.menor().getCodigo(), 10);
        probar("Mayor código = 80 (Impresora)", arbol.mayor().getCodigo(), 80);

        System.out.println("\n=== Eliminación ===");
        probar("Eliminar hoja (10) devuelve USB 32GB", arbol.eliminar(10).getNombre(), "USB 32GB");
        probar("10 ya no existe", arbol.contiene(10), false);
        probar("Nuevo menor código = 20", arbol.menor().getCodigo(), 20);
        probar("Eliminar nodo con un hijo (60)", arbol.eliminar(60).getCodigo(), 60);
        probar("Su hijo 65 sube: camino 50 -> 70 -> 65", arbol.buscar(65).getCamino(), List.of(50, 70, 65));
        probar("Eliminar nodo con dos hijos (30)", arbol.eliminar(30).getCodigo(), 30);
        probar("El sucesor 35 ocupa su lugar: camino 50 -> 35", arbol.buscar(35).getCamino(), List.of(50, 35));
        probar("Eliminar la raíz (50)", arbol.eliminar(50).getCodigo(), 50);
        probar("La nueva raíz es el sucesor 65", arbol.getRaiz().getDato().getCodigo(), 65);
        probar("Eliminar inexistente (99) devuelve null", arbol.eliminar(99), null);
        probar("Tamaño final = 6", arbol.getTamano(), 6);
        probar("Inorden sigue ordenado tras eliminar", codigos(arbol.inorden()), List.of(20, 35, 40, 65, 70, 80));
        probar("Se puede volver a registrar un código eliminado", arbol.insertar(new Producto(50, "Teclado", 85000, 14)), true);

        System.out.println("\n=== Árbol vacío ===");
        ArbolBST vacio = new ArbolBST();
        probar("Árbol vacío: buscar hace 0 comparaciones", vacio.buscar(10).getComparaciones(), 0);
        probar("Árbol vacío: menor es null", vacio.menor(), null);
        probar("Árbol vacío: mayor es null", vacio.mayor(), null);
        probar("Árbol vacío: eliminar devuelve null", vacio.eliminar(10), null);
        probar("Árbol vacío: Inorden vacío", vacio.inorden(), List.of());
        probar("Árbol vacío: altura 0", vacio.altura(), 0);
        ArbolBST todo = Catalogo.crearInventario();
        for (Producto p : Catalogo.productosTienda()) todo.eliminar(p.getCodigo());
        probar("Eliminar los 10 deja el árbol vacío", todo.estaVacio() && todo.getTamano() == 0, true);

        System.out.println("\n=== Validación de datos del producto ===");
        probar("Código 0 se rechaza", lanzaError(() -> new Producto(0, "X", 100, 1)), true);
        probar("Código negativo se rechaza", lanzaError(() -> new Producto(-5, "X", 100, 1)), true);
        probar("Nombre vacío se rechaza", lanzaError(() -> new Producto(5, "   ", 100, 1)), true);
        probar("Precio negativo se rechaza", lanzaError(() -> new Producto(5, "X", -1, 1)), true);
        probar("Cantidad negativa se rechaza", lanzaError(() -> new Producto(5, "X", 100, -1)), true);
        probar("Cantidad 0 sí es válida (agotado)", lanzaError(() -> new Producto(5, "X", 100, 0)), false);

        System.out.println("\n=== Inserción aleatoria vs ordenada (n = 1000) ===");
        List<Experimento.Resultado> resultados = Experimento.compararInsercion(1000, 42);
        for (Experimento.Resultado e : resultados) System.out.println("  " + e);
        Experimento.Resultado aleatoria = resultados.get(0);
        Experimento.Resultado ordenada = resultados.get(1);
        probar("Ordenada degenera en lista: altura = n", ordenada.altura(), 1000);
        probar("Ordenada: promedio de comparaciones = (n+1)/2", ordenada.promedioComparaciones(), 500.5);
        probar("Aleatoria: altura mucho menor que n (< 50)", aleatoria.altura() < 50, true);
        probar("Aleatoria: promedio < 20 comparaciones", aleatoria.promedioComparaciones() < 20, true);

        System.out.println("\nResultado: " + pruebasOk + "/" + pruebasTotal + " pruebas correctas");
    }

    private static List<Integer> codigos(List<Producto> lista) {
        List<Integer> out = new ArrayList<>();
        for (Producto p : lista) out.add(p.getCodigo());
        return out;
    }

    private static int maxComparaciones(ArbolBST arbol) {
        int max = 0;
        for (int codigo = 0; codigo <= 90; codigo++) {
            max = Math.max(max, arbol.buscar(codigo).getComparaciones());
        }
        return max;
    }

    private static boolean lanzaError(Runnable accion) {
        try {
            accion.run();
            return false;
        } catch (IllegalArgumentException e) {
            return true;
        }
    }

    private static void probar(String nombre, Object obtenido, Object esperado) {
        pruebasTotal++;
        boolean ok = obtenido == null ? esperado == null : obtenido.equals(esperado);
        if (ok) pruebasOk++;
        System.out.println((ok ? "[OK]    " : "[FALLO] ") + nombre
                + (ok ? "" : " -> obtenido " + obtenido + ", esperado " + esperado));
    }
}
