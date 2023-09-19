package tp1;
//Escriba un programa que lea cadenas de texto por la entrada estándar, de línea en línea.

//De cada línea leída, deberá imprimir por la salida estándar la cantidad de caracteres de la misma.
//Además el programa deberá aceptar un parámetro, indicando la cantidad mínima de caracteres. S
//i una línea tiene menos de la cantidad mínima de caracteres, se deberá imprimir por la salida de errores, 
//en vez de la salida estándar.
//El programa debe terminar al leer el fin de archivo.
//Realice pruebas en la línea de comandos sobre el programa, utilizando los operadores de redirección.

//Modifique el Ejercicio 4b, para que realice lo siguiente:
//
//El programa deberá procesar cada una de las líneas de texto, cada un (1) segundo.
//El programa deberá registrar un manejador de señales, para las señales INT y TERM.
//El programa deberá terminar si recibe cualquiera de ambas señales, 
//aunque no haya terminado de procesar todas las líneas de texto, 
//por lo que deberá comprobar si se recibió alguna de las señales antes de procesar cada línea.
import java.util.Scanner;

import sun.misc.Signal;
import sun.misc.SignalHandler;

public class Eje5a {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int minLength = Integer.parseInt(args[0]);
		System.out.println("Cada linea debe tener al menos " + minLength + " carácteres");
		// El programa deberá registrar un manejador de señales, para las señales INT y
		// TERM.
		System.out.println("El ID de proceso del programa es: " + (int) ProcessHandle.current().pid());
		// Manejador de señales INT
		Signal.handle(new Signal("INT"), new SignalHandler() {
			public void handle(Signal signal) {
				System.out.println("Señal INT recibida");
				Runtime.getRuntime().halt(1);
			}
		});

		// Manejador de señales TERM
		Signal.handle(new Signal("TERM"), new SignalHandler() {
			public void handle(Signal signal) {
				System.out.println("Señal TERM recibida");
				Runtime.getRuntime().halt(1);
			}
		});

		while (scanner.hasNextLine()) {
			String line = scanner.nextLine();
			// comprobamos si se ha recibido una señal de terminación antes de procesar cada
			// línea
			if (Thread.currentThread().isInterrupted())
				break;
			if (line.length() < minLength)
				System.err.println("Error: La línea es demasiado corta.");
			else
				System.out.println(line.length());
			try {
				Thread.sleep(1000); // esperamos un segundo antes de procesar la siguiente línea
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			}
		}
	}
}
