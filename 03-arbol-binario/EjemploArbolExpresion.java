/**
 * Ejercicio: árbol binario de expresiones.
 *
 *          *
 *        /   \
 *       +     -
 *      / \   / \
 *     8   2 11  4
 *
 * Expresión: (8 + 2) * (11 - 4)
 */
public class EjemploArbolExpresion {

    public static void main(String[] args) {

        NodoArbol<String> mult = new NodoArbol<>("*");
        NodoArbol<String> suma = new NodoArbol<>("+");
        NodoArbol<String> resta = new NodoArbol<>("-");

        NodoArbol<String> n3 = new NodoArbol<>("8");
        NodoArbol<String> n5 = new NodoArbol<>("2");
        NodoArbol<String> n10 = new NodoArbol<>("11");
        NodoArbol<String> n2 = new NodoArbol<>("4");

        mult.setIzquierdo(suma);
        mult.setDerecho(resta);

        suma.setIzquierdo(n3);
        suma.setDerecho(n5);

        resta.setIzquierdo(n10);
        resta.setDerecho(n2);

        ArbolBinario<String> arbol = new ArbolBinario<>(mult);

        System.out.println("=== ÁRBOL DE EXPRESIÓN ===");
        arbol.imprimirArbol();

        System.out.println(
            "\nResultado de (8 + 2) * (11 - 4): "
            + evaluar(mult)
        );
    }

    public static int evaluar(NodoArbol<String> nodo) {

        if (nodo == null) {
            throw new IllegalArgumentException(
                "No se puede evaluar una referencia null."
            );
        }

        if (nodo.esHoja()) {
            return Integer.parseInt(nodo.getDato());
        }

        int izq = evaluar(nodo.getIzquierdo());
        int der = evaluar(nodo.getDerecho());

        return switch (nodo.getDato()) {
            case "+" -> izq + der;
            case "-" -> izq - der;
            case "*" -> izq * der;
            case "/" -> izq / der;
            default -> throw new IllegalArgumentException(
                "Operador no válido: " + nodo.getDato()
            );
        };
    }
}