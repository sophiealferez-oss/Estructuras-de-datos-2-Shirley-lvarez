# Guía 11 – Trie: mini motor de autocompletado

**Asignatura:** Estructura de Datos II · Ingeniería de Software / Sistemas · Semestre IV
**Lenguaje:** Java (JDK 11 o superior)

## Objetivo

Construir una estructura **Trie** propia para insertar palabras, buscar términos completos,
verificar prefijos y sugerir palabras (máximo 5 resultados) a partir de un prefijo.

## Estructura del proyecto

```
11-trie-autocompletado/
├── NodoTrie.java   # Nodo: Map<Character,NodoTrie> hijos + boolean finPalabra
├── Trie.java       # insertar, buscar, empiezaCon, sugerir
├── Main.java       # Diccionario de 30 palabras, 5 prefijos (+1 inexistente) y 11 pruebas automáticas
├── salida.txt      # Salida real de la ejecución
└── README.md
```

## Cómo compilar y ejecutar

```bash
javac *.java
java Main          # demostración + pruebas
java Main -i       # además, modo interactivo (escriba un prefijo; "salir" termina)
```

## Diseño

### NodoTrie
Cada nodo representa una transición de carácter y contiene:

- `Map<Character, NodoTrie> hijos`: nodos hijos indexados por carácter. Se usa `TreeMap`
  (en lugar de `HashMap`) para recorrer los hijos en orden alfabético, de modo que las
  sugerencias salgan siempre en el mismo orden y las pruebas sean verificables.
- `boolean finPalabra`: indica que el camino desde la raíz hasta el nodo forma una palabra completa.

### Trie
| Método | Descripción |
|---|---|
| `insertar(String)` | Desciende creando los nodos faltantes (con `computeIfAbsent`) y marca `finPalabra`. Normaliza a minúsculas e ignora duplicados y entradas vacías. |
| `buscar(String)` | `true` solo si la palabra completa fue insertada (un simple prefijo no cuenta). |
| `empiezaCon(String)` | `true` si existe al menos una palabra con ese prefijo. |
| `sugerir(String)` | Hasta **5** palabras que comienzan con el prefijo. |
| `sugerir(String, int)` | Igual, con límite configurable. |

`buscar` y `empiezaCon` comparten el método privado `recorrer(cadena)`, que sigue los caracteres
desde la raíz y devuelve el nodo final (o `null` si el camino no existe).

### Autocompletado
1. Se llega al **nodo final del prefijo** con `recorrer`.
2. Desde ese nodo se hace un **DFS recursivo con backtracking** (`recolectar`), acumulando en un
   `StringBuilder` y agregando la palabra cada vez que `finPalabra` es `true`.
3. El recorrido se **corta en cuanto se alcanzan 5 resultados**, sin explorar el resto del subárbol.

## Complejidad respecto a la longitud de la cadena

Sea **L** la longitud de la palabra o prefijo consultado y **N** el número de palabras almacenadas.

| Operación | Tiempo |
|---|---|
| `insertar` | O(L) |
| `buscar` | O(L) |
| `empiezaCon` | O(L) |
| `sugerir` | O(L + K), K = nodos visitados bajo el prefijo hasta completar el límite |

El costo depende de la **longitud de la cadena, no de cuántas palabras hay en el diccionario**.
En espacio, el Trie ocupa O(total de caracteres) en el peor caso, pero **comparte los prefijos**
(por ejemplo `programa`, `programacion` y `programador` comparten los nodos de `programa`).

### Comparación con revisar secuencialmente una lista de 1000 palabras

Buscar un prefijo en una lista requiere comparar el prefijo contra cada palabra: **O(N·L)**, es
decir, hasta ~1000 comparaciones por consulta. En el Trie se avanzan solo **L pasos**, sin importar
si el diccionario tiene 1000 o 1.000.000 de palabras. Además, en la lista hay que recorrerla completa
para saber que *no* existe ninguna coincidencia, mientras que el Trie se detiene en el primer
carácter que falta. Esa es la razón por la que el Trie es adecuado para diccionarios,
validación de prefijos y autocompletado. La contrapartida es un mayor consumo de memoria
(un objeto `NodoTrie` y un mapa por carácter).

## Evidencia de ejecución

Diccionario cargado: **30 palabras** de veterinaria (mínimo exigido: 30):

> anestesia, antibiótico, antiparasitario, bozal, cánido, celo, castración, cirugía,
> clinorresistencia, desparasitación, diagnóstico, ecografía, eutanasia, felino, garrapata,
> gestación, histopatología, inmunización, laceración, microchip, miosis, necropsia,
> otomicroscopía, patología, pronóstico, quórum, rabia, radiografía, sonda, vacuna

Prefijos probados: `cir`, `ane`, `anti`, `vac`, `qu` y uno inexistente (`xen`).

```
=== Mini motor de autocompletado (Trie) ===
Palabras cargadas: 30

--- Sugerencias (maximo 5) ---
Prefijo "cir" -> [cirugía]
Prefijo "ane" -> [anestesia]
Prefijo "anti" -> [antibiótico, antiparasitario]
Prefijo "vac" -> [vacuna]
Prefijo "qu" -> [quórum]
Prefijo "xen" -> (sin sugerencias)

--- Pruebas automaticas ---
[OK]    buscar("cirugía") es true
[OK]    buscar("RABIA") es true (ignora mayusculas)
[OK]    buscar("vac") es false (solo prefijo)
[OK]    buscar("antibióticos") es false
[OK]    empiezaCon("ane") es true
[OK]    empiezaCon("aa") es false
[OK]    sugerir("anti") devuelve 2 resultados
[OK]    sugerir("an", 3) devuelve 3 resultados
[OK]    sugerir("radiografía") devuelve [radiografía]
[OK]    sugerir("xen") devuelve lista vacia
[OK]    insertar duplicado no cambia el tamano (30)

Resultado: 11/11 pruebas correctas
```

Observe que `anti` comparte el camino `a → n → t → i` y desde ahí se divide en dos ramas
(`antibiótico` y `antiparasitario`). Las sugerencias salen en orden alfabético gracias al `TreeMap`;
como este compara por código Unicode, las vocales con tilde quedan después de la `z`
(por ejemplo, bajo `c` la rama `á` de `cánido` es la última). La sobrecarga `sugerir(prefijo, limite)`
permite fijar un tope distinto de 5, como en la prueba `sugerir("an", 3)`.

## Posibles mejoras

- Ordenar sugerencias por frecuencia de uso en lugar de orden alfabético.
- Operación `eliminar(String)`.
- Soporte para tildes y la letra ñ (normalización con `java.text.Normalizer`).
- Pruebas con JUnit en lugar de verificaciones manuales en `Main`.
