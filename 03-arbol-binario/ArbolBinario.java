    /**
     * Clase de apoyo para trabajar con un árbol binario.
     * La Guía 3 solicita construirlo manualmente enlazando
     * referencias izquierda y derecha.
     */
    public class ArbolBinario<T> {

        private NodoArbol<T> raiz;

        public ArbolBinario() {
            raiz = null;
        }

        public ArbolBinario(NodoArbol<T> raiz) {
            this.raiz = raiz;
        }

        public NodoArbol<T> getRaiz() {
            return raiz;
        }

        public void setRaiz(NodoArbol<T> raiz) {
            this.raiz = raiz;
        }

        /**
         * Imprime la raíz y sus hijos directos.
         * Incluye validaciones para evitar NullPointerException.
         */
        public void imprimirRaizYHijos() {

            if (raiz == null) {
                System.out.println("El árbol está vacío.");
                return;
            }

            System.out.println("Raíz: " + raiz.getDato());

            if (raiz.getIzquierdo() != null) {
                System.out.println(
                    "Hijo izquierdo: " + raiz.getIzquierdo().getDato()
                );
            } else {
                System.out.println("Hijo izquierdo: null");
            }

            if (raiz.getDerecho() != null) {
                System.out.println(
                    "Hijo derecho: " + raiz.getDerecho().getDato()
                );
            } else {
                System.out.println("Hijo derecho: null");
            }
        }

        /** Muestra todo el árbol usando recursividad. */
        public void imprimirArbol() {
            if (raiz == null) {
                System.out.println("El árbol está vacío.");
                return;
            }
            imprimirRecursivo(raiz, 0, "RAÍZ");
        }

        private void imprimirRecursivo(
                NodoArbol<T> nodo,
                int nivel,
                String relacion) {

            if (nodo == null) {
                return;
            }

            String sangria = "   ".repeat(nivel);

            System.out.println(
                sangria + relacion + " -> " + nodo.getDato()
            );

            imprimirRecursivo(
                nodo.getIzquierdo(), nivel + 1, "IZQ"
            );

            imprimirRecursivo(
                nodo.getDerecho(), nivel + 1, "DER"
            );
        }

        public int contarNodos() {
            return contarNodos(raiz);
        }

        private int contarNodos(NodoArbol<T> nodo) {
            if (nodo == null) return 0;

            return 1
                + contarNodos(nodo.getIzquierdo())
                + contarNodos(nodo.getDerecho());
        }

        public int contarHojas() {
            return contarHojas(raiz);
        }

        private int contarHojas(NodoArbol<T> nodo) {
            if (nodo == null) return 0;
            if (nodo.esHoja()) return 1;

            return contarHojas(nodo.getIzquierdo())
                + contarHojas(nodo.getDerecho());
        }

        /**
         * Altura medida en número de aristas.
         * Árbol vacío = -1; hoja = 0.
         */
        public int altura() {
            return altura(raiz);
        }

        private int altura(NodoArbol<T> nodo) {
            if (nodo == null) return -1;

            int izq = altura(nodo.getIzquierdo());
            int der = altura(nodo.getDerecho());

            return 1 + Math.max(izq, der);
        }
    }
