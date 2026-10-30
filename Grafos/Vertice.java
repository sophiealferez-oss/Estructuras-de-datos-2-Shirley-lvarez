/**
 * Representa un vértice (nodo) del grafo.
 *
 * <p>En el dominio elegido (transporte), cada vértice es una ciudad que actúa
 * como terminal o estación de una red de rutas terrestres de Colombia.</p>
 *
 * <p>Formalmente, el conjunto V del grafo G = (V, A) es el conjunto de objetos
 * {@code Vertice} registrados en {@link Grafo}.</p>
 */
class Vertice {

    /** Identificador único del vértice (se usa como llave en las aristas). */
    String nombre;

    /** Departamento al que pertenece la ciudad (dato descriptivo adicional). */
    String departamento;

    /**
     * Crea un vértice.
     *
     * @param nombre       nombre único de la ciudad
     * @param departamento departamento donde se ubica
     */
    Vertice(String nombre, String departamento) {
        if (nombre == null || nombre.isBlank()) {
            throw new IllegalArgumentException("El nombre del vértice no puede ser vacío");
        }
        this.nombre = nombre;
        this.departamento = departamento;
    }

    @Override
    public String toString() {
        return nombre + " (" + departamento + ")";
    }
}
