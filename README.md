# Cliente-servidor-socketsTCP
Programa cliente-servidor en Java utilizando sockets TCP

Trabajo final de la asignatura Computadores y Redes. 
Juan Carlos Brenes
Máster en Automática e Informática Industrial
Universidad Politécnica de Valencia

Implementar un sistema cliente servidor con las siguientes características:
• El servidor aceptará peticiones de conexión mediante un sistema multithread. Cada vez que
se solicita una conexión, esta se acepta y es un working thread (WT) el que proporciona los
datos
• El servidor seguirá atendiendo las peticiones posteriores
• Los datos son un objeto de la clase usado en la P5 con los siguientes campos:
Prioridad (4 bytes), longitud campo de datos(1 byte) y datos (de 0 a 8 bytes),
time_stamp(8 bytes)
• El cada working thread genera para su cliente un objeto cada 100 msec (serializable como
en la practica 5) el cual envía a su cliente con datos aleatorios excepto el campo de datos
(ver P5)
• El cliente una vez está conectado deberá:
o Sacar los datos por consola
o Comprobar el time stamp
o La comunicación entre cliente-servidor (WT) permanece hasta que se recibe en el cliente el
mensaje con el dato 128, o el cliente decide terminar
o Tanto el cliente como el servidor(WT) deberán finalizar correctamente ante la finalización de
su par


