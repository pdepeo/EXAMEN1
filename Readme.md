La EETAC está interesada en construir un juego virtual que permita
promocionar la escuela. Por este motivo, propone a sus alumnos de DSA la
construcción de una prueba piloto con las siguientes operaciones:
● Registro de un usuario con la siguiente información: nombre,
apellidos, fecha de nacimiento, correo electrónico y password.
El proceso de registro genera un nuevo usuario en el sistema,
con un identificador asociado y un saldo inicial de 50
dsaCoins. En caso que el sistema ya tenga un usuario con el
correo electrónico se debe indicar el error.
● Listado de usuarios ordenados alfabéticamente (apellidos y
nombre en caso de existir apellidos repetidos).
● Login de un usuario (email/password).
● Añadir un nuevo objeto (nombre, descripción, coins) en el
catálogo de objetos de la tienda.
● Listado de objetos, global de la tienda, ordenado por precio
(descendentemente).
● Compra de un objeto por parte de un usuario. En caso que el
usuario no exista o no disponga de saldo suficiente, se deberá
informar del error.
● Listado de objetos comprados por un usuario.
SE PIDE:
PARTE I: 5 puntos
1.-Identificar las entidades de información y sus propiedades. Podéis hacer una
propuesta de atributos básica.
2.- Especificación del componente que implementará las operaciones descritas
anteriormente: interfaz Java
3.- Implementación de una Fachada (patrón de diseño) que implemente el
interfaz definido previamente.
3.1 Elección de las estructuras de datos
3.2 La fachada deberá implementarse como un Singleton.
3.3 Todos los métodos deberán tener una TRAZA (a nivel de INFO) de
LOG4J que muestre el valor de los parámetros al inicio de los métodos y
al final. También debe contemplarse trazas de otros niveles (ERROR o
FATAL)
4.- Implementación de un test (JUNIT) sobre el componente desarrollado
PARTE II: 5 puntos
1.- Definir (servicio, operaciones, rutas, métodos HTTP, peticiones, respuestas,
códigos de respuesta) e implementar un servicio REST que permita realizar las
operaciones especificadas en la primera parte del ejercicio. Se recomienda que
todas las operaciones deben retornar “objetos de transferencia” y evitar
ciclos/relaciones. Si estos objetos de transferencia son complejos se complica
la serialización/deserialización
NOTA: El servicio debe utilizar el componente construido en el punto anterior
