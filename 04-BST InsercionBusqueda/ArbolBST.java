/**
 * BST didáctico con inserción y búsqueda recursiva.
 *
 * Convención:
 * - menores a la izquierda;
 * - mayores o iguales a la derecha.
 */
public class ArbolBST {

    private NodoArbol raiz;

    public NodoArbol getRaiz() {
        return raiz;
    }

    /** Inserta un valor conservando la propiedad BST. */
    public void insertar(int valor) {
        raiz = insertarRec(raiz, valor);
    }

    /**
     * Caso base: si nodo == null, se crea el nuevo nodo.
     * Caso recursivo: se continúa por izquierda o derecha.
     */
    private NodoArbol insertarRec(NodoArbol nodo, int valor) {

        if (nodo == null) {
            return new NodoArbol(valor);
        }

        if (valor < nodo.getDato()) {
            nodo.setIzquierdo(
                insertarRec(nodo.getIzquierdo(), valor)
            );
        } else {
            nodo.setDerecho(
                insertarRec(nodo.getDerecho(), valor)
            );
        }

        return nodo;
    }

    /** Devuelve true si el valor existe. */
    public boolean buscar(int valor) {
        return buscarRec(raiz, valor);
    }

    private boolean buscarRec(NodoArbol nodo, int valor) {

        if (nodo == null) return false;
        if (valor == nodo.getDato()) return true;

        if (valor < nodo.getDato()) {
            return buscarRec(nodo.getIzquierdo(), valor);
        }

        return buscarRec(nodo.getDerecho(), valor);
    }

    /**
     * Búsqueda con evidencia del camino y de las comparaciones.
     */
    public ResultadoBusqueda buscarConTraza(int valor) {
        return buscarConTrazaRec(raiz, valor, 0, new StringBuilder());
    }

    private ResultadoBusqueda buscarConTrazaRec(
            NodoArbol nodo,
            int valor,
            int comparaciones,
            StringBuilder camino) {

        if (nodo == null) {
            if (camino.length() > 0) camino.append(" -> null");
            else camino.append("null");

            return new ResultadoBusqueda(
                false, comparaciones, camino.toString()
            );
        }

        comparaciones++;

        if (camino.length() > 0) camino.append(" -> ");
        camino.append(nodo.getDato());

        if (valor == nodo.getDato()) {
            return new ResultadoBusqueda(
                true, comparaciones, camino.toString()
            );
        }

        if (valor < nodo.getDato()) {
            return buscarConTrazaRec(
                nodo.getIzquierdo(), valor, comparaciones, camino
            );
        }

        return buscarConTrazaRec(
            nodo.getDerecho(), valor, comparaciones, camino
        );
    }

    /**
     * Altura en número de aristas:
     * árbol vacío = -1; hoja = 0.
     */
    public int altura() {
        return alturaRec(raiz);
    }

    private int alturaRec(NodoArbol nodo) {
        if (nodo == null) return -1;

        int izq = alturaRec(nodo.getIzquierdo());
        int der = alturaRec(nodo.getDerecho());

        return 1 + Math.max(izq, der);
    }

    /** Recorrido Inorden: imprime las claves ordenadas. */
    public void mostrarInorden() {
        mostrarInordenRec(raiz);
        System.out.println();
    }

    private void mostrarInordenRec(NodoArbol nodo) {
        if (nodo == null) return;

        mostrarInordenRec(nodo.getIzquierdo());
        System.out.print(nodo.getDato() + " ");
        mostrarInordenRec(nodo.getDerecho());
    }

    /** Impresión lateral del árbol. */
    public void imprimirArbol() {
        imprimirArbolRec(raiz, 0);
    }

    private void imprimirArbolRec(NodoArbol nodo, int nivel) {
        if (nodo == null) return;

        imprimirArbolRec(nodo.getDerecho(), nivel + 1);
        System.out.println("    ".repeat(nivel) + nodo.getDato());
        imprimirArbolRec(nodo.getIzquierdo(), nivel + 1);
    }
}
