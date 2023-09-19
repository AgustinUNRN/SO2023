package tp1;
//Escriba un programa que verifique la existencia de la variable de entorno DATE_FORMAT.

//Si la variable se encuentra definida, deberá imprimir el contenido por la salida estándar, 
//y retornar un valor de retorno que indique la correcta finalización (0).
//Si la variable no se encuentra definida, 
//deberá imprimir un mensaje indicando la situación por la salida estándar, 
//y retornar un valor de retorno que indique la finalización con error (1).
//Ejecute el programa por la línea de comandos y compruebe el valor de retorno para cada variante.

public class Eje2a {
	public static void main(String[] args) {
		String dateFormat = System.getenv("DATE_FORMAT");

		if (dateFormat == null) {
			System.err.println("La variable de entorno DATE_FORMAT no está definida.");
			System.exit(1);
		} else {
			System.out.println(dateFormat);
			System.exit(0);
		}
	}
}
