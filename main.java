import java.util.ArrayList;
import java.util.List;

class NodoGeneral<T> {
    private T dato;
    private List<NodoGeneral<T>> hijos = new ArrayList<>();

    public NodoGeneral(T dato) { this.dato = dato; }
    public void agregarHijo(NodoGeneral<T> hijo) { hijos.add(hijo); }
    public T getDato() { return dato; }
    public List<NodoGeneral<T>> getHijos() { return hijos; }

    // Cuenta este nodo más todos sus descendientes (recursivo)
    public int contarNodos() {
        int total = 1;
        for (NodoGeneral<T> hijo : hijos) {
            total += hijo.contarNodos();
        }
        return total;
    }

    // Imprime el árbol con sangría según el nivel (recursivo)
    public void imprimir(int nivel) {
        String sangria = "    ".repeat(nivel);
        String marca = (nivel == 0) ? "" : "|__ ";
        System.out.println(sangria + marca + dato + " (nivel " + nivel + ")");
        for (NodoGeneral<T> hijo : hijos) {
            hijo.imprimir(nivel + 1);
        }
    }
}

public class main {
    public static void main(String[] args) {
        NodoGeneral<String> empresa = new NodoGeneral<>("Empresa");
        NodoGeneral<String> tecnologia = new NodoGeneral<>("tecnologia");
        NodoGeneral<String> finanzas = new NodoGeneral<>("finanzas");
        NodoGeneral<String> recursosHumanos = new NodoGeneral<>("recursosHumanos");

        NodoGeneral<String> desarrollo = new NodoGeneral<>("Desarrollo");
        NodoGeneral<String> contabilidad = new NodoGeneral<>("Contabilidad");
        NodoGeneral<String> tesoreria = new NodoGeneral<>("tesoreria");
        NodoGeneral<String> seleccion = new NodoGeneral<>("seleccion");
        NodoGeneral<String> soporte = new NodoGeneral<>("soporte");

        empresa.agregarHijo(tecnologia);
        empresa.agregarHijo(finanzas);
        empresa.agregarHijo(recursosHumanos);

        tecnologia.agregarHijo(desarrollo);
        tecnologia.agregarHijo(soporte);

        finanzas.agregarHijo(contabilidad);
        finanzas.agregarHijo(tesoreria);

        recursosHumanos.agregarHijo(seleccion);

        System.out.println("Raiz: " + empresa.getDato());
        System.out.println("Total de nodos: " + empresa.contarNodos());
        System.out.println();
        empresa.imprimir(0);
    }
}