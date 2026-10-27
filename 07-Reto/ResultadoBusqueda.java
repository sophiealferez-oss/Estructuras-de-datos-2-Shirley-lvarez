import java.util.List;

/**
 * Resultado de una búsqueda en el BST: el producto encontrado (o null),
 * cuántos nodos se compararon y el camino de códigos visitados.
 */
public class ResultadoBusqueda {
    private final Producto producto;
    private final int comparaciones;
    private final List<Integer> camino;

    public ResultadoBusqueda(Producto producto, int comparaciones, List<Integer> camino) {
        this.producto = producto;
        this.comparaciones = comparaciones;
        this.camino = camino;
    }

    public boolean isEncontrado() { return producto != null; }
    public Producto getProducto() { return producto; }
    public int getComparaciones() { return comparaciones; }
    public List<Integer> getCamino() { return camino; }

    /** Camino legible, por ejemplo "50 -> 70 -> 60 -> 65". */
    public String caminoTexto() {
        if (camino.isEmpty()) return "(árbol vacío)";
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < camino.size(); i++) {
            if (i > 0) sb.append(" -> ");
            sb.append(camino.get(i));
        }
        return sb.toString();
    }
}
