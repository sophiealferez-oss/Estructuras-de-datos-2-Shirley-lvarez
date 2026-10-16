/**
 * Guía 3: construcción manual de un árbol binario de 7 nodos.
 */
public class main{

    public static void main (String[] args) {

        // 1. Crear los siete nodos.
        NodoArbol<String> Colombia = new NodoArbol<>("Colombia");
        NodoArbol<String> Bogota = new NodoArbol<>("Bogota");
        NodoArbol<String> Usme = new NodoArbol<>("Usme");
        NodoArbol<String> Suba = new NodoArbol<>("Suba");
        NodoArbol<String> Acacias = new NodoArbol<>("Acacias");
        NodoArbol<String> Nutivara = new NodoArbol<>("Nutivara");
        NodoArbol<String> Bachue = new NodoArbol<>("Bachue");

        /*
         * 2. Enlazar las referencias:
         *
         *          A
         *        /   \
         *       B     C
         *      / \   / \
         *     D   E F   G
         *
         * Es binario, lleno, completo y perfecto.
         */
        Colombia.setIzquierdo(Bogota);
        Colombia.setDerecho(Acacias);

        Bogota.setIzquierdo(Usme);
        Bogota.setDerecho(Suba);

        Acacias.setIzquierdo(Nutivara);
        Acacias.setDerecho(Bachue);

        // 3. Crear el objeto árbol.
        ArbolBinario<String> arbol = new ArbolBinario<>(Colombia);

        // 4. Método solicitado por la guía.
        System.out.println("=== RAÍZ E HIJOS ===");
        arbol.imprimirRaizYHijos();

        // 5. Mostrar toda la estructura.
        System.out.println("\n=== ÁRBOL COMPLETO ===");
        arbol.imprimirArbol();

        // 6. Probar propiedades.
        System.out.println("\n=== PROPIEDADES ===");
        System.out.println("Nodos: " + arbol.contarNodos());
        System.out.println("Hojas: " + arbol.contarHojas());
        System.out.println("Altura: " + arbol.altura());
        System.out.println("Grado de Colombia: " + Colombia.grado());
        System.out.println("¿Usme es hoja?: " + Usme.esHoja());

        // 7. Prueba segura con null.
        System.out.println("\n=== CONTROL DE NULL ===");

        System.out.println(
            "Hijo izquierdo de Usme: " + Usme.getIzquierdo()
        );

        if (Usme.getIzquierdo() != null) {
            System.out.println(Usme.getIzquierdo().getDato());
        } else {
            System.out.println(
                "Usme no tiene hijo izquierdo; la referencia es null."
            );
        }

        // 8. Probar un árbol vacío.
        System.out.println("\n=== ÁRBOL VACÍO ===");
        ArbolBinario<Integer> vacio = new ArbolBinario<>();
        vacio.imprimirRaizYHijos();
    }
}
