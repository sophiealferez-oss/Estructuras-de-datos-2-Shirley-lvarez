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

    /**
     * Elimina el producto con ese código manteniendo el invariante del BST.
     * Casos: hoja (se quita), un hijo (el hijo sube) y dos hijos
     * (se copia el sucesor inorden y se elimina el sucesor del subárbol derecho).
     * Complejidad: O(h).
     * @return el producto eliminado, o null si el código no existe
     */
    public Producto eliminar(int codigo) {
        Producto eliminado = buscar(codigo).getProducto();
        if (eliminado == null) return null;
        raiz = eliminar(raiz, codigo);
        tamano--;
        return eliminado;
    }

    private NodoArbol eliminar(NodoArbol nodo, int codigo) {
        if (nodo == null) return null;
        int codigoActual = nodo.getDato().getCodigo();
        if (codigo < codigoActual) {
            nodo.setIzquierdo(eliminar(nodo.getIzquierdo(), codigo));
        } else if (codigo > codigoActual) {
            nodo.setDerecho(eliminar(nodo.getDerecho(), codigo));
        } else {
            // Caso 1 y 2: hoja o un solo hijo
            if (nodo.getIzquierdo() == null) return nodo.getDerecho();
            if (nodo.getDerecho() == null) return nodo.getIzquierdo();
            // Caso 3: dos hijos -> sucesor inorden (mínimo del subárbol derecho)
            NodoArbol sucesor = minimo(nodo.getDerecho());
            nodo.setDato(sucesor.getDato());
            nodo.setDerecho(eliminar(nodo.getDerecho(), sucesor.getDato().getCodigo()));
        }
        return nodo;
    }

    private NodoArbol minimo(NodoArbol nodo) {
        while (nodo.getIzquierdo() != null) nodo = nodo.getIzquierdo();
        return nodo;
    }

    /** Producto de menor código (nodo más a la izquierda). O(h). Null si está vacío. */
    public Producto menor() {
        return raiz == null ? null : minimo(raiz).getDato();
    }

    /** Producto de mayor código (nodo más a la derecha). O(h). Null si está vacío. */
    public Producto mayor() {
        if (raiz == null) return null;
        NodoArbol actual = raiz;
        while (actual.getDerecho() != null) actual = actual.getDerecho();
        return actual.getDato();
    }

    /** Recorrido Inorden: devuelve los productos ordenados por código. O(n). */
    public List<Producto> inorden() {
        List<Producto> lista = new ArrayList<>();
        inorden(raiz, lista);
        return lista;
    }

    private void inorden(NodoArbol nodo, List<Producto> lista) {
        if (nodo == null) return;
        inorden(nodo.getIzquierdo(), lista);
        lista.add(nodo.getDato());
        inorden(nodo.getDerecho(), lista);
    }

    /**
     * Altura medida en niveles: árbol vacío = 0, un solo nodo = 1.
     * Es el máximo de comparaciones que puede hacer una búsqueda. O(n).
     */
    public int altura() {
        return altura(raiz);
    }

    private int altura(NodoArbol nodo) {
        if (nodo == null) return 0;
        return 1 + Math.max(altura(nodo.getIzquierdo()), altura(nodo.getDerecho()));
    }
}
