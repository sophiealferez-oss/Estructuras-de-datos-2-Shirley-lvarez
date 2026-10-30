/**
 * Representa una arista del grafo, es decir, una ruta entre dos ciudades.
 *
 * <p>Se respeta la estructura del código base de la guía (origen, destino,
 * peso, dirigida) y se agregan métodos auxiliares y validaciones.</p>
 *
 * <ul>
 *   <li><b>No dirigida</b> ({@code dirigida = false}): la ruta se puede recorrer
 *       en ambos sentidos (carretera de doble vía).</li>
 *   <li><b>Dirigida</b> ({@code dirigida = true}): la ruta solo se recorre de
 *       {@code origen} hacia {@code destino} (ruta expresa de un solo sentido).</li>
 *   <li><b>Peso</b>: distancia aproximada en kilómetros.</li>
 * </ul>
 */
class Arista {

    /** Nombre del vértice de salida. */
    String origen;

    /** Nombre del vértice de llegada. */
    String destino;

    /** Peso de la arista (distancia aproximada en km). */
    double peso;

    /** {@code true} si la arista solo se recorre de origen a destino. */
    boolean dirigida;

    /**
     * Crea una arista.
     *
     * @param o   nombre del vértice origen
     * @param d   nombre del vértice destino
     * @param p   peso (distancia en km); debe ser positivo
     * @param dir {@code true} si es dirigida, {@code false} si es no dirigida
     */
    Arista(String o, String d, double p, boolean dir) {
        if (o == null || d == null) {
            throw new IllegalArgumentException("Origen y destino son obligatorios");
        }
        if (p <= 0) {
            throw new IllegalArgumentException("El peso debe ser positivo: " + p);
        }
        origen = o;
        destino = d;
        peso = p;
        dirigida = dir;
    }

    /**
     * Devuelve el extremo opuesto al vértice dado.
     *
     * @param nombre nombre de uno de los extremos de la arista
     * @return el otro extremo, o {@code null} si el vértice no pertenece a la arista
     */
    String otroExtremo(String nombre) {
        if (origen.equals(nombre)) return destino;
        if (destino.equals(nombre)) return origen;
        return null;
    }

    /** Indica si la arista toca al vértice dado (como origen o destino). */
    boolean incideEn(String nombre) {
        return origen.equals(nombre) || destino.equals(nombre);
    }

    @Override
    public String toString() {
        String flecha = dirigida ? " --> " : " --- ";
        return origen + flecha + destino + " [" + peso + " km]";
    }
}
