import java.io.*;
import java.net.*; 

//Clase que implementa el mensaje serial, tiene un constructor que recibe el 
//número de secuencia, y el entero Prioriodad y el entero Longitud de Datos. Tambien 
//tiene un constructor para cuando no recibe ningún argumento
public class Mensaje_serial implements Serializable{

	private byte[] datos = new byte[8]; //array de 8 bytes
	private int prioridad; //int=32bit=4bytes
	private byte longitud; //byte=1byte
	private long marca_temp; //long=64bit=8bytes
	
	//Constructor cuando se reciben parámetros
	public Mensaje_serial(int secuencia, int prio, byte tamano){
		//Se llena cada byte del array con el número de secuencia
		for (int i=0; i<8; i++){
			datos[i]=(byte)secuencia;
		}
		prioridad= prio;
		longitud= tamano;
		//La marca temporal se asigna como usando la cantidad de milisegundos desde el inicio
		marca_temp= System.currentTimeMillis();
	}
	
	//Constructor cuando se reciben parámetros
	public Mensaje_serial(){
		marca_temp= System.currentTimeMillis();
	}

	public byte[] Datos(){
		return datos;
	}
	
	public int Prioridad(){
		return prioridad;
	}
	
	public byte Longitud_Dato(){
		return longitud;
	}

	public long Marca_Tiempo(){
		return marca_temp;
	}
}

