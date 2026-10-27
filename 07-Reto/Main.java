import java.util.List;
import java.util.Scanner;

/**
 * Guía 7 - Reto integrador BST: sistema de inventario jerárquico.
 * Menú de consola sobre el catálogo de la tienda de tecnología (Guía 6),
 * organizado en un BST cuya clave es el código del producto.
 */
public class Main {

    private static final Scanner entrada = new Scanner(System.in);
    private static final ArbolBST inventario = Catalogo.crearInventario();

    public static void main(String[] args) {
        System.out.println("=== Inventario de la tienda de tecnología (BST por código) ===");
        System.out.println("Se cargaron " + inventario.getTamano() + " productos del catálogo.");

        while (true) {
            mostrarMenu();
            Integer opcion = leerEntero("Opción: ", 0, 6);
            if (opcion == null || opcion == 0) break;
            switch (opcion) {
                case 1 -> registrar();
                case 2 -> buscar();
                case 3 -> eliminar();
                case 4 -> listarInorden();
                case 5 -> menorYMayor();
                case 6 -> pruebaInsercion();
            }
        }
        System.out.println("Hasta pronto.");
    }

    private static void mostrarMenu() {
        System.out.println();
        System.out.println("1. Registrar producto");
        System.out.println("2. Buscar producto");
        System.out.println("3. Eliminar producto");
        System.out.println("4. Listar inventario (Inorden)");
        System.out.println("5. Producto de menor y mayor código");
        System.out.println("6. Prueba de inserción aleatoria vs ordenada");
        System.out.println("0. Salir");
    }

    // O(h): primero se valida el duplicado y luego se inserta
    private static void registrar() {
        Integer codigo = leerEntero("Código (1 - 1000000): ", 1, 1_000_000);
        if (codigo == null) return;
        if (inventario.contiene(codigo)) {
            System.out.println("Error: ya existe un producto con el código " + codigo
                    + " -> " + inventario.buscar(codigo).getProducto());
            return;
        }
        String nombre = leerTexto("Nombre: ");
        if (nombre == null) return;
        Integer precio = leerEntero("Precio en COP: ", 0, Integer.MAX_VALUE);
        if (precio == null) return;
        Integer cantidad = leerEntero("Cantidad en stock: ", 0, Integer.MAX_VALUE);
        if (cantidad == null) return;

        Producto nuevo = new Producto(codigo, nombre, precio, cantidad);
        inventario.insertar(nuevo);
        System.out.println("Registrado: " + nuevo);
    }

    private static void buscar() {
        if (avisarSiVacio()) return;
        Integer codigo = leerEntero("Código a buscar: ", 1, 1_000_000);
        if (codigo == null) return;
        ResultadoBusqueda r = inventario.buscar(codigo);
        if (r.isEncontrado()) {
            System.out.println("Encontrado: " + r.getProducto());
        } else {
            System.out.println("No existe un producto con el código " + codigo + ".");
        }
        System.out.println("Comparaciones: " + r.getComparaciones()
                + " | Camino: " + r.caminoTexto()
                + " | Altura del árbol: " + inventario.altura());
    }

    private static void eliminar() {
        if (avisarSiVacio()) return;
        Integer codigo = leerEntero("Código a eliminar: ", 1, 1_000_000);
        if (codigo == null) return;
        Producto eliminado = inventario.eliminar(codigo);
        if (eliminado == null) {
            System.out.println("No existe un producto con el código " + codigo + ". No se eliminó nada.");
        } else {
            System.out.println("Eliminado: " + eliminado);
            System.out.println("Quedan " + inventario.getTamano() + " productos.");
        }
    }

    private static void listarInorden() {
        if (avisarSiVacio()) return;
        List<Producto> productos = inventario.inorden();
        long valorTotal = 0;
        System.out.println("Código | Producto             | Precio (COP) | Stock");
        for (Producto p : productos) {
            System.out.printf("%6d | %-20s | %,12d | %5d%n",
                    p.getCodigo(), p.getNombre(), p.getPrecio(), p.getCantidad());
            valorTotal += (long) p.getPrecio() * p.getCantidad();
        }
        System.out.printf("Total: %d productos | Altura: %d | Valor del inventario: $%,d COP%n",
                productos.size(), inventario.altura(), valorTotal);
    }

    private static void menorYMayor() {
        if (avisarSiVacio()) return;
        System.out.println("Menor código: " + inventario.menor());
        System.out.println("Mayor código: " + inventario.mayor());
    }

    private static void pruebaInsercion() {
        Integer n = leerEntero("Cantidad de productos a insertar (10 - 5000): ", 10, 5000);
        if (n == null) return;
        System.out.println("Inserción | n    | Altura | Comparaciones promedio | Comparaciones (código inexistente)");
        for (Experimento.Resultado r : Experimento.compararInsercion(n, 42)) {
            System.out.printf("%-9s | %-4d | %6d | %22.2f | %d%n",
                    r.tipo(), r.n(), r.altura(), r.promedioComparaciones(), r.comparacionesInexistente());
        }
        System.out.printf("Referencia: log2(%d) = %.2f%n", n, Math.log(n) / Math.log(2));
        System.out.println("(Esta prueba usa árboles aparte; no modifica el inventario.)");
    }

    private static boolean avisarSiVacio() {
        if (inventario.estaVacio()) {
            System.out.println("El inventario está vacío.");
            return true;
        }
        return false;
    }

    // ---------- Lectura validada de datos ----------

    /** Lee un entero en [min, max]; repite mientras sea inválido. Devuelve null si se acaba la entrada. */
    private static Integer leerEntero(String mensaje, int min, int max) {
        while (true) {
            System.out.print(mensaje);
            if (!entrada.hasNextLine()) return null;
            String linea = entrada.nextLine().trim();
            try {
                int valor = Integer.parseInt(linea);
                if (valor >= min && valor <= max) return valor;
                System.out.println(max == Integer.MAX_VALUE
                        ? "Debe ser mayor o igual a " + min + "."
                        : "Debe estar entre " + min + " y " + max + ".");
            } catch (NumberFormatException e) {
                System.out.println("\"" + linea + "\" no es un número entero válido.");
            }
        }
    }

    /** Lee un texto no vacío; repite mientras esté vacío. Devuelve null si se acaba la entrada. */
    private static String leerTexto(String mensaje) {
        while (true) {
            System.out.print(mensaje);
            if (!entrada.hasNextLine()) return null;
            String linea = entrada.nextLine().trim();
            if (!linea.isEmpty()) return linea;
            System.out.println("El texto no puede estar vacío.");
        }
    }
}
