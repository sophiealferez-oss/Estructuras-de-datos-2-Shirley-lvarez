import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Árbol binario de búsqueda (BST) genérico con los cuatro recorridos de la Guía 6.
 * Cada recorrido tiene dos versiones: una que imprime (como el código base de la guía)
 * y otra que devuelve la secuencia en una lista, útil para las pruebas.
 *
 * @param <T> tipo de dato, debe ser comparable (en este proyecto, Producto)
 */
public class ArbolBST<T extends Comparable<T>> {
    private NodoArbol<T> raiz;

    public NodoArbol<T> getRaiz() { return raiz; }

    /** Inserta un valor respetando la propiedad del BST. Ignora duplicados. */
    public void insertar(T valor) {
        raiz = insertar(raiz, valor);
    }

    private NodoArbol<T> insertar(NodoArbol<T> n, T valor) {
        if (n == null) return new NodoArbol<>(valor);
        int cmp = valor.compareTo(n.getDato());
        if (cmp < 0) n.setIzquierdo(insertar(n.getIzquierdo(), valor));
        else if (cmp > 0) n.setDerecho(insertar(n.getDerecho(), valor));
        return n;
    }

    // ---------- Preorden: raíz - izquierda - derecha ----------
    public void preorden(NodoArbol<T> n) {
        if (n == null) return;
        System.out.print(n.getDato() + " ");
        preorden(n.getIzquierdo());
        preorden(n.getDerecho());
    }

    public List<T> preordenLista() {
        List<T> out = new ArrayList<>();
        preorden(raiz, out);
        return out;
    }

    private void preorden(NodoArbol<T> n, List<T> out) {
        if (n == null) return;
        out.add(n.getDato());
        preorden(n.getIzquierdo(), out);
        preorden(n.getDerecho(), out);
    }

    // ---------- Inorden: izquierda - raíz - derecha ----------
    public void inorden(NodoArbol<T> n) {
        if (n == null) return;
        inorden(n.getIzquierdo());
        System.out.print(n.getDato() + " ");
        inorden(n.getDerecho());
    }

    public List<T> inordenLista() {
        List<T> out = new ArrayList<>();
        inorden(raiz, out);
        return out;
    }

    private void inorden(NodoArbol<T> n, List<T> out) {
        if (n == null) return;
        inorden(n.getIzquierdo(), out);
        out.add(n.getDato());
        inorden(n.getDerecho(), out);
    }

    // ---------- Postorden: izquierda - derecha - raíz ----------
    public void postorden(NodoArbol<T> n) {
        if (n == null) return;
        postorden(n.getIzquierdo());
        postorden(n.getDerecho());
        System.out.print(n.getDato() + " ");
    }

    public List<T> postordenLista() {
        List<T> out = new ArrayList<>();
        postorden(raiz, out);
        return out;
    }

    private void postorden(NodoArbol<T> n, List<T> out) {
        if (n == null) return;
        postorden(n.getIzquierdo(), out);
        postorden(n.getDerecho(), out);
        out.add(n.getDato());
    }

    // ---------- Por niveles (BFS) con Queue ----------
    public void porNiveles(NodoArbol<T> raiz) {
        if (raiz == null) return;
        Queue<NodoArbol<T>> cola = new LinkedList<>();
        cola.add(raiz);
        while (!cola.isEmpty()) {
            NodoArbol<T> actual = cola.poll();
            System.out.print(actual.getDato() + " ");
            if (actual.getIzquierdo() != null) cola.add(actual.getIzquierdo());
            if (actual.getDerecho() != null) cola.add(actual.getDerecho());
        }
    }

    public List<T> porNivelesLista() {
        List<T> out = new ArrayList<>();
        if (raiz == null) return out;
        Queue<NodoArbol<T>> cola = new LinkedList<>();
        cola.add(raiz);
        while (!cola.isEmpty()) {
            NodoArbol<T> actual = cola.poll();
            out.add(actual.getDato());
            if (actual.getIzquierdo() != null) cola.add(actual.getIzquierdo());
            if (actual.getDerecho() != null) cola.add(actual.getDerecho());
        }
        return out;
    }

    // ---------- Eliminación segura del árbol completo (postorden) ----------
    /**
     * Libera todos los nodos visitando primero los hijos y al final el padre,
     * de modo que nunca se pierde la referencia a un subárbol sin procesar.
     * @return secuencia en que se liberaron los nodos
     */
    public List<T> eliminarTodo() {
        List<T> eliminados = new ArrayList<>();
        liberar(raiz, eliminados);
        raiz = null;
        return eliminados;
    }

    private void liberar(NodoArbol<T> n, List<T> eliminados) {
        if (n == null) return;
        liberar(n.getIzquierdo(), eliminados);
        liberar(n.getDerecho(), eliminados);
        eliminados.add(n.getDato());
        n.setIzquierdo(null);
        n.setDerecho(null);
    }
}
