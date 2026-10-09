import java.util.ArrayList;
import java.util.List;

class NodoGeneral<T> {
    private T dato;
    private List<NodoGeneral<T>> hijos = new ArrayList<>();

    public NodoGeneral(T dato) { this.dato = dato; }
    public void agregarHijo(NodoGeneral<T> hijo) { hijos.add(hijo); }
    public T getDato() { return dato; }
    public List<NodoGeneral<T>> getHijos() { return hijos; }
}

public class organigrama {
    public static void main(String[] args) {
        // Se crean los nodos
        NodoGeneral<String> empresa = new NodoGeneral<>("Empresa");
        NodoGeneral<String> tecnologia = new NodoGeneral<>("tecnologia");
        NodoGeneral<String> finanzas = new NodoGeneral<>("finanzas");
        NodoGeneral<String> recursosHumanos = new NodoGeneral<>("recursosHumanos");

        NodoGeneral<String> desarrollo = new NodoGeneral<>("Desarrollo");
        NodoGeneral<String> contabilidad = new NodoGeneral<>("Contabilidad");
        NodoGeneral<String> tesoreria = new NodoGeneral<>("tesoreria");
        NodoGeneral<String> seleccion = new NodoGeneral<>("seleccion");
        NodoGeneral<String> soporte = new NodoGeneral<>("soporte");

        // Se arma la jerarquía
        empresa.agregarHijo(tecnologia);
        empresa.agregarHijo(finanzas);
        empresa.agregarHijo(recursosHumanos);

        tecnologia.agregarHijo(desarrollo);
        tecnologia.agregarHijo(soporte);

        finanzas.agregarHijo(contabilidad);
        finanzas.agregarHijo(tesoreria);

        recursosHumanos.agregarHijo(seleccion);

    
        System.out.println("Nivel 0 (raiz): " + empresa.getDato());
        System.out.println();
        System.out.println("Nivel 1 (hijos de empresa): " + tecnologia.getDato() + ", " + finanzas.getDato() + ", " + recursosHumanos.getDato());
        System.out.println();

        System.out.println("Nivel 2");
    
        System.out.println();
        System.out.println("hijos de tecnologia: " + desarrollo.getDato() + ", " + soporte.getDato());
        System.out.println("hijos de finanzas:  " + contabilidad.getDato() + ", " + tesoreria.getDato());
        System.out.println("hijos de recursos humanos:  " + seleccion.getDato());
        



    }
}