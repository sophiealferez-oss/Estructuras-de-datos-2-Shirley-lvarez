# Guía 6 – Recorridos en árboles
## Objetivo
Implementar y comparar los recorridos Preorden, Inorden, Postorden y por niveles sobre un mismo árbol binario de búsqueda (BST).

## Situación real: catálogo de una tienda de tecnología
El árbol guarda el catálogo de productos de una tienda de tecnología. Cada nodo contiene un `Producto` (código, nombre, precio en COP y stock) y el BST se organiza por **código (SKU)**. Así, los recorridos tienen un significado práctico: listar el inventario, copiar el catálogo, borrarlo, o mostrarlo por niveles.

## Estructura de la carpeta
```
06-recorridos/
├── src/
│   ├── Producto.java    # producto del catálogo (Comparable por código)
│   ├── NodoArbol.java   # nodo genérico (dato, izquierdo, derecho)
│   ├── ArbolBST.java    # BST genérico + cuatro recorridos + eliminación segura
│   └── Main.java        # catálogo de 10 productos, recorridos y pruebas
├── salida.txt           # salida real de la ejecución
└── README.md
```

## Cómo compilar y ejecutar
```bash
javac -d out src/*.java
java -cp out Main
```

## Catálogo de 10 productos (por código)
Orden de inserción: `50, 30, 70, 20, 40, 60, 80, 10, 35, 65`

| Código | Producto   | Precio (COP) | Stock |
|--------|------------|--------------|-------|
| 10     | USB 32GB   | 25.000       | 40    |
| 20     | Cable HDMI | 18.000       | 50    |
| 30     | Mouse      | 45.000       | 30    |
| 35     | Parlante   | 150.000      | 9     |
| 40     | Audífonos  | 120.000      | 18    |
| 50     | Teclado    | 85.000       | 14    |
| 60     | Webcam     | 95.000       | 11    |
| 65     | Router     | 130.000      | 12    |
| 70     | Monitor    | 620.000      | 6     |
| 80     | Impresora  | 480.000      | 4     |

Forma del árbol resultante:
```
                Teclado(50)
              /             \
        Mouse(30)            Monitor(70)
        /       \            /         \
 Cable HDMI(20) Audífonos(40) Webcam(60) Impresora(80)
      /             /              \
 USB 32GB(10)  Parlante(35)      Router(65)
```

## Secuencias obtenidas

| Recorrido   | Orden de visita         | Secuencia (código) | Secuencia (producto) |
|-------------|-------------------------|--------------------|----------------------|
| Preorden    | raíz – izq – der        | 50 30 20 10 40 35 70 60 65 80 | Teclado, Mouse, Cable HDMI, USB 32GB, Audífonos, Parlante, Monitor, Webcam, Router, Impresora |
| Inorden     | izq – raíz – der        | 10 20 30 35 40 50 60 65 70 80 | USB 32GB, Cable HDMI, Mouse, Parlante, Audífonos, Teclado, Webcam, Router, Monitor, Impresora |
| Postorden   | izq – der – raíz        | 10 20 35 40 30 65 60 80 70 50 | USB 32GB, Cable HDMI, Parlante, Audífonos, Mouse, Router, Webcam, Impresora, Monitor, Teclado |
| Por niveles | nivel por nivel (Queue) | 50 30 70 20 40 60 80 10 35 65 | Teclado, Mouse, Monitor, Cable HDMI, Audífonos, Webcam, Impresora, USB 32GB, Parlante, Router |

## Interpretación de las salidas
- **Inorden** entrega el catálogo ordenado por código. Se verificó en `Main` comparándolo con una `List` ordenada con `Collections.sort`: son idénticos. Ocurre por la propiedad del BST (lo de la izquierda es menor y lo de la derecha es mayor). Con esta salida se genera el reporte de inventario, con un valor total de $16.195.000 COP.
- **Preorden** empieza siempre por la raíz (Teclado) y cada padre aparece antes que sus hijos, por lo que la secuencia permite reconstruir el mismo árbol.
- **Postorden** termina siempre en la raíz (Teclado) y cada producto aparece después de sus dos hijos.
- **Por niveles** recorre por profundidad creciente (Teclado / Mouse, Monitor / Cable HDMI, Audífonos, Webcam, Impresora / USB 32GB, Parlante, Router) usando una `Queue`: se saca un nodo y se encolan sus hijos.
- Los cuatro recorridos visitan exactamente los mismos 10 productos; solo cambia el orden.

## Casos de uso por recorrido
- **Preorden:** copiar o exportar el catálogo conservando su estructura (guardar la raíz primero permite reconstruir el árbol idéntico en otro sistema).
- **Inorden:** generar el reporte de inventario ordenado por código, o una lista de precios.
- **Postorden:** vaciar el catálogo de forma segura, o calcular totales que dependen de los hijos (por ejemplo, el valor de un subárbol de productos).
- **Por niveles:** mostrar el catálogo por niveles, calcular la altura del árbol o encontrar el producto más cercano a la raíz que cumple una condición.

## ¿Qué recorrido usar para eliminar un árbol completo?
**Postorden.** Visita primero el hijo izquierdo, luego el derecho y por último el padre. Así, cuando se libera un nodo sus hijos ya fueron procesados y no se pierde la referencia a ningún subárbol. Con Preorden o Inorden se liberaría el padre antes de haber recorrido todos sus hijos, y si se cortan los enlaces antes de tiempo, quedarían subárboles inalcanzables. Se implementó en `ArbolBST.eliminarTodo()`, y la salida confirma el orden de liberación `USB 32GB, Cable HDMI, Parlante, Audífonos, Mouse, Router, Webcam, Impresora, Monitor, Teclado`, dejando la raíz en `null`.

## Pruebas realizadas (13/13 correctas)
Las pruebas están en `Main` y su resultado en `salida.txt`:
1. Secuencia exacta de los cuatro recorridos.
2. Inorden igual a la lista ordenada.
3. Preorden y por niveles visitan 10 nodos.
4. Un código duplicado no se inserta dos veces.
5. Árbol vacío (sin `NullPointerException`) en preorden y por niveles.
6. Árbol de un solo nodo.
7. Eliminación en orden postorden y árbol vacío al final.

## Capturas de ejecución
Agregar aquí las capturas de pantalla de la ejecución en VS Code (la salida de texto completa está en `salida.txt`).
