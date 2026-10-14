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

                LenguajesdeProgramacionJava.agregarHijo(NetBeans);
                calculoDiferencial.agregarHijo(Derivadas);

                // ============================================================
                // 3. MOSTRAR EL ÁRBOL
                // ============================================================

                System.out.println("=== ÁRBOL UNIVERSITARIO ===");
                metricasarbol.mostrarArbol(IUDC, 0);

                // ============================================================
                // 4. PROPIEDADES GENERALES
                // ============================================================

                System.out.println();
                System.out.println("=== PROPIEDADES DEL ÁRBOL ===");

                System.out.println(
                                "Raíz: " + IUDC.getDato());

                System.out.println(
                                "Número total de nodos: "
                                                + metricasarbol.contarNodos(IUDC));

                System.out.println(
                                "Número de hojas: "
                                                + metricasarbol.contarHojas(IUDC));

                System.out.println(
                                "Grado del árbol: "
                                                + metricasarbol.gradoArbol(IUDC));

                System.out.println(
                                "Altura del árbol: "
                                                + metricasarbol.altura(IUDC));

                System.out.println(
                                "Número de niveles: "
                                                + (metricasarbol.altura(IUDC) + 1));

                // ============================================================
                // 5. PROFUNDIDAD DE CINCO NODOS
                // ============================================================

                System.out.println();
                System.out.println("=== PROFUNDIDAD DE CINCO NODOS ===");

                mostrarProfundidad(IUDC, ingenieriadeSoftware);
                mostrarProfundidad(IUDC, BasesDatos);
                mostrarProfundidad(IUDC, Lenguajes2);
                mostrarProfundidad(IUDC, AnatomiaAnimal);
                mostrarProfundidad(IUDC, Derivadas);

                // ============================================================
                // 6. GRADO DE NODOS
                // ============================================================

                System.out.println();
                System.out.println("=== GRADO DE ALGUNOS NODOS ===");

                mostrarGrado(IUDC);
                mostrarGrado(ingenieriadeSoftware);
                mostrarGrado(veterinaria);
                mostrarGrado(IngenieríaIndustrial);
                mostrarGrado(LenguajesdeProgramacionJava);

                // ============================================================
                // 7. TABLA COMPLETA DE MÉTRICAS
                // ============================================================

                System.out.println();
                System.out.println("=== TABLA DE MÉTRICAS ===");
                metricasarbol.mostrarTablaMetricas(IUDC);

                // ============================================================
                // 8. DOS CAMINOS Y SU LONGITUD
                // ============================================================

                System.out.println();
                System.out.println("=== CAMINOS ===");

                mostrarCamino(IUDC, Calculo);
                mostrarCamino(IUDC, POO);

                // ============================================================
                // 9. COMPARACIÓN MANUAL VS. CÓDIGO
                // ============================================================

                System.out.println();
                System.out.println("=== COMPARACIÓN ===");

                int alturaManual = 3;
                int alturaCalculada = metricasarbol.altura(IUDC);

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
                                metricasarbol.profundidad(raiz, nodo));
        }

        private static void mostrarGrado(
                        NodoGeneral<?> nodo) {

                System.out.printf(
                                "%-28s -> %d%n",
                                nodo.getDato(),
                                metricasarbol.gradoNodo(nodo));
        }

        private static void mostrarCamino(
                        NodoGeneral<?> raiz,
                        NodoGeneral<?> destino) {

                var ruta = metricasarbol.camino(raiz, destino);

                System.out.println(
                                metricasarbol.rutaComoTexto(ruta));

                System.out.println(
                                "Longitud: "
                                                + metricasarbol.longitudCamino(
                                                                raiz,
                                                                destino)
                                                + " aristas");
        }
}