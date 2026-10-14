import java.util.ArrayList;
import java.util.List;

class NodoGeneral<T> {
    private T dato;
    private List<NodoGeneral<T>> hijos = new ArrayList<>();

    public NodoGeneral(T dato) 
    { this.dato = dato; }
    public void agregarHijo(NodoGeneral<T> hijo)
    { hijos.add(hijo); }
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

        //  jerarquía
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
        System.out.println("Nivel 1 - hijos de empresa: " + empresa.getDato());
        System.out.println();
        System.out.println(empresa.getHijos().get(0).getDato());
        System.out.println();
        System.out.println(empresa.getHijos().get(1).getDato());
        System.out.println();
        System.out.println(empresa.getHijos().get(2).getDato());

        System.out.println("Nivel 2");

        System.out.println();

        System.out.println("hijos de tecnologia: " + tecnologia.getDato());
        System.out.println();
        System.out.println(tecnologia.getHijos().get(0).getDato());
        System.out.println();
        System.out.println(tecnologia.getHijos().get(1).getDato());
        System.out.println();

        System.out.println("hijos de finanzas: " + finanzas.getDato());
        System.out.println();
        System.out.println(finanzas.getHijos().get(0).getDato());
        System.out.println();
        System.out.println(finanzas.getHijos().get(1).getDato());

        System.out.println();
        System.out.println("hijos de recursos humanos: " + recursosHumanos.getDato());
        System.out.println(recursosHumanos.getHijos().get(0).getDato());

    


    }
}