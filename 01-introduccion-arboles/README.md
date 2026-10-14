DOCUMENTACION ORGANIGRAMA

Para esta actividad hemos elegido el escenario de organigrama, el cual se denomina como una estructura jerárquica. La estructura jerárquica consiste de un elemento principal arriba (la raíz) y de él salen otros (hijos), y de esos otros hijos salen más. Por ejemplo: organigramas olas carpetas del computador. Un Ârbol es el modelo que representa a la estructura jeràrquica.

¿Cómo nos ayudan los árboles?
Nos ayudan organizando elementos en niveles, de lo general a lo específico.

También está la estructura lineal, que quiere decir que los elementos van uno detrás de otros, siguiendo una secuencia. Las características de cada elemento es que tiene un anterior y un siguiente. Podemos referenciar, en este caso, a las listas, pilas, colas y arreglos.

Diferencias entre la estructura jerárquica y la estructura lineal:

En la lineal, cada elemento va después de otro; en la estructura jerárquica, no.
En la jerárquica, con el ejemplo del árbol, se guardan elementos y se conoce a quién pertenece. En la estructura lineal, no.

Teniendo en cuenta lo que facilitan los árboles, podemos destacar otras ventajas tales como:

Agiliza la búsqueda de los elementos.
Se pueden descartar ramas enteras al buscar en vez de revisar todo uno por uno.

Hay 2 reglas que son de lógica:

Un nodo (elemento) puede tener varios hijos.
Cada nodo (elemento) tiene un solo padre, salvo la raíz (elemento principal), que NO tiene NINGUNO.

Entonces, ¿qué ocurre cuando un nodo tiene más de un padre?
Si esto sucede, el nodo tendría varios caminos y no se sabría con certeza a quién le pertenece, y dejaría de cumplir con una de las características que representa un árbol, corriendo con la consecuencia de convertirse en un grafo.

¿Qué es un grafo? Un grafo es más flexible, pero también más difícil de manejar.

DOCUMENTACION DEL ESCENARIO DE SISTEMAS DE ARCHIVOS

Para este caso me he basado en el sistema de archivos de mi equipo, donde he seleccionado a la nube shirley-personal (la raíz- nivel 0), de allí nacen 4 carpetas (nivel 1 ):
0 Datos adjuntos
1 Desktop
2 Imagenes
3 Documentos
4 La evolucion de la computacion

3 de estas carpetas (Desktop, Imagenes y documentos) tienen subcarpetas (nivel 2) y de la carpeta documentos nacen otras subcarpetas convirtiendosen finalmente en hojas (nivel 3).

Desktop -> Optimizacion de procesos administrativos
Imagenes -> Capturas de pantalla

Documentos -> arduino
Documentos -> ProyectoWebService -> ProyectoWebService-Slnx
Documentos -> NetBeansProject -> Herencia

Mientras tanto la carepta Datos adjuntos se convierte también en una hoja ya que no hay nada adentro allí y la evolución de la computación es solo un archivo.

Este sería el molde como árbol del sistema de archivos que he construido. Donde se puede ver a la hora de imprimir a donde pertenecen las carpetas, las subcarpetas y las subcarpeta que nacen de las subcarpetas, haciendo así que la búsqueda de estos archivos (nodos) sea más fácil.

DOCUMENTACIÓN DEL ESCENARIO DEL MENÚ DE APLICACIONES

Un menú de aplicación es una lista de opciones que casi todos los programas tienen para que nosotros les digamos que hacer.
Para la construcción del menú de aplicaciones he usado el molde del árbol con el fin de facilitar la busqueda de las opciones.

El programa que usé como ejemplo es el editor de código Visual Studio Code (Raíz). Cuando entramos al programa encontramos un en la parte superior una barra que representa un menú, donde hay varias opciones y destas opciones nacen más opciones para nosotros decidir que queremos hacer con lo que vayamos a construir allí. Las opciones que yo usé fueron:

File
Edit
Selection
View
Go

Como mencioné anteriormente, al darle clik a estas opciones se despliegan otras opciones con el fin de realizar una acción para el archivo en el que estamos trabajando.

Las opciones que tuve en cuenta como ejemplos fueron los siguientes:

File ->New File
File ->Save
File ->Share. Aquí aL darle click a share se despliegan otras opciones tales como :

                File ->Share -> copyvscodedevlink
                File ->Share -> ExportProfile Default

Edit -> Cut
Edit -> Copy
Edit -> Paste

Selection -> SelectAll
Selection -> Copy Line Up
Selection -> Move Line Up

View-> Run (quedando como hoja)
View-> Terminal (quedando como hoja)

GO-> Last Edit Location (quedadno como hoja porque este comando nos envía al lugar donde realizamos la ultima edicion dentro del archivo)
