import java.util.List;
import java.util.Scanner;

/**
 * Programa de prueba del mini motor de autocompletado.
 * Carga un diccionario de 30 palabras, ejecuta pruebas verificables
 * y ofrece un modo interactivo (opcional: java Main -i).
 */
public class Main {

    private static final String[] DICCIONARIO = {"Anestesia", "Antibiótico", "Antiparasitario", "Bozal", "Cánido", 
    "Celo", "Castración", "Cirugía", "Clinorresistencia", "Desparasitación", "Diagnóstico", "Ecografía", "Eutanasia", 
    "Felino", "Garrapata", "Gestación", "Histopatología", "Inmunización", "Laceración", "Microchip", "Miosis", "Necropsia", 
    "Otomicroscopía", "Patología", "Pronóstico", "Quórum", "Rabia", "Radiografía", "Sonda", "Vacuna"};

    private static int pruebasOk = 0;
    private static int pruebasTotal = 0;

    public static void main(String[] args) {
        Trie trie = new Trie();
        for (String palabra : DICCIONARIO) {
            trie.insertar(palabra);
        }
        System.out.println("=== Mini motor de autocompletado (Trie) ===");
        System.out.println("Palabras cargadas: " + trie.tamanio());

        // 1) Autocompletado con 5 prefijos (+ 1 inexistente)
        System.out.println("\n--- Sugerencias (maximo 5) ---");
        String[] prefijos = {"cir", "ane", "anti", "vac", "qu", "xen"};
        for (String p : prefijos) {
            List<String> sug = trie.sugerir(p);
            System.out.printf("Prefijo \"%s\" -> %s%n", p, sug.isEmpty() ? "(sin sugerencias)" : sug);
        }

        // 2) Pruebas verificables
        System.out.println("\n--- Pruebas automaticas ---");
        verificar("buscar(\"cirugía\") es true", trie.buscar("cirugía"));
        verificar("buscar(\"RABIA\") es true (ignora mayusculas)", trie.buscar("RABIA"));
        verificar("buscar(\"vac\") es false (solo prefijo)", !trie.buscar("vac"));
        verificar("buscar(\"antibióticos\") es false", !trie.buscar("antibióticos"));
        verificar("empiezaCon(\"ane\") es true", trie.empiezaCon("ane"));
        verificar("empiezaCon(\"aa\") es false", !trie.empiezaCon("aa"));
        verificar("sugerir(\"anti\") devuelve 2 resultados", trie.sugerir("anti").size() == 2);
        verificar("sugerir(\"an\", 3) devuelve 3 resultados", trie.sugerir("an", 3).size() == 3);
        verificar("sugerir(\"radiografía\") devuelve [radiografía]", trie.sugerir("radiografía").equals(List.of("radiografía")));
        verificar("sugerir(\"xen\") devuelve lista vacia", trie.sugerir("xen").isEmpty());
        trie.insertar("cirugía"); // duplicado
        verificar("insertar duplicado no cambia el tamano (30)", trie.tamanio() == 30);
        System.out.printf("%nResultado: %d/%d pruebas correctas%n", pruebasOk, pruebasTotal);

        // 3) Modo interactivo opcional
        if (args.length > 0 && args[0].equals("-i")) {
            modoInteractivo(trie);
        }
    }

    private static void verificar(String descripcion, boolean condicion) {
        pruebasTotal++;
        if (condicion) pruebasOk++;
        System.out.println((condicion ? "[OK]    " : "[FALLA] ") + descripcion);
    }

    private static void modoInteractivo(Trie trie) {
        System.out.println("\nModo interactivo. Escriba un prefijo (o 'salir').");
        try (Scanner sc = new Scanner(System.in)) {
            while (true) {
                System.out.print("> ");
                if (!sc.hasNextLine()) break;
                String linea = sc.nextLine().trim();
                if (linea.equalsIgnoreCase("salir")) break;
                System.out.println(trie.sugerir(linea));
            }
        }
    }
}
