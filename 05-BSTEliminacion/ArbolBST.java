/**
 * Árbol Binario de Búsqueda con inserción, búsqueda y eliminación.
 *
 * Propiedad BST utilizada:
 * - valores menores: subárbol izquierdo;
 * - valores mayores o iguales: subárbol derecho.
 *
 * La eliminación contempla:
 * 1. Nodo hoja.
 * 2. Nodo con un solo hijo.
 * 3. Nodo con dos hijos.
 */
public class ArbolBST {

    private NodoArbol raiz;

    public NodoArbol getRaiz() {
        return raiz;
    }

    // ==========================================================
    // INSERCIÓN
    // ==========================================================

    public void insertar(int valor) {
        raiz = insertarRec(raiz, valor);
    }

    private NodoArbol insertarRec(
            NodoArbol nodo,
            int valor) {

        if (nodo == null) {
            return new NodoArbol(valor);
        }

        if (valor < nodo.getDato()) {

            nodo.setIzquierdo(
                    insertarRec(
                            nodo.getIzquierdo(),
                            valor
                    )
            );

        } else {

            nodo.setDerecho(
                    insertarRec(
                            nodo.getDerecho(),
                            valor
                    )
            );
        }

        return nodo;
    }

    // ==========================================================
    // BÚSQUEDA
    // ==========================================================

    public boolean buscar(int valor) {
        return buscarRec(raiz, valor);
    }

    private boolean buscarRec(
            NodoArbol nodo,
            int valor) {

        if (nodo == null) {
            return false;
        }

        if (valor == nodo.getDato()) {
            return true;
        }

        if (valor < nodo.getDato()) {
            return buscarRec(
                    nodo.getIzquierdo(),
                    valor
            );
        }

        return buscarRec(
                nodo.getDerecho(),
                valor
        );
    }

    // ==========================================================
    // ELIMINACIÓN
    // ==========================================================

    /**
     * Elimina un valor del BST.
     *
     * La raíz se reasigna porque el nodo eliminado puede ser
     * precisamente la raíz del árbol.
     */
    public void eliminar(int valor) {
        raiz = eliminarRec(raiz, valor);
    }

    /**
     * Elimina recursivamente un valor.
     *
     * @param nodo  raíz del subárbol actual.
     * @param valor valor que se desea eliminar.
     * @return nueva raíz del subárbol después de la eliminación.
     */
    private NodoArbol eliminarRec(
            NodoArbol nodo,
            int valor) {

        // ------------------------------------------------------
        // CASO BASE: el valor no está en el árbol.
        // ------------------------------------------------------
        if (nodo == null) {
            return null;
        }

        // ------------------------------------------------------
        // 1. LOCALIZAR EL NODO
        // ------------------------------------------------------
        if (valor < nodo.getDato()) {

            nodo.setIzquierdo(
                    eliminarRec(
                            nodo.getIzquierdo(),
                            valor
                    )
            );

        } else if (valor > nodo.getDato()) {

            nodo.setDerecho(
                    eliminarRec(
                            nodo.getDerecho(),
                            valor
                    )
            );

        } else {

            // ==================================================
            // EL NODO FUE ENCONTRADO
            // ==================================================

            // --------------------------------------------------
            // CASO 1: NODO HOJA
            // --------------------------------------------------
            // Si ambos hijos son null, devolvemos null.
            // La referencia del padre quedará desconectada.
            if (nodo.getIzquierdo() == null
                    && nodo.getDerecho() == null) {

                return null;
            }

            // --------------------------------------------------
            // CASO 2A: SOLO TIENE HIJO DERECHO
            // --------------------------------------------------
            // El padre pasa a apuntar directamente al hijo derecho.
            if (nodo.getIzquierdo() == null) {
                return nodo.getDerecho();
            }

            // --------------------------------------------------
            // CASO 2B: SOLO TIENE HIJO IZQUIERDO
            // --------------------------------------------------
            // El padre pasa a apuntar directamente al hijo izquierdo.
            if (nodo.getDerecho() == null) {
                return nodo.getIzquierdo();
            }

            // --------------------------------------------------
            // CASO 3: TIENE DOS HIJOS
            // --------------------------------------------------
            // 1. Buscar el sucesor inorden:
            //    mínimo del subárbol derecho.
            int sucesor =
                    minValor(
                            nodo.getDerecho()
                    );

            // 2. Copiar el valor del sucesor en el nodo actual.
            nodo.setDato(sucesor);

            // 3. Eliminar el sucesor original del subárbol derecho.
            //    De esta forma no quedan datos duplicados.
            nodo.setDerecho(
                    eliminarRec(
                            nodo.getDerecho(),
                            sucesor
                    )
            );
        }

        // Devolver la nueva raíz del subárbol.
        return nodo;
    }

    // ==========================================================
    // SUCESOR EN INORDEN
    // ==========================================================

    /**
     * Devuelve el valor mínimo de un subárbol.
     *
     * En un BST, el mínimo se encuentra avanzando siempre
     * por las referencias izquierdas.
     *
     * @param nodo raíz del subárbol.
     * @return valor mínimo encontrado.
     */
    public int minValor(NodoArbol nodo) {

        if (nodo == null) {
            throw new IllegalArgumentException(
                    "No se puede obtener el mínimo de un subárbol vacío."
            );
        }

        NodoArbol actual = nodo;

        while (actual.getIzquierdo() != null) {
            actual = actual.getIzquierdo();
        }

        return actual.getDato();
    }

    // ==========================================================
    // RECORRIDO INORDEN
    // ==========================================================

    /**
     * Imprime las claves ordenadas ascendentemente.
     */
    public void mostrarInorden() {
        mostrarInordenRec(raiz);
        System.out.println();
    }

    private void mostrarInordenRec(
            NodoArbol nodo) {

        if (nodo == null) {
            return;
        }

        mostrarInordenRec(
                nodo.getIzquierdo()
        );

        System.out.print(
                nodo.getDato() + " "
        );

        mostrarInordenRec(
                nodo.getDerecho()
        );
    }

    // ==========================================================
    // VALIDACIÓN DE LA PROPIEDAD BST
    // ==========================================================

    /**
     * Verifica que el árbol conserve la propiedad BST.
     */
    public boolean esBSTValido() {
        return esBSTValidoRec(
                raiz,
                null,
                null
        );
    }

    private boolean esBSTValidoRec(
            NodoArbol nodo,
            Integer minimo,
            Integer maximo) {

        if (nodo == null) {
            return true;
        }

        int valor = nodo.getDato();

        // Menores estrictos a la izquierda.
        if (minimo != null
                && valor < minimo) {
            return false;
        }

        if (maximo != null
                && valor >= maximo) {
            return false;
        }

        return esBSTValidoRec(
                    nodo.getIzquierdo(),
                    minimo,
                    valor
                )
                &&
                esBSTValidoRec(
                    nodo.getDerecho(),
                    valor,
                    maximo
                );
    }

    // ==========================================================
    // IMPRESIÓN VISUAL
    // ==========================================================

    /**
     * Muestra el árbol de lado.
     */
    public void imprimirArbol() {
        imprimirArbolRec(
                raiz,
                0
        );
    }

    private void imprimirArbolRec(
            NodoArbol nodo,
            int nivel) {

        if (nodo == null) {
            return;
        }

        imprimirArbolRec(
                nodo.getDerecho(),
                nivel + 1
        );

        System.out.println(
                "    ".repeat(nivel)
                        + nodo.getDato()
        );

        imprimirArbolRec(
                nodo.getIzquierdo(),
                nivel + 1
        );
    }

    // ==========================================================
    // UTILIDADES
    // ==========================================================

    public int contarNodos() {
        return contarNodosRec(raiz);
    }

    private int contarNodosRec(
            NodoArbol nodo) {

        if (nodo == null) {
            return 0;
        }

        return 1
                + contarNodosRec(
                        nodo.getIzquierdo()
                )
                + contarNodosRec(
                        nodo.getDerecho()
                );
    }
}
