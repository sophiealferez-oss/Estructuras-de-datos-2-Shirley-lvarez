public class Main {

    public static void main(String[] args) {

        // ============================================================
        // 1. CREACIÓN DE LOS NODOS
        // ============================================================

        NodoGeneral<String> IUDC = new NodoGeneral<>("IUDC");

        // Nivel 1
        NodoGeneral<String> ingenieriadeSoftware = new NodoGeneral<>("Ingenieria de Software");

        NodoGeneral<String> veterinaria = new NodoGeneral<>("Veterinaria");

        NodoGeneral<String> IngenieríaIndustrial = new NodoGeneral<>("Ingeniería Industrial");

        // Nivel 2
        NodoGeneral<String> BasesDatos = new NodoGeneral<>("Bases de datos");

        NodoGeneral<String> Lenguajes2 = new NodoGeneral<>("Lenguajes de programacion II");

        NodoGeneral<String> AnatomiaAnimal = new NodoGeneral<>("Anatomia Animal");

        NodoGeneral<String> Calculo = new NodoGeneral<>("Calculo");

        // Nivel 3

        NodoGeneral<String> POO = new NodoGeneral<>("POO");

        NodoGeneral<String> LenguajesdeProgramacionJava = new NodoGeneral<>(" Lenguajes de Programacion Java");

        NodoGeneral<String> calculoIntegral = new NodoGeneral<>("calculo Integral");

        NodoGeneral<String> calculoDiferencial = new NodoGeneral<>("Calculo Diferencial");

        // Nivel 4
        NodoGeneral<String> NetBeans = new NodoGeneral<>("Netbeans");

        NodoGeneral<String> Derivadas = new NodoGeneral<>("Derivadas");

        // ============================================================
        // 2. CONSTRUCCIÓN DE LA JERARQUÍA
        // ============================================================

        IUDC.agregarHijo(ingenieriadeSoftware);
        IUDC.agregarHijo(veterinaria);
        IUDC.agregarHijo(IngenieríaIndustrial);

        ingenieriadeSoftware.agregarHijo(BasesDatos);
        ingenieriadeSoftware.agregarHijo(Lenguajes2);

        veterinaria.agregarHijo(AnatomiaAnimal);

        IngenieríaIndustrial.agregarHijo(Calculo);

        Lenguajes2.agregarHijo(POO);
        Lenguajes2.agregarHijo(LenguajesdeProgramacionJava);

        Calculo.agregarHijo(calculoIntegral);
        Calculo.agregarHijo(calculoDiferencial);

        LenguajesdeProgramacionJava.agregarHijo(Netbeans);
        calculoDiferencial.agregarHijo(Derivadas);

        // ============================================================
        // 3. MOSTRAR EL ÁRBOL
        // ============================================================

        System.out.println("=== ÁRBOL UNIVERSITARIO ===");
        MetricasArbol.mostrarArbol(universidad, 0);

        // ============================================================
        // 4. PROPIEDADES GENERALES
        // ============================================================

        System.out.println();
        System.out.println("=== PROPIEDADES DEL ÁRBOL ===");

        System.out.println(
                "Raíz: " + universidad.getDato());

        System.out.println(
                "Número total de nodos: "
                        + MetricasArbol.contarNodos(universidad));

        System.out.println(
                "Número de hojas: "
                        + MetricasArbol.contarHojas(universidad));

        System.out.println(
                "Grado del árbol: "
                        + MetricasArbol.gradoArbol(universidad));

        System.out.println(
                "Altura del árbol: "
                        + MetricasArbol.altura(universidad));

        System.out.println(
                "Número de niveles: "
                        + (MetricasArbol.altura(universidad) + 1));

        // ============================================================
        // 5. PROFUNDIDAD DE CINCO NODOS
        // ============================================================

        System.out.println();
        System.out.println("=== PROFUNDIDAD DE CINCO NODOS ===");

        mostrarProfundidad(universidad, ingenieria);
        mostrarProfundidad(universidad, sistemas);
        mostrarProfundidad(universidad, semilleroIA);
        mostrarProfundidad(universidad, administracion);
        mostrarProfundidad(universidad, cultura);

        // ============================================================
        // 6. GRADO DE NODOS
        // ============================================================

        System.out.println();
        System.out.println("=== GRADO DE ALGUNOS NODOS ===");

        mostrarGrado(universidad);
        mostrarGrado(ingenieria);
        mostrarGrado(sistemas);
        mostrarGrado(software);
        mostrarGrado(semilleroIA);

        // ============================================================
        // 7. TABLA COMPLETA DE MÉTRICAS
        // ============================================================

        System.out.println();
        System.out.println("=== TABLA DE MÉTRICAS ===");
        MetricasArbol.mostrarTablaMetricas(universidad);

        // ============================================================
        // 8. DOS CAMINOS Y SU LONGITUD
        // ============================================================

        System.out.println();
        System.out.println("=== CAMINOS ===");

        mostrarCamino(universidad, semilleroDatos);
        mostrarCamino(universidad, contaduria);

        // ============================================================
        // 9. COMPARACIÓN MANUAL VS. CÓDIGO
        // ============================================================

        System.out.println();
        System.out.println("=== COMPARACIÓN ===");

        int alturaManual = 3;
        int alturaCalculada = MetricasArbol.altura(universidad);

        System.out.println(
                "Altura calculada manualmente: "
                        + alturaManual);

        System.out.println(
                "Altura calculada por Java: "
                        + alturaCalculada);

        System.out.println(
                alturaManual == alturaCalculada
                        ? "Resultado: COINCIDEN."
                        : "Resultado: REVISAR CÁLCULO.");
    }

    private static void mostrarProfundidad(
            NodoGeneral<?> raiz,
            NodoGeneral<?> nodo) {

        System.out.printf(
                "%-28s -> %d%n",
                nodo.getDato(),
                MetricasArbol.profundidad(raiz, nodo));
    }

    private static void mostrarGrado(
            NodoGeneral<?> nodo) {

        System.out.printf(
                "%-28s -> %d%n",
                nodo.getDato(),
                MetricasArbol.gradoNodo(nodo));
    }

    private static void mostrarCamino(
            NodoGeneral<?> raiz,
            NodoGeneral<?> destino) {

        var ruta = MetricasArbol.camino(raiz, destino);

        System.out.println(
                MetricasArbol.rutaComoTexto(ruta));

        System.out.println(
                "Longitud: "
                        + MetricasArbol.longitudCamino(
                                raiz,
                                destino)
                        + " aristas");
    }
}