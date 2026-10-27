import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Compara cómo queda el BST según el orden de inserción de los códigos.
 * - Ordenada (1, 2, 3, ..., n): cada nodo se cuelga a la derecha y el árbol degenera en una lista.
 * - Aleatoria (los mismos códigos barajados): el árbol queda cerca de balanceado.
 */
public final class Experimento {

    /** Medidas de un árbol construido con un orden de inserción. */
    public record Resultado(String tipo, int n, int altura, double promedioComparaciones,
                            int comparacionesInexistente) { }

    private Experimento() { }

    /** Construye un árbol con los códigos 1..n insertados en orden y otro en orden aleatorio. */
    public static List<Resultado> compararInsercion(int n, long semilla) {
        List<Integer> ordenados = new ArrayList<>();
        for (int i = 1; i <= n; i++) ordenados.add(i);

        List<Integer> aleatorios = new ArrayList<>(ordenados);
        Collections.shuffle(aleatorios, new Random(semilla));

        return List.of(medir("Aleatoria", aleatorios), medir("Ordenada", ordenados));
    }

    /** Inserta los códigos en el orden dado y mide altura y comparaciones de búsqueda. */
    public static Resultado medir(String tipo, List<Integer> codigos) {
        ArbolBST arbol = new ArbolBST();
        for (int codigo : codigos) {
            arbol.insertar(new Producto(codigo, "Producto " + codigo, 1000, 1));
        }

        long totalComparaciones = 0;
        for (int codigo : codigos) {
            totalComparaciones += arbol.buscar(codigo).getComparaciones();
        }
        double promedio = codigos.isEmpty() ? 0 : (double) totalComparaciones / codigos.size();
        int inexistente = arbol.buscar(codigos.size() + 1).getComparaciones();

        return new Resultado(tipo, codigos.size(), arbol.altura(), promedio, inexistente);
    }
}
