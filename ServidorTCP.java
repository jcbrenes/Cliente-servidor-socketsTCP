import java.io.*; 
import java.net.*; 

//Clase que implementa un hilo en el servidor para enviar mensajes serializados al cliente. Se envían 128 
//mensajes al cliente, uno cada 100ms. Tiene un constructor que recibe como parámetro un socket.
class Tarea_Conexion extends Thread{
	Socket conexion; 
	int periodo;
	
	public Tarea_Conexion(Socket conex){
		conexion= conex;
		periodo= 100; //Periodo de tiempo para enviar cada mensaje
	}
	
	public void run(){
		try{
			ObjectOutputStream stream_salida = new ObjectOutputStream(conexion.getOutputStream());
			//bucle de 128 iteraciones para enviar igual numero de mensajes al cliente
			for(int i = 0; i < 128; i++){
				byte longitud_datos= (byte)(8*Math.random()); //el parámetro longitud se llena con un valor aleatorio
				int prioridad = (int) (10*Math.random());   //el parámetro prioridad se llena con un valor aleatorio
				Mensaje_serial miMensaje= new Mensaje_serial(i, prioridad, longitud_datos); //se crea un nuevo mensaje (objeto de la clase Mensaje_serial
				stream_salida.writeObject(miMensaje); //se escribe el mensaje en el puerto
				stream_salida.flush();
				System.out.println("Se envió objeto # "+ miMensaje.Datos()[1]+" - con estampa: "+miMensaje.Marca_Tiempo()+ " en hilo con ID "+ Thread.currentThread().getId());
				try{
					sleep(periodo);
				} catch (InterruptedException e){}
			} 
			System.out.println("Se terminó de enviar el stream desde el servidor al cliente en hilo con ID "+ Thread.currentThread().getId());
			stream_salida.close(); //Se cierra el stream de datos
			conexion.close(); //se cierra el socket de conexion
		} catch (IOException e) {
			//Excepción en caso de fallar la escritura en el socket
			System.out.println("No se establecer conexion con el cliente en el hilo con ID " + Thread.currentThread().getId());
			System.out.println("La conexión ha sido cerrada.");
		}

	}	
}

//Clase de impletementa un servidor TCP
public class ServidorTCP {

	public static void main(String[] args) throws IOException{
		
		int numPuertoServidor= 1234; //Número de puerto para atender solicitudes
		ServerSocket socketservidor = new ServerSocket(numPuertoServidor); 
		System.out.println("El puerto de atención en el servidor es el: " + socketservidor.getLocalPort());
		socketservidor.setSoTimeout(10000); //Establece un valor de tiempo de 10s para esperar solicitudes
		try{
			//bucle infinito en que el servidor espera solicitudes. Cuando se recibe una solicitud de conexion
			//se crea un nuevo hilo (objeto de la clase Tarea_Conexión) y se le envía el socket de conexión
			while (true){
				Socket conexion = socketservidor.accept(); //se acepta la conexión y se crea un socket
				Tarea_Conexion WT = new Tarea_Conexion(conexion); //Se crea un nuevo hilo
				WT.start(); //Se inicia el nuevo hilo
			}
		}catch(SocketTimeoutException ste){
			//En caso de vencerse el tiempo de espera para aceptar conexiones, se cae levanta la excepción
			System.out.println("Tiempo de espera de clientes ha expirado, conexion cerrada");
			socketservidor.close(); //Se cierra el Server socket al termianl el programa
		}
	}

}
