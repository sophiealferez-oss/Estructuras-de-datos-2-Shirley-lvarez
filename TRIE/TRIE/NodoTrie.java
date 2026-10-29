import java.util.Map;
import java.util.TreeMap;

/**
 * Nodo de un Trie.
 *
 * Cada nodo representa una transición de carácter: la arista que llega a él
 * está etiquetada con un carácter (la clave en el mapa del padre).
 *
 * Se usa TreeMap (implementa Map) en lugar de HashMap para que los hijos se
 * recorran en orden alfabético y las sugerencias salgan siempre en el mismo
 * orden (resultados deterministas y fáciles de verificar en las pruebas).
 */
class NodoTrie {

    /** Mapa de hijos: carácter -> nodo siguiente. */
    Map<Character, NodoTrie> hijos = new TreeMap<>();

    /** true si el camino desde la raíz hasta este nodo forma una palabra completa. */
    boolean finPalabra;
}
