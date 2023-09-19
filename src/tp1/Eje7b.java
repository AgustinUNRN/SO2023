package tp1;

//Escriba un programa que inicie dos (2) hilos. 
//Uno debe ejecutar el comando de búsqueda “find” para buscar un determinado archivo en un directorio, 
//y el otro hilo debe realizar la búsqueda del mismo archivo en otro directorio.
//
//El nombre de archivo a buscar y los dos (2) directorios deben ser por argumentos al programa.
//
//Cada hilo debe imprimir “Encontre el {archivo} en {directorio}” o “No encontre el archivo en directorio”, 
//dependiendo del valor de retorno.
//
//El programa debe terminar cuando ambos hilos hayan terminado.

import java.io.IOException;

public class Eje7b {

	public static void main(String[] args) {
		String fileName = args[0], dir1 = args[1], dir2 = args[2];
		String mensaje = "ncontré el archivo " + fileName + " en " + dir1;

		Thread thread1 = new Thread(() -> {
			int exitCode = executeCommand("find " + dir1 + fileName);
			if (exitCode == 0)
				System.out.println("E" + mensaje);
			else
				System.out.println("No e" + mensaje);
		});

		Thread thread2 = new Thread(() -> {
			int exitCode = executeCommand("find " + dir2 + fileName);
			if (exitCode == 0)
				System.out.println("E" + mensaje);
			else
				System.out.println("No e" + mensaje);
		});

		thread1.start();
		thread2.start();

		try {
			thread1.join();
			thread2.join();
		} catch (InterruptedException e) {
			System.err.println("Error: Se interrumpió la ejecución de los hilos.");
		}
	}

	private static int executeCommand(String command) {
		int exitCode = -1;
		try {
			Process process = new ProcessBuilder(command.split(" ")).start(); // el " " indica el delimitador
			exitCode = process.waitFor();
		} catch (IOException | InterruptedException e) {
			System.err.println("Error: No se pudo ejecutar el comando.");
		}

		return exitCode;
	}
}
