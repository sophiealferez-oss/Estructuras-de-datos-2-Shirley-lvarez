import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Trie (árbol de prefijos) para almacenar palabras y autocompletar.
 *
 * Complejidad (L = longitud de la cadena consultada):
 *   insertar(palabra)   -> O(L)
 *   buscar(palabra)     -> O(L)
 *   empiezaCon(prefijo) -> O(L)
 *   sugerir(prefijo)    -> O(L + K), K = nodos visitados bajo el prefijo
 *                          (con límite de resultados, se corta al llegar al tope)
 * Ninguna depende de cuántas palabras (N) hay almacenadas.
 */
class Trie {

    /** Límite por defecto de sugerencias. */
    public static final int LIMITE_SUGERENCIAS = 5;

    /** Raíz del árbol: no representa ningún carácter. */
    private final NodoTrie raiz = new NodoTrie();

    /** Cantidad de palabras distintas almacenadas. */
    private int totalPalabras = 0;

    /**
     * Inserta una palabra en el Trie (se normaliza a minúsculas).
     * Crea los nodos que falten y marca el último como fin de palabra.
     */
    void insertar(String palabra) {
        if (palabra == null || palabra.isBlank()) {
            return; // se ignoran entradas vacías
        }
        NodoTrie actual = raiz;
        for (char c : palabra.trim().toLowerCase().toCharArray()) {
            actual = actual.hijos.computeIfAbsent(c, k -> new NodoTrie());
        }
        if (!actual.finPalabra) { // evita contar duplicados
            actual.finPalabra = true;
            totalPalabras++;
        }
    }

    /**
     * Busca una palabra COMPLETA.
     * @return true solo si la palabra fue insertada (no basta con ser prefijo).
     */
    boolean buscar(String palabra) {
        NodoTrie nodo = recorrer(palabra);
        return nodo != null && nodo.finPalabra;
    }

    /**
     * Indica si existe al menos una palabra que comience con el prefijo dado.
     */
    boolean empiezaCon(String prefijo) {
        return recorrer(prefijo) != null;
    }

    /**
     * Sugiere hasta 5 palabras que comienzan con el prefijo.
     */
    List<String> sugerir(String prefijo) {
        return sugerir(prefijo, LIMITE_SUGERENCIAS);
    }

    /**
     * Sugiere hasta {@code limite} palabras que comienzan con el prefijo.
     * 1) Se desciende hasta el nodo final del prefijo.
     * 2) Desde ahí se recorre el subárbol (DFS) acumulando palabras completas.
     */
    List<String> sugerir(String prefijo, int limite) {
        List<String> resultado = new ArrayList<>();
        NodoTrie nodoPrefijo = recorrer(prefijo);
        if (nodoPrefijo == null || limite <= 0) {
            return resultado; // prefijo inexistente
        }
        recolectar(nodoPrefijo, new StringBuilder(prefijo.trim().toLowerCase()), resultado, limite);
        return resultado;
    }

    /** Cantidad de palabras distintas almacenadas. */
    int tamanio() {
        return totalPalabras;
    }

    // ------------------------------------------------------------------
    // Métodos auxiliares privados
    // ------------------------------------------------------------------

    /**
     * Desciende por el Trie siguiendo los caracteres de la cadena.
     * @return el nodo donde termina la cadena, o null si el camino no existe.
     */
    private NodoTrie recorrer(String cadena) {
        if (cadena == null) {
            return null;
        }
        NodoTrie actual = raiz;
        for (char c : cadena.trim().toLowerCase().toCharArray()) {
            actual = actual.hijos.get(c);
            if (actual == null) {
                return null;
            }
        }
        return actual;
    }

    /**
     * DFS recursivo que arma las palabras bajo un nodo.
     * Se detiene en cuanto se alcanza el límite de resultados.
     */
    private void recolectar(NodoTrie nodo, StringBuilder actual, List<String> resultado, int limite) {
        if (resultado.size() >= limite) {
            return; // corte temprano
        }
        if (nodo.finPalabra) {
            resultado.add(actual.toString());
        }
        for (Map.Entry<Character, NodoTrie> hijo : nodo.hijos.entrySet()) {
            actual.append(hijo.getKey());                              // elegir
            recolectar(hijo.getValue(), actual, resultado, limite);    // explorar
            actual.deleteCharAt(actual.length() - 1);                  // deshacer (backtracking)
            if (resultado.size() >= limite) {
                return;
            }
        }
    }
}
