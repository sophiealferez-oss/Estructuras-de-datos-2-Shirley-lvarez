/**
 * Nodo de un Árbol Binario de Búsqueda.
 * Cada nodo almacena un entero y dos referencias:
 * izquierda y derecha.
 */
public class NodoArbol {
    private final int dato;
    private NodoArbol izquierdo;
    private NodoArbol derecho;

    public NodoArbol(int dato) {
        this.dato = dato;
    }

    public int getDato() { return dato; }
    public NodoArbol getIzquierdo() { return izquierdo; }
    public NodoArbol getDerecho() { return derecho; }
    public void setIzquierdo(NodoArbol n) { izquierdo = n; }
    public void setDerecho(NodoArbol n) { derecho = n; }

    /** Un nodo hoja existe, pero no tiene hijos. */
    public boolean esHoja() {
        return izquierdo == null && derecho == null;
    }
}
