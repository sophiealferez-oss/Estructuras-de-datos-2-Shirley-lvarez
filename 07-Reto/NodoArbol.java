/**
 * Nodo del BST: guarda un producto y los enlaces a sus dos hijos.
 */
public class NodoArbol {
    private Producto dato;
    private NodoArbol izquierdo;
    private NodoArbol derecho;

    public NodoArbol(Producto dato) {
        this.dato = dato;
    }

    public Producto getDato() { return dato; }
    public void setDato(Producto dato) { this.dato = dato; }
    public NodoArbol getIzquierdo() { return izquierdo; }
    public void setIzquierdo(NodoArbol izquierdo) { this.izquierdo = izquierdo; }
    public NodoArbol getDerecho() { return derecho; }
    public void setDerecho(NodoArbol derecho) { this.derecho = derecho; }
}
