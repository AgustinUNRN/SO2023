package tp1;
//Escriba un programa que lea cadenas de texto por la entrada estándar, de línea en línea.

//De cada línea leída, deberá imprimir por la salida estándar la cantidad de caracteres de la misma.
//Además el programa deberá aceptar un parámetro, indicando la cantidad mínima de caracteres. S
//i una línea tiene menos de la cantidad mínima de caracteres, se deberá imprimir por la salida de errores, 
//en vez de la salida estándar.
//El programa debe terminar al leer el fin de archivo.
//Realice pruebas en la línea de comandos sobre el programa, utilizando los operadores de redirección.

import java.util.Scanner;

public class Eje4b {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int minLength = Integer.parseInt(args[0]);
		System.out.println("Cada linea debe tener al menos " + minLength + " carácteres");

		while (scanner.hasNextLine()) {
			String line = scanner.nextLine();
			if (line.length() < minLength)
				System.err.println("Error: La línea es demasiado corta.");
			else
				System.out.println(line.length());
		}
	}

}
