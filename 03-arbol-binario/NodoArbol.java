/**
 * Nodo genérico de un árbol binario.
 * Cada nodo almacena un dato y hasta dos referencias:
 * izquierda y derecha.
 */
public class NodoArbol<T> {

    private final T dato;
    private NodoArbol<T> izquierdo;
    private NodoArbol<T> derecho;

    public NodoArbol(T dato) {
        this.dato = dato;
        this.izquierdo = null;
        this.derecho = null;
    }

    public T getDato() {
        return dato;
    }

    public NodoArbol<T> getIzquierdo() {
        return izquierdo;
    }

    public NodoArbol<T> getDerecho() {
        return derecho;
    }

    public void setIzquierdo(NodoArbol<T> nodo) {
        this.izquierdo = nodo;
    }

    public void setDerecho(NodoArbol<T> nodo) {
        this.derecho = nodo;
    }

    /** Un nodo hoja existe, pero no tiene hijos. */
    public boolean esHoja() {
        return izquierdo == null && derecho == null;
    }

    /** En un árbol binario el grado puede ser 0, 1 o 2. */
    public int grado() {
        int total = 0;
        if (izquierdo != null) total++;
        if (derecho != null) total++;
        return total;
    }

    @Override
    public String toString() {
        return String.valueOf(dato);
    }
}
