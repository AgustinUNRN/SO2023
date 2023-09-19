package tp1;
//Escriba un programa que reciba un nombre de archivo por parámetro.

//El mismo deberá leer el contenido del archivo (una línea de texto), 
//e imprimirá el contenido por la salida estándar cada un (1) segundo, por siempre. 
//Recuerde abrir el archivo y cerralo, al terminar de leer el contenido.
//Si el mismo recibe la señal INT o TERM, 
//debe terminar mostrando un mensaje por la salida de errores indicando la finalización y retornando al entorno, 
//el valor de la señal recibida.
//Si recibe la señal USR1, debe volver a leer el contenido del archivo y continuar con la operación normal.
//Realice pruebas sobre el programa, modificando el contenido del archivo mientras este esté en operación.
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.concurrent.TimeUnit;
import sun.misc.Signal;
import sun.misc.SignalHandler;
public class Eje5b {
	public static void main(String[] args) {
		try {
			if (args.length != 1) {
				System.err.println("Error al intentar leer el archivo: " + args[0]);
				System.exit(1);
			}
			System.out.println("El ID de proceso del programa es: " + (int) ProcessHandle.current().pid());
			// Manejador de señales INT
			Signal.handle(new Signal("INT"), new SignalHandler() {
				public void handle(Signal signal) {
					System.err.println("Señal INT recibida");
					Runtime.getRuntime().halt(signal.getNumber());
				}
			});
			// Manejador de señales TERM
			Signal.handle(new Signal("TERM"), new SignalHandler() {
				public void handle(Signal signal) {
					System.err.println("Señal TERM recibida");
					Runtime.getRuntime().halt(signal.getNumber());
				}
			});
			RandomAccessFile file = new RandomAccessFile(args[0], "r");
			// Manejador de señales USR1
			Signal.handle(new Signal("USR1"), new SignalHandler() {
				public void handle(Signal signal) {
					try {
						System.out.println("Recibida la señal USR1. Volviendo a leer el archivo.");
						file.seek(0);//volvemos a leer
						leerArchivo(file);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			});
			leerArchivo(file);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static void leerArchivo(RandomAccessFile file) {
		try {
			String line;
			while ((line = file.readLine()) != null) {
				System.out.println(line);
				TimeUnit.SECONDS.sleep(1);
			}
			// Si llegamos al final del archivo, volver al principio
			file.close();
		} catch (IOException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
