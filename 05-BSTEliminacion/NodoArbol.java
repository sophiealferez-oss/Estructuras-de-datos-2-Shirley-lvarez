/**
 * Representa un nodo de un Árbol Binario de Búsqueda (BST).
 *
 * En la Guía 5 se necesita modificar el dato de un nodo cuando
 * se elimina un nodo con dos hijos y se reemplaza por su sucesor
 * en inorden. Por esa razón se incluye setDato().
 */
public class NodoArbol {

    private int dato;
    private NodoArbol izquierdo;
    private NodoArbol derecho;

    /**
     * Crea un nodo con el valor indicado.
     *
     * @param dato valor almacenado en el nodo.
     */
    public NodoArbol(int dato) {
        this.dato = dato;
        this.izquierdo = null;
        this.derecho = null;
    }

    public int getDato() {
        return dato;
    }

    /**
     * Modifica el dato almacenado.
     * Se utiliza en el caso de eliminación con dos hijos.
     */
    public void setDato(int dato) {
        this.dato = dato;
    }

    public NodoArbol getIzquierdo() {
        return izquierdo;
    }

    public NodoArbol getDerecho() {
        return derecho;
    }

    public void setIzquierdo(NodoArbol izquierdo) {
        this.izquierdo = izquierdo;
    }

    public void setDerecho(NodoArbol derecho) {
        this.derecho = derecho;
    }

    /**
     * Un nodo hoja no tiene hijos.
     */
    public boolean esHoja() {
        return izquierdo == null && derecho == null;
    }

    /**
     * Devuelve el número de hijos directos.
     */
    public int grado() {
        int total = 0;

        if (izquierdo != null) {
            total++;
        }

        if (derecho != null) {
            total++;
        }

        return total;
    }
}
