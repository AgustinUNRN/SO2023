package tp1;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

//Escriba un programa que imprima por la salida estándar, la fecha y hora actual. 
//Por defecto, deberá imprimir la misma en formato ISO8601 (https://es.wikipedia.org/wiki/ISO_8601).
//Ahora bien, si se encuentra definida la variable de entorno DATE_FORMAT, esta deberá contener el 
//formato en que se debe imprimir, y el programa deberá utilizar el formato definido en ella. 
//El contenido de la variable, va a depender del lenguaje de programación en el que trabaje.

public class Eje1b {
	public static void main(String[] args) {
		String dateSystem = System.getenv("DATE_FORMAT");
		DateTimeFormatter formatUsar;

		if (dateSystem != null) // si en sistema tengo algo uso ese formato
			formatUsar = DateTimeFormatter.ofPattern(dateSystem);
		else // sino uso el formato iso
			formatUsar = DateTimeFormatter.ISO_DATE_TIME;
		LocalDateTime now = LocalDateTime.now();
		String date = now.format(formatUsar);
		System.out.println(date);
	}

}
