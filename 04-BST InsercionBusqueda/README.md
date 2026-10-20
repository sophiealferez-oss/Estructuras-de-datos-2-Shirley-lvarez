# Guía 4 – BST I: inserción y búsqueda

**Asignatura:** Estructura de Datos II  
**Programas:** Ingeniería de Software – Ingeniería de Sistemas  
**Semestre:** IV  
**Ecosistema:** Java / GitHub

## Objetivo

Implementar inserción y búsqueda recursiva en un Árbol Binario de Búsqueda y analizar su eficiencia.

## Propiedad BST

- menor que el nodo actual → izquierda;
- mayor o igual → derecha.

## Ejercicio base

Valores:

```text
50, 30, 70, 20, 40, 60, 80
```

Árbol esperado:

```text
        50
       /  \
     30    70
    / \    / \
   20 40  60 80
```

Buscar `60` recorre:

```text
50 -> 70 -> 60
```

Buscar `65` recorre:

```text
50 -> 70 -> 60 -> null
```

## Archivos

- `NodoArbol.java`
- `ResultadoBusqueda.java`
- `ArbolBST.java`
- `Main.java`
- `salida_esperada.txt`
- `comparacion_bst.csv`

## Compilar

```bash
javac NodoArbol.java ResultadoBusqueda.java ArbolBST.java Main.java
```

## Ejecutar

```bash
java Main
```

## Análisis esperado

Un BST razonablemente balanceado puede aproximar sus búsquedas e inserciones a `O(log n)`.  
Si los datos se insertan ya ordenados, el árbol puede degenerarse y su altura acercarse a `n - 1`, por lo que las operaciones pueden acercarse a `O(n)`.
