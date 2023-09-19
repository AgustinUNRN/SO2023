package tp1;
//Escriba un programa que imprima por la salida estándar, la fecha y hora actual.

//Por defecto, deberá imprimir la misma en formato ISO8601. (https://es.wikipedia.org/wiki/ISO_8601).
//Para modificar el formato de impresión, se podrá hacer por dos medios. 
//A través de la variable de entorno DATE_FORMAT, o a través del argumento -f.
//El comportamiento debe ser el siguiente:
//Si se especifica el parámetro -f, se debe usar el formato que este indica.
//Si no se especifica -f, pero está definida la variable DATE_FORMAT, 
//se debe usar el formato indicado por la variable.
//Si ninguno de ambos se encuentra definido, se debe imprimir el formato ISO8601.

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Eje3a {
	public static void main(String[] args) {
		String dateSystem = System.getenv("DATE_FORMAT");
		DateTimeFormatter formatUsar;
		if (args.length > 0 && args[0].equals("-f")) {
			if (args.length > 1) // si tengo el formato por parametro, piso el del sistema
				dateSystem = args[1];
			else {
				System.err.println("El formato debe ser especificado después del argumento -f.");
				System.exit(1);
			}
		}
		if (dateSystem != null)
			formatUsar = DateTimeFormatter.ofPattern(dateSystem);
		else // sino uso el formato iso
			formatUsar = DateTimeFormatter.ISO_DATE_TIME;
		LocalDateTime now = LocalDateTime.now();
		String date = now.format(formatUsar);
		System.out.println(date);
	}
}
