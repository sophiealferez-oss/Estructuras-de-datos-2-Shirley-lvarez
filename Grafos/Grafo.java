import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Deque;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Grafo G = (V, A) implementado con lista de adyacencia.
 *
 * <p>Soporta aristas dirigidas y no dirigidas en un mismo grafo (grafo
 * <b>mixto</b>), con pesos. Incluye operaciones para clasificarlo:
 * dirección, ponderación, conectividad, ciclos y densidad.</p>
 *
 * <p>Convención interna: una arista no dirigida (u, v) se almacena una sola vez
 * en la lista de aristas, pero genera <b>dos arcos</b> en la lista de
 * adyacencia (u → v y v → u). Una arista dirigida genera un solo arco.</p>
 */
class Grafo {

    /** Umbral de densidad: D &gt;= 0.5 se considera grafo denso. */
    static final double UMBRAL_DENSIDAD = 0.5;

    /** Arco de la lista de adyacencia: hacia dónde va y de qué arista proviene. */
    private static class Arco {
        final String vecino;
        final int idArista;

        Arco(String vecino, int idArista) {
            this.vecino = vecino;
            this.idArista = idArista;
        }
    }

    /** Conjunto V, indexado por nombre (conserva el orden de inserción). */
    private final Map<String, Vertice> vertices = new LinkedHashMap<>();

    /** Conjunto A, en orden de inserción. */
    private final List<Arista> aristas = new ArrayList<>();

    /** Lista de adyacencia: nombre del vértice -> arcos que salen de él. */
    private final Map<String, List<Arco>> adyacencia = new LinkedHashMap<>();

    // ------------------------------------------------------------------
    // Construcción del grafo
    // ------------------------------------------------------------------

    /**
     * Agrega un vértice al grafo.
     *
     * @return {@code true} si se agregó; {@code false} si ya existía uno con ese nombre
     */
    boolean agregarVertice(Vertice v) {
        if (vertices.containsKey(v.nombre)) {
            return false;
        }
        vertices.put(v.nombre, v);
        adyacencia.put(v.nombre, new ArrayList<>());
        return true;
    }

    /**
     * Agrega una arista al grafo. Ambos extremos deben existir previamente.
     *
     * @throws IllegalArgumentException si origen o destino no son vértices del grafo
     */
    void agregarArista(Arista a) {
        if (!vertices.containsKey(a.origen) || !vertices.containsKey(a.destino)) {
            throw new IllegalArgumentException("Vértice inexistente en la arista: " + a);
        }
        int id = aristas.size();
        aristas.add(a);
        adyacencia.get(a.origen).add(new Arco(a.destino, id));
        if (!a.dirigida) {
            adyacencia.get(a.destino).add(new Arco(a.origen, id));
        }
    }

    // ------------------------------------------------------------------
    // Consultas básicas
    // ------------------------------------------------------------------

    Collection<Vertice> getVertices() {
        return Collections.unmodifiableCollection(vertices.values());
    }

    List<Arista> getAristas() {
        return Collections.unmodifiableList(aristas);
    }

    int numVertices() {
        return vertices.size();
    }

    int numAristas() {
        return aristas.size();
    }

    /** Cantidad de aristas dirigidas. */
    int numDirigidas() {
        int c = 0;
        for (Arista a : aristas) {
            if (a.dirigida) c++;
        }
        return c;
    }

    /** Cantidad de aristas no dirigidas. */
    int numNoDirigidas() {
        return aristas.size() - numDirigidas();
    }

    /** Número de arcos reales: una dirigida aporta 1 y una no dirigida aporta 2. */
    int numArcos() {
        return numDirigidas() + 2 * numNoDirigidas();
    }

    /** Número de aristas que inciden en el vértice (sin importar el sentido). */
    int gradoIncidente(String nombre) {
        int c = 0;
        for (Arista a : aristas) {
            if (a.incideEn(nombre)) c++;
        }
        return c;
    }

    /** Grado de salida: arcos que se pueden tomar desde el vértice. */
    int gradoSalida(String nombre) {
        return adyacencia.get(nombre).size();
    }

    /** Grado de entrada: arcos que llegan al vértice. */
    int gradoEntrada(String nombre) {
        int c = 0;
        for (List<Arco> lista : adyacencia.values()) {
            for (Arco arco : lista) {
                if (arco.vecino.equals(nombre)) c++;
            }
        }
        return c;
    }

    /** Suma de los pesos de todas las aristas (cada arista cuenta una vez). */
    double pesoTotal() {
        double suma = 0;
        for (Arista a : aristas) {
            suma += a.peso;
        }
        return suma;
    }

    // ------------------------------------------------------------------
    // Clasificación formal del grafo
    // ------------------------------------------------------------------

    /** @return "dirigido", "no dirigido" o "mixto" según las aristas existentes. */
    String tipoDireccion() {
        int d = numDirigidas();
        if (d == 0) return "no dirigido";
        if (d == aristas.size()) return "dirigido";
        return "mixto";
    }

    /**
     * Un grafo es ponderado si sus aristas llevan un peso que las distingue;
     * aquí: al menos una arista tiene un peso diferente de 1.
     */
    boolean esPonderado() {
        for (Arista a : aristas) {
            if (a.peso != 1.0) return true;
        }
        return false;
    }

    /**
     * Densidad: D = arcos / (n · (n − 1)).
     *
     * <p>Para un grafo dirigido es |A| / (n(n−1)); para uno no dirigido es
     * 2|A| / (n(n−1)). En un grafo mixto se cuentan los arcos reales
     * (ver {@link #numArcos()}). Valores cercanos a 1 indican grafo denso.</p>
     */
    double densidad() {
        int n = vertices.size();
        if (n < 2) return 0.0;
        return (double) numArcos() / ((double) n * (n - 1));
    }

    /** @return {@code true} si la densidad supera el umbral {@link #UMBRAL_DENSIDAD}. */
    boolean esDenso() {
        return densidad() >= UMBRAL_DENSIDAD;
    }

    /**
     * Conexo (débilmente conexo): existe un camino entre todo par de vértices
     * si se ignora el sentido de las aristas.
     */
    boolean esConexo() {
        if (vertices.isEmpty()) return true;
        String inicio = vertices.keySet().iterator().next();
        return alcanzables(inicio, false, true).size() == vertices.size();
    }

    /**
     * Fuertemente conexo: desde cualquier vértice se puede llegar a cualquier
     * otro respetando el sentido de las aristas. Se verifica que el primer
     * vértice alcance a todos y que todos alcancen al primero.
     */
    boolean esFuertementeConexo() {
        if (vertices.isEmpty()) return true;
        String inicio = vertices.keySet().iterator().next();
        boolean ida = alcanzables(inicio, false, false).size() == vertices.size();
        boolean vuelta = alcanzables(inicio, true, false).size() == vertices.size();
        return ida && vuelta;
    }

    /**
     * Búsqueda en anchura (BFS) desde un vértice.
     *
     * @param inicio           vértice de partida
     * @param invertir         si es {@code true}, recorre las aristas en sentido contrario
     * @param ignorarDireccion si es {@code true}, trata todas las aristas como no dirigidas
     * @return conjunto de vértices alcanzados (incluye el inicial)
     */
    private Set<String> alcanzables(String inicio, boolean invertir, boolean ignorarDireccion) {
        Set<String> visitados = new LinkedHashSet<>();
        Deque<String> cola = new ArrayDeque<>();
        visitados.add(inicio);
        cola.add(inicio);

        while (!cola.isEmpty()) {
            String u = cola.poll();
            for (Arista a : aristas) {
                boolean salePorOrigen = a.origen.equals(u);
                boolean salePorDestino = a.destino.equals(u);
                String siguiente = null;

                if (ignorarDireccion) {
                    if (salePorOrigen) siguiente = a.destino;
                    else if (salePorDestino) siguiente = a.origen;
                } else if (!invertir) {
                    if (salePorOrigen) siguiente = a.destino;
                    else if (salePorDestino && !a.dirigida) siguiente = a.origen;
                } else {
                    if (salePorDestino) siguiente = a.origen;
                    else if (salePorOrigen && !a.dirigida) siguiente = a.destino;
                }

                if (siguiente != null && visitados.add(siguiente)) {
                    cola.add(siguiente);
                }
            }
        }
        return visitados;
    }

    // ------------------------------------------------------------------
    // Ciclos
    // ------------------------------------------------------------------

    /**
     * Busca un ciclo mediante búsqueda en profundidad (DFS).
     *
     * <p>Se detecta un ciclo cuando, desde el vértice actual, se llega a un
     * vértice que ya está en el camino en construcción. Para no confundir una
     * arista no dirigida con un ciclo trivial (ir y volver por la misma
     * arista), no se vuelve a recorrer la arista por la que se llegó.</p>
     *
     * @return lista de vértices del ciclo (el primero se repite al final),
     *         o lista vacía si el grafo es acíclico
     */
    List<String> buscarCiclo() {
        Set<String> visitados = new LinkedHashSet<>();
        for (String v : vertices.keySet()) {
            if (!visitados.contains(v)) {
                List<String> ciclo = dfsCiclo(v, -1, visitados, new ArrayList<>());
                if (ciclo != null) return ciclo;
            }
        }
        return Collections.emptyList();
    }

    private List<String> dfsCiclo(String actual, int aristaEntrada,
                                  Set<String> visitados, List<String> camino) {
        visitados.add(actual);
        camino.add(actual);

        for (Arco arco : adyacencia.get(actual)) {
            if (arco.idArista == aristaEntrada) continue; // no volver por la misma arista

            int posicion = camino.indexOf(arco.vecino);
            if (posicion >= 0) { // el vecino ya está en el camino actual -> ciclo
                List<String> ciclo = new ArrayList<>(camino.subList(posicion, camino.size()));
                ciclo.add(arco.vecino);
                return ciclo;
            }
            if (!visitados.contains(arco.vecino)) {
                List<String> r = dfsCiclo(arco.vecino, arco.idArista, visitados, camino);
                if (r != null) return r;
            }
        }
        camino.remove(camino.size() - 1); // retroceso (backtracking)
        return null;
    }

    /** @return {@code true} si el grafo contiene al menos un ciclo. */
    boolean esCiclico() {
        return !buscarCiclo().isEmpty();
    }

    // ------------------------------------------------------------------
    // Ejercicio: conversión a grafo dirigido
    // ------------------------------------------------------------------

    /**
     * Devuelve un grafo <b>dirigido</b> equivalente: cada arista no dirigida
     * (u, v) se reemplaza por dos aristas dirigidas u → v y v → u con el mismo
     * peso; las aristas ya dirigidas se copian sin cambios.
     *
     * <p>Cambio semántico: antes la arista era una relación simétrica (una sola
     * ruta de doble vía); ahora son dos relaciones independientes, cada una con
     * su propio sentido, que podrían tener pesos distintos o eliminarse por
     * separado.</p>
     */
    Grafo convertirADirigido() {
        Grafo g = new Grafo();
        for (Vertice v : vertices.values()) {
            g.agregarVertice(v);
        }
        for (Arista a : aristas) {
            g.agregarArista(new Arista(a.origen, a.destino, a.peso, true));
            if (!a.dirigida) {
                g.agregarArista(new Arista(a.destino, a.origen, a.peso, true));
            }
        }
        return g;
    }
}
