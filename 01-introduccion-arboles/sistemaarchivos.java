import java.util.ArrayList;
import java.util.List;

class NodoGeneral<ST> {
    private ST dato;
    private List<NodoGeneral<ST>> hijos = new ArrayList();

    public NodoGeneral(ST dato) {
        this.dato = dato;
    }

    public void agregarHijo(NodoGeneral<ST> hijo) {
        hijos.add(hijo);
    }

    public ST getDato() {
        return dato;
    }

    public List<NodoGeneral<ST>> getHijos() {
        return hijos;
    }

}

public class sistemaarchivos {

    public static void main(String[] args) {

        NodoGeneral<String> nubeShirleyPersonal = new NodoGeneral<>("Nube"); // raiz
        NodoGeneral<String> DatosAdjuntos = new NodoGeneral<>("Datos Adjuntos"); // hijo de raiz
        NodoGeneral<String> Desktop = new NodoGeneral<>("Desktop");// hijo de raiz
        NodoGeneral<String> Imagenes = new NodoGeneral<>("Imagenes");// hijo de raiz
        NodoGeneral<String> Documentos = new NodoGeneral<>("Documentos");// hijo de raiz
        NodoGeneral<String> LaEvolucionDeLaComputacion = new NodoGeneral<String>("La evolucion de la computación");// hijo
                                                                                                                   // de
                                                                                                                   // raiz
                                                                                                                   // -
                                                                                                                   // hojita

        // nivel2: hijos de los hijos

        NodoGeneral<String> OptimizacionDeProcesosAdministrativos = new NodoGeneral<String>(
                "Optimizacion de procesos administrativos"); // hijo de desktop
        NodoGeneral<String> CapturasDePantalla = new NodoGeneral<String>("Capturas de pantalla"); // hijo de imagenes
        NodoGeneral<String> Arduino = new NodoGeneral<>("Arduino"); //// hijo de documentos
        NodoGeneral<String> ProyectoWebServices = new NodoGeneral<String>("Proyecto Web Services");// hijo de documentos
        NodoGeneral<String> NetbeansProjects = new NodoGeneral<String>("NetBeansProjects");// hijo de documentos

        // nivel 3 algunos hijos de los hijos

        NodoGeneral<String> ProyectoWebServicesSlnx = new NodoGeneral<>("Proyecto Web Services.slnx"); // hijo de
                                                                                                       // ProyectoWebServices
        NodoGeneral<String> Herencia = new NodoGeneral<>("Herencia");// hijo de NetbeansProjects

        // ahora relacionamos padre e hijo (raiz con sus ramas)

        // nivel 1

        nubeShirleyPersonal.agregarHijo(DatosAdjuntos);
        nubeShirleyPersonal.agregarHijo(Desktop);
        nubeShirleyPersonal.agregarHijo(Imagenes);
        nubeShirleyPersonal.agregarHijo(Documentos);
        nubeShirleyPersonal.agregarHijo(LaEvolucionDeLaComputacion);

        // ahora las ramas con sus hojas (padre e hijos)

        // nivel 2

        Desktop.agregarHijo(OptimizacionDeProcesosAdministrativos);

        Imagenes.agregarHijo(CapturasDePantalla);

        Documentos.agregarHijo(Arduino);
        Documentos.agregarHijo(ProyectoWebServices);
        Documentos.agregarHijo(NetbeansProjects);

        // nivel 3

        ProyectoWebServices.agregarHijo(ProyectoWebServicesSlnx);
        NetbeansProjects.agregarHijo(Herencia);

        System.out.println("Raiz: " + nubeShirleyPersonal.getDato());
        System.out.println();
        System.out.println("Nivel 1: carpetas de la nube");
        System.out.println();
        System.out.println(nubeShirleyPersonal.getHijos().get(0).getDato());
        System.out.println();
        System.out.println(nubeShirleyPersonal.getHijos().get(1).getDato());
        System.out.println();
        System.out.println(nubeShirleyPersonal.getHijos().get(2).getDato());
        System.out.println();
        System.out.println(nubeShirleyPersonal.getHijos().get(3).getDato());
        System.out.println();
        System.out.println(nubeShirleyPersonal.getHijos().get(4).getDato());

        System.out.println();
        System.out.println("Nivel 2: Subcarpetas de las carpetas");
        System.out.println();
        System.out.println("Carpeta Desktop:" + Desktop.getHijos().get(0).getDato());
        System.out.println();
        System.out.println("Carpeta Imagenes:" + Imagenes.getHijos().get(0).getDato());
        System.out.println();
        System.out.println("Carpeta Documentos:" + Documentos.getHijos().get(0).getDato());
        System.out.println();
        System.out.println("Carpeta Documentos:" + Documentos.getHijos().get(1).getDato());
        System.out.println();
        System.out.println("Carpeta Documentos:" + Documentos.getHijos().get(2).getDato());

        System.out.println();
        System.out.println("Nivel 3: Subcarpetas de las subcarpetas");
        System.out.println();
        System.out.println("Subcarpeta Proyecto Web Services que viene de la carpeta Documentos:"
                + ProyectoWebServices.getHijos().get(0).getDato());
        System.out.println();
        System.out.println("Subcarpeta NetBeansProjects que viene de la carpeta Documentos:"
                + NetbeansProjects.getHijos().get(0).getDato());

        System.out.println();
        System.out.println("Hojas sueltas de la nube");
        System.out.println();
        System.out.println(nubeShirleyPersonal.getHijos().get(0).getDato());
        System.out.println();
        System.out.println(nubeShirleyPersonal.getHijos().get(4).getDato());

        System.out.println();
        System.out.println("Hojas sueltas de la carpeta Documentos");
        System.out.println();
        System.out.println(Documentos.getHijos().get(0).getDato());
        System.out.println();

    }

}
