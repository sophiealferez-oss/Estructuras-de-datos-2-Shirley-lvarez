/**
 * Guía 5. BST II: eliminación de nodos.
 *
 * Se prueban de forma independiente los tres casos:
 * 1. eliminación de una hoja;
 * 2. eliminación de un nodo con un hijo;
 * 3. eliminación de un nodo con dos hijos.
 *
 * El tercer caso elimina la raíz, cumpliendo además
 * el ejercicio adicional de la guía.
 */
public class Main {

        public static void main(String[] args) {

                probarCasoHoja();
                probarCasoUnHijo();
                probarCasoDosHijosYRaiz();
                probarValorInexistente();
        }

        // ==========================================================
        // CASO 1: ELIMINAR UNA HOJA
        // ==========================================================

        private static void probarCasoHoja() {

                System.out.println(
                                "========================================");
                System.out.println(
                                "CASO 1: ELIMINAR NODO HOJA");
                System.out.println(
                                "========================================");

                ArbolBST arbol = crearArbolBase();

                System.out.println(
                                "\nANTES de eliminar 105:");
                arbol.imprimirArbol();

                System.out.println(
                                "\nInorden antes:");
                arbol.mostrarInorden();

                arbol.eliminar(105);

                System.out.println(
                                "\nDESPUÉS de eliminar 105:");
                arbol.imprimirArbol();

                System.out.println(
                                "\nInorden después:");
                arbol.mostrarInorden();

                System.out.println(
                                "¿BST válido?: "
                                                + arbol.esBSTValido());

                System.out.println(
                                "¿20 existe?: "
                                                + arbol.buscar(20));

                System.out.println();
        }

        // ==========================================================
        // CASO 2: ELIMINAR UN NODO CON UN HIJO
        // ==========================================================

        private static void probarCasoUnHijo() {

                System.out.println(
                                "========================================");
                System.out.println(
                                "CASO 2: ELIMINAR NODO CON UN HIJO");
                System.out.println(
                                "========================================");

                ArbolBST arbol = crearArbolConNodoDeUnHijo();

                System.out.println(
                                "\nANTES de eliminar 90000:");
                arbol.imprimirArbol();

                System.out.println(
                                "\nInorden antes:");
                arbol.mostrarInorden();

                arbol.eliminar(60);

                System.out.println(
                                "\nDESPUÉS de eliminar 95000:");
                arbol.imprimirArbol();

                System.out.println(
                                "\nInorden después:");
                arbol.mostrarInorden();

                System.out.println(
                                "¿BST válido?: "
                                                + arbol.esBSTValido());

                System.out.println(
                                "¿90000 existe?: "
                                                + arbol.buscar(90000));

                System.out.println(
                                "¿95000 sigue existiendo?: "
                                                + arbol.buscar(95000));

                System.out.println();
        }

        // ==========================================================
        // CASO 3: ELIMINAR UN NODO CON DOS HIJOS
        // Y, AL MISMO TIEMPO, ELIMINAR LA RAÍZ
        // ==========================================================

        private static void probarCasoDosHijosYRaiz() {

                System.out.println(
                                "========================================");
                System.out.println(
                                "CASO 3: DOS HIJOS / ELIMINAR RAÍZ");
                System.out.println(
                                "========================================");

                ArbolBST arbol = crearArbolBase();

                System.out.println(
                                "\nANTES de eliminar la raíz 75000:");
                arbol.imprimirArbol();

                System.out.println(
                                "\nInorden antes:");
                arbol.mostrarInorden();

                int sucesor = arbol.minValor(
                                arbol.getRaiz().getDerecho());

                System.out.println(
                                "\nSucesor inorden de 80000: "
                                                + sucesor);

                arbol.eliminar(75000);

                System.out.println(
                                "\nDESPUÉS de eliminar la raíz 75000:");
                arbol.imprimirArbol();

                System.out.println(
                                "\nInorden después:");
                arbol.mostrarInorden();

                System.out.println(
                                "Nueva raíz: "
                                                + arbol.getRaiz().getDato());

                System.out.println(
                                "¿BST válido?: "
                                                + arbol.esBSTValido());

                System.out.println(
                                "¿75000 existe?: "
                                                + arbol.buscar(75000));

                System.out.println();
        }

        // ==========================================================
        // PRUEBA EXTRA: VALOR INEXISTENTE
        // ==========================================================

        private static void probarValorInexistente() {

                System.out.println(
                                "========================================");
                System.out.println(
                                "PRUEBA EXTRA: ELIMINAR VALOR INEXISTENTE");
                System.out.println(
                                "========================================");

                ArbolBST arbol = crearArbolBase();

                int antes = arbol.contarNodos();

                arbol.eliminar(1000);

                int despues = arbol.contarNodos();

                System.out.println(
                                "Nodos antes: " + antes);

                System.out.println(
                                "Nodos después: " + despues);

                System.out.println(
                                "¿BST válido?: "
                                                + arbol.esBSTValido());
        }

        // ==========================================================
        // ÁRBOLES DE PRUEBA
        // ==========================================================

        /**
         * BST base:
         *
         *          75000
         *        /    \
         *      99       80000
         *     /  \      /      \
         *   30   105  50000    95000
         */
        private static ArbolBST crearArbolBase() {

                int[] valores = {
                                75000, 99, 80000, 30,
                                105, 50000, 90000
                };

                ArbolBST arbol = new ArbolBST();

                for (int valor : valores) {
                        arbol.insertar(valor);
                }

                return arbol;
        }

        /**
         * BST con un nodo que tiene exactamente un hijo:
         *
         *          75000
         *        /    \
         *      99      80000
         *     /  \     /       \
         *   30   105  50000    90000
         *                         \
         *                        95000
         *
         * El nodo 60 tiene un único hijo: 65.
         */
        private static ArbolBST crearArbolConNodoDeUnHijo() {

                int[] valores = {

                                75000, 99, 80000, 30,
                                105, 50000, 90000, 95000

                };

                ArbolBST arbol = new ArbolBST();

                for (int valor : valores) {
                        arbol.insertar(valor);
                }

                return arbol;
        }

/**
             * reemplazo con sucesor
             *
             *          80000
             *        /       \
             *      99        50000
             *     /  \         \
             *   30  105      95000
             *                  
             *               
             *
             * El nodo 60 tiene un único hijo: 65.
             */


        private static ArbolBST crearArbolReemplazoSucesor() {

                int[] valores = {

                                80000, 99, 30,105,90000,50000
                };

                ArbolBST arbol = new ArbolBST();

                for (int valor : valores) {
                        arbol.insertar(valor);
                }

                return arbol;
        }


}



