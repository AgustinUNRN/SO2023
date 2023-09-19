package tp1;

//Escriba un programa que reciba como parámetro un comando a ejecutar, en forma de texto.
//El programa debe ejecutar el comando. Si el valor de retorno del comando ejecutado es cero (0), 
//debe imprimir “Ejecución correcta” por la salida estándar y retorno el mismo valor al entorno.
//Si el valor de retorno es distinto de cero (0), 
//debe imprimir “Ejecución incorrecta” por la salida de errores y retornar el mismo valor al entorno.
import java.io.IOException;

public class Eje6b {
	public static void main(String[] args) {
		String command = args[0];
		try {
			Process process = new ProcessBuilder(command.split(" ")).start();// el " " indica el delimitador
			int exitCode = process.waitFor();
			if (exitCode == 0)
				System.out.println("Ejecución correcta");
			else
				System.err.println("Ejecución incorrecta");
			System.out.println(exitCode);
			System.exit(exitCode);
		} catch (IOException | InterruptedException e) {
			System.err.println("Error: No se pudo ejecutar el comando.");
		}
	}
}
