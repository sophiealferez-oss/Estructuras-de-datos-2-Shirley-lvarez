import java.util.ArrayList;
import java.util.List;

/**
 * Árbol binario de búsqueda de productos, ordenado por código.
 * Invariante: todo código del subárbol izquierdo es menor que el del nodo
 * y todo código del subárbol derecho es mayor. No hay códigos repetidos.
 */
public class ArbolBST {
    private NodoArbol raiz;
    private int tamano;

    public NodoArbol getRaiz() { return raiz; }
    public int getTamano() { return tamano; }
    public boolean estaVacio() { return raiz == null; }

    /**
     * Inserta el producto en su posición según el código.
     * Complejidad: O(h); O(log n) si el árbol está balanceado, O(n) si está degenerado.
     * @return false si ya existía un producto con ese código (no se inserta)
     */
    public boolean insertar(Producto producto) {
        NodoArbol nuevo = new NodoArbol(producto);
        if (raiz == null) {
            raiz = nuevo;
            tamano++;
            return true;
        }
        NodoArbol actual = raiz;
        while (true) {
            int codigoActual = actual.getDato().getCodigo();
            if (producto.getCodigo() == codigoActual) return false;
            if (producto.getCodigo() < codigoActual) {
                if (actual.getIzquierdo() == null) {
                    actual.setIzquierdo(nuevo);
                    break;
                }
                actual = actual.getIzquierdo();
            } else {
                if (actual.getDerecho() == null) {
                    actual.setDerecho(nuevo);
                    break;
                }
                actual = actual.getDerecho();
            }
        }
        tamano++;
        return true;
    }

    /**
     * Busca un producto por código registrando cada nodo comparado.
     * Complejidad: O(h). Las comparaciones nunca superan la altura del árbol.
     */
    public ResultadoBusqueda buscar(int codigo) {
        List<Integer> camino = new ArrayList<>();
        NodoArbol actual = raiz;
        while (actual != null) {
            int codigoActual = actual.getDato().getCodigo();
            camino.add(codigoActual);
            if (codigo == codigoActual) {
                return new ResultadoBusqueda(actual.getDato(), camino.size(), camino);
            }
            actual = codigo < codigoActual ? actual.getIzquierdo() : actual.getDerecho();
        }
        return new ResultadoBusqueda(null, camino.size(), camino);
    }

    public boolean contiene(int codigo) {
        return buscar(codigo).isEncontrado();
    }
}
