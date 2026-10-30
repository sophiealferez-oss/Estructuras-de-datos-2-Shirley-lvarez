
import java.util.List;

/**
 * Programa de demostración de la Guía 12:
 * Introducción a grafos y redes complejas.
 *
 * Dominio: estaciones de TransMilenio de Bogotá.
 * Vértices: 10 estaciones.
 * Aristas: 17 conexiones (12 no dirigidas y 5 dirigidas).
 * Peso: distancia aproximada en km, con fines académicos.
 *
 * Compilar: javac -encoding UTF-8 *.java
 * Ejecutar: java Main
 */
public class Main {

    public static void main(String[] args) {
        Grafo red = construirRedDeTransporte();

        imprimirTablaVertices(red);
        imprimirTablaAristas(red);
        imprimirClasificacion(red);
        imprimirEjercicios(red);
    }

    /**
     * Construye el grafo de TransMilenio:
     * 10 vértices y 17 aristas.
     */
    static Grafo construirRedDeTransporte() {
        Grafo g = new Grafo();

        // --------------------------------------------------
        // VÉRTICES: 10 estaciones de TransMilenio
        // --------------------------------------------------

        g.agregarVertice(
                new Vertice("Portal Norte", "Troncal Norte"));
        g.agregarVertice(
                new Vertice("Toberin", "Troncal Norte"));
        g.agregarVertice(
                new Vertice("Mazuren", "Troncal Norte"));
        g.agregarVertice(
                new Vertice("Calle 146", "Troncal Norte"));
        g.agregarVertice(
                new Vertice("Calle 142", "Troncal Norte"));
        g.agregarVertice(
                new Vertice("Calle 127", "Troncal Norte"));
        g.agregarVertice(
                new Vertice("Calle 100", "Troncal Norte"));
        g.agregarVertice(
                new Vertice("Heroes", "Troncal Caracas"));
        g.agregarVertice(
                new Vertice("Calle 72", "Troncal Caracas"));
        g.agregarVertice(
                new Vertice("Calle 57", "Troncal Caracas"));

        // --------------------------------------------------
        // ARISTAS NO DIRIGIDAS: 12 conexiones de doble sentido
        // --------------------------------------------------

        g.agregarArista(new Arista(
                "Portal Norte", "Toberin", 1.2, false));

        g.agregarArista(new Arista(
                "Toberin", "Mazuren", 1.0, false));

        g.agregarArista(new Arista(
                "Mazuren", "Calle 146", 1.1, false));

        g.agregarArista(new Arista(
                "Calle 146", "Calle 142", 0.8, false));

        g.agregarArista(new Arista(
                "Calle 142", "Calle 127", 1.8, false));

        g.agregarArista(new Arista(
                "Calle 127", "Calle 100", 2.5, false));

        g.agregarArista(new Arista(
                "Calle 100", "Heroes", 2.0, false));

        g.agregarArista(new Arista(
                "Heroes", "Calle 72", 1.4, false));

        g.agregarArista(new Arista(
                "Calle 72", "Calle 57", 1.8, false));

        g.agregarArista(new Arista(
                "Calle 146", "Calle 127", 2.6, false));

        g.agregarArista(new Arista(
                "Calle 100", "Calle 72", 3.1, false));

        g.agregarArista(new Arista(
                "Heroes", "Calle 57", 2.8, false));

        // --------------------------------------------------
        // ARISTAS DIRIGIDAS: 5 conexiones de un solo sentido
        // Valores simplificados para el ejercicio académico
        // --------------------------------------------------

        g.agregarArista(new Arista(
                "Portal Norte", "Calle 100", 6.5, true));

        g.agregarArista(new Arista(
                "Toberin", "Heroes", 7.0, true));

        g.agregarArista(new Arista(
                "Calle 127", "Calle 57", 5.0, true));

        g.agregarArista(new Arista(
                "Calle 57", "Portal Norte", 9.0, true));

        g.agregarArista(new Arista(
                "Calle 72", "Calle 142", 4.0, true));

        return g;
    }

    // ------------------------------------------------------
    // TABLA DE VÉRTICES
    // ------------------------------------------------------

    static void imprimirTablaVertices(Grafo g) {
        System.out.println("=== TABLA DE VERTICES (V) ===");

        System.out.printf(
                "%-4s %-16s %-20s %-8s %-8s %-8s%n",
                "N°",
                "Estacion",
                "Troncal",
                "Grado",
                "G.sal",
                "G.ent"
        );

        int i = 1;

        for (Vertice v : g.getVertices()) {
            System.out.printf(
                    "%-4d %-16s %-20s %-8d %-8d %-8d%n",
                    i++,
                    v.nombre,
                    v.departamento,
                    g.gradoIncidente(v.nombre),
                    g.gradoSalida(v.nombre),
                    g.gradoEntrada(v.nombre)
            );
        }

        System.out.println("|V| = " + g.numVertices());
        System.out.println();
    }

    // ------------------------------------------------------
    // TABLA DE ARISTAS
    // ------------------------------------------------------

    static void imprimirTablaAristas(Grafo g) {
        System.out.println("=== TABLA DE ARISTAS (A) ===");

        System.out.printf(
                "%-4s %-16s %-16s %-12s %-14s%n",
                "N°",
                "Origen",
                "Destino",
                "Peso (km)",
                "Tipo"
        );

        int i = 1;

        for (Arista a : g.getAristas()) {
            System.out.printf(
                    "%-4d %-16s %-16s %-12.1f %-14s%n",
                    i++,
                    a.origen,
                    a.destino,
                    a.peso,
                    a.dirigida ? "Dirigida" : "No dirigida"
            );
        }

        System.out.println(
                "|A| = " + g.numAristas()
                + " (" + g.numNoDirigidas()
                + " no dirigidas + " + g.numDirigidas()
                + " dirigidas)"
        );

        System.out.println();
    }

    // ------------------------------------------------------
    // CLASIFICACIÓN FORMAL DEL GRAFO
    // ------------------------------------------------------

    static void imprimirClasificacion(Grafo g) {
        System.out.println(
                "=== CLASIFICACION FORMAL: G = (V, A) ==="
        );

        System.out.println(
                "Orden |V|          : " + g.numVertices()
        );

        System.out.println(
                "Tamano |A|         : " + g.numAristas()
        );

        System.out.println(
                "Arcos reales       : " + g.numArcos()
                + " (dirigida = 1, no dirigida = 2)"
        );

        System.out.println(
                "Direccion          : " + g.tipoDireccion()
        );

        System.out.println(
                "Ponderado          : "
                + (g.esPonderado() ? "Si (km)" : "No")
        );

        System.out.println(
                "Conexo (debil)     : "
                + (g.esConexo() ? "Si" : "No")
        );

        System.out.println(
                "Fuertemente conexo : "
                + (g.esFuertementeConexo() ? "Si" : "No")
        );

        System.out.println(
                "Ciclico            : "
                + (g.esCiclico() ? "Si" : "No (acíclico)")
        );

        System.out.printf(
                "Densidad D         : %d / (%d * %d) = %.3f -> %s%n",
                g.numArcos(),
                g.numVertices(),
                g.numVertices() - 1,
                g.densidad(),
                g.esDenso() ? "DENSO" : "DISPERSO"
        );

        System.out.printf(
                "Peso total         : %.1f km%n",
                g.pesoTotal()
        );

        System.out.println();
    }

    // ------------------------------------------------------
    // EJERCICIOS DE LA GUÍA
    // ------------------------------------------------------

    static void imprimirEjercicios(Grafo g) {
        System.out.println("=== EJERCICIOS ===");

        // Ejercicio 1: conversión a grafo dirigido
        Grafo dirigido = g.convertirADirigido();

        System.out.println(
                "1) Conversion a grafo dirigido:"
        );

        System.out.println(
                "   Original: " + g.numAristas()
                + " aristas ("
                + g.numNoDirigidas() + " no dirigidas, "
                + g.numDirigidas() + " dirigidas)"
        );

        System.out.println(
                "   Dirigido: " + dirigido.numAristas()
                + " aristas (tipo: "
                + dirigido.tipoDireccion() + ")"
        );

        System.out.println(
                "   Cada conexion no dirigida se convierte "
                + "en dos rutas de un solo sentido."
        );

        // Ejercicio 2: búsqueda de ciclo
        List<String> ciclo = g.buscarCiclo();

        System.out.println(
                "2) Ciclo encontrado: "
                + (ciclo.isEmpty()
                    ? "ninguno"
                    : String.join(" -> ", ciclo))
        );

        // Ejercicio 3: cálculo de densidad
        System.out.printf(
                "3) Densidad = %.3f -> el grafo es %s "
                + "(umbral %.1f)%n",
                g.densidad(),
                g.esDenso() ? "denso" : "disperso",
                Grafo.UMBRAL_DENSIDAD
        );
    }
}
