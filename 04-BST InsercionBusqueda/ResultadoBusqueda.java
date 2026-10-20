/**
 * Guarda el resultado de una búsqueda:
 * encontrado, número de comparaciones y camino recorrido.
 */
public class ResultadoBusqueda {
    private final boolean encontrado;
    private final int comparaciones;
    private final String camino;

    public ResultadoBusqueda(boolean encontrado, int comparaciones, String camino) {
        this.encontrado = encontrado;
        this.comparaciones = comparaciones;
        this.camino = camino;
    }

    public boolean isEncontrado() { return encontrado; }
    public int getComparaciones() { return comparaciones; }
    public String getCamino() { return camino; }

    @Override
    public String toString() {
        return "encontrado=" + encontrado
                + ", comparaciones=" + comparaciones
                + ", camino=" + camino;
    }
}
