/**
 * Guía 4. BST I: inserción y búsqueda.
 * Compara un BST construido con datos distribuidos
 * frente a otro construido con datos ordenados.
 */
public class Main {

    public static void main(String[] args) {

        // ----------------------------------------------------------
        // EJERCICIO DE LA GUÍA: 50,30,70,20,40,60,80
        // ----------------------------------------------------------
        System.out.println("=== BST DE 7 NODOS ===");

        int[] valores = {75000, 99, 80000, 30, 105, 50000, 90000};
        ArbolBST bst = new ArbolBST();

        for (int v : valores) {
            bst.insertar(v);
        }

        bst.imprimirArbol();

        System.out.println("\nInorden:");
        bst.mostrarInorden();

        System.out.println("\nBuscar 50000:");
        System.out.println(bst.buscarConTraza(50000));

        System.out.println("\nBuscar 77:");
        System.out.println(bst.buscarConTraza(77));

        // ----------------------------------------------------------
        // 15 CÓDIGOS EN ORDEN RAZONABLEMENTE DISTRIBUIDO
        // ----------------------------------------------------------
        int[] distribuidos = {
            50, 65, 60, 105, 75,
            110, 85, 115, 125, 120,
            125, 130, 140, 135, 145
        };

        ArbolBST bstDistribuido = new ArbolBST();
        for (int v : distribuidos) {
            bstDistribuido.insertar(v);
        }

        // ----------------------------------------------------------
        // 15 CÓDIGOS YA ORDENADOS
        // ----------------------------------------------------------
        int[] ordenados = {
            50, 60, 65, 75, 85,
            95, 105, 110, 115, 120,
            125, 130, 135, 140, 145
        };

        ArbolBST bstOrdenado = new ArbolBST();
        for (int v : ordenados) {
            bstOrdenado.insertar(v);
        }
        int[] consultas = {60, 85, 110, 130, 145};

        System.out.println("\n=== DATOS DISTRIBUIDOS ===");
        int compDistribuido = probarBusquedas(bstDistribuido, consultas);

        System.out.println("\n=== DATOS ORDENADOS ===");
        int compOrdenado = probarBusquedas(bstOrdenado, consultas);

        // ----------------------------------------------------------
        // COMPARACIÓN FINAL
        // ----------------------------------------------------------
        System.out.println("\n=== COMPARACIÓN ===");
        System.out.println(
            "Altura distribuido: " + bstDistribuido.altura()
        );
        System.out.println(
            "Altura ordenado: " + bstOrdenado.altura()
        );
        System.out.println(
            "Comparaciones distribuidas: " + compDistribuido
        );
        System.out.println(
            "Comparaciones ordenadas: " + compOrdenado
        );

        System.out.printf(
            "Promedio distribuido: %.2f%n",
            compDistribuido / (double) consultas.length
        );

        System.out.printf(
            "Promedio ordenado: %.2f%n",
            compOrdenado / (double) consultas.length
        );

        System.out.println(
            "\nConclusión: insertar datos ya ordenados puede "
            + "degenerar el BST y acercar búsqueda/inserción a O(n)."
        );
    }

    private static int probarBusquedas(
            ArbolBST arbol,
            int[] consultas) {

        int total = 0;

        for (int valor : consultas) {
            ResultadoBusqueda r = arbol.buscarConTraza(valor);
            System.out.println("Buscar " + valor + " -> " + r);
            total += r.getComparaciones();
        }

        return total;
    }
}