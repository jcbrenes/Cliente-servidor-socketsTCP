import java.io.*; 
import java.net.*; 

//Clase de impletementa un cliente TCP
public class ClienteTCP {

	public static void main(String[] args) throws IOException, ClassNotFoundException{
		// TODO Auto-generated method stub
		String ipremoto = "localhost"; 
		int numPuertoRemoto = 1234;  //Número de puerto de atención en el servidor
		try{
			Socket conexion = new Socket(ipremoto, numPuertoRemoto); //Se crea un socket de conexion con el servidor	
			ObjectInputStream stream_entrada = new ObjectInputStream(conexion.getInputStream());
			System.out.println("El puerto de conexion en el Cliente es el " + conexion.getLocalPort());

			int secuencia_mensaje=0; //variable donde se almacena el número de secuencia del mensaje recibido
			//bucle donde se reciben mensajes del servidor hasta encontrar el mensaje 127 (último mensaje)
			while( secuencia_mensaje != 127){
				Mensaje_serial mensaje_recibido= new Mensaje_serial();
				try{
					mensaje_recibido= (Mensaje_serial) stream_entrada.readObject();	//se lee el nuevo mensaje
					//Se imprime en pantalla los datos del mensaje recibido
					System.out.print("Objeto recibido: # " + mensaje_recibido.Datos()[1]+ 
							" - prioridad: "+mensaje_recibido.Prioridad()+
							" - longitud de los datos: "+mensaje_recibido.Longitud_Dato());
					//Se comprueba que el timestamp sea menor a 200ms desde el tiempo actual
					if ((System.currentTimeMillis()- mensaje_recibido.Marca_Tiempo())<200){
						System.out.println(" - marca de tiempo correcta.");
					}else{
						System.out.println(" - marca de tiempo está vencida.");
					}
					//Se alamace el número de secuencia del mensaje
					secuencia_mensaje= mensaje_recibido.Datos()[1];
				}catch(IOException e){
					//Excepción en caso de que la lectura del socket falle
					System.out.println("La conexión ha sido cerrada. No se recibirán más datos" );
					break; //Se sale del bucle while
				}
		}
		
		System.out.println("Cliente terminó de recibir stream serial");
		stream_entrada.close(); //Se cierra el stream de datos en el cliente
		conexion.close();   //Se cierra el socket en el cliente
		}catch(IOException e){
			//Excepción en caso de que falle el establecer el socket de conexión con el servidor
			System.out.println("No se pudo establecer comunicacion con el servidor" );
		}
	}

}
