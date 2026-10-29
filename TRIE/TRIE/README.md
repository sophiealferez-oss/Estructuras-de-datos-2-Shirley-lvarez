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
├── Main.java       # Diccionario de 40 palabras, 5 prefijos y 11 pruebas automáticas
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

Diccionario cargado: **40 palabras** (mínimo exigido: 30). Prefijos probados: `pro`, `est`, `ar`,
`co`, `di` y uno inexistente (`xyz`).

```
=== Mini motor de autocompletado (Trie) ===
Palabras cargadas: 40

--- Sugerencias (maximo 5) ---
Prefijo "pro" -> [procesador, proceso, producto, programa, programacion]
Prefijo "est" -> [estado, estandar, estrategia, estructura, estudiante]
Prefijo "ar" -> [arbol, archivo, arreglo]
Prefijo "co" -> [codigo, cola, compilador, consulta]
Prefijo "di" -> [diccionario, directorio, diseno]
Prefijo "xyz" -> (sin sugerencias)

--- Pruebas automaticas ---
[OK]    buscar("nodo") es true
[OK]    buscar("NODO") es true (ignora mayusculas)
[OK]    buscar("pro") es false (solo prefijo)
[OK]    buscar("programas") es false
[OK]    empiezaCon("prog") es true
[OK]    empiezaCon("zz") es false
[OK]    sugerir("pro") devuelve 5 resultados
[OK]    sugerir("pro", 3) devuelve 3 resultados
[OK]    sugerir("pila") devuelve [pila]
[OK]    sugerir("xyz") devuelve lista vacia
[OK]    insertar duplicado no cambia el tamano (40)

Resultado: 11/11 pruebas correctas
```

Observe que `pro` tiene 9 palabras en el diccionario, pero se devuelven solo las **5** primeras en
orden alfabético, cumpliendo el límite máximo pedido en la guía.

## Posibles mejoras

- Ordenar sugerencias por frecuencia de uso en lugar de orden alfabético.
- Operación `eliminar(String)`.
- Soporte para tildes y la letra ñ (normalización con `java.text.Normalizer`).
- Pruebas con JUnit en lugar de verificaciones manuales en `Main`.
