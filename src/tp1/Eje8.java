package tp1;
//Escriba un programa que realice las siguientes tareas:

//Debe leer una lista de comandos por la entrada estándar (Un comando por línea).	->		Eje4b
//
//Debe ejecutar cada uno de los comandos, en un hilo independiente.
//
//Cada hilo debe imprimir por la salida estándar el comando y el valor de retorno, en formato “comando” separador “retorno”.
//-El separador, le será indicado por parámetro -s.		->		Eje3a
//
//-Si este parámetro no se le indica, el valor por defecto es el carácter tabulador.
//
//Además, si el valor de retorno es distinto de cero (0), 
//debe imprimir por la salida de errores el mensaje “El comando {comando}, 
//fallo con valor de retorno {retorno}”.
//
//A cada comando a ejecutar, 
//se le deben pasar las mismas variables de entorno que tiene el proceso actual, salvo las variables HOSTNAME.
//
//La variable HOSTNAME debe ser pasada con el valor “prueba”.		->		Eje1a
//
//El programa debe terminar, cuando no queden líneas para leer en la entrada estándar y todos los hilos que inició, 
//hayan terminado.
//
//Además deberá registrar manejadores de señales para INT y TERM. Si se recibe cualquier de las señales, 
//deberá dejar de leer la entrada estándar y terminar, una vez hayan terminado todos los hilos iniciados.	->		Eje5a
//
//Cuando el programa termine, deberá imprimir por la salida estándar la cantidad de líneas leídas o comandos ejecutados.
//
//El valor de retorno, 
//debe ser cero (0) si termina sin ser detenido o uno (1) si termina a través de una de las señales registradas.
//
//Realice pruebas sobre el programa, utilizando archivos y los operadores de redirección de la línea de comandos.
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import sun.misc.Signal;
import sun.misc.SignalHandler;

public class Eje8 {
	public static void main(String[] args) throws IOException, InterruptedException {
		System.out.println("El ID de proceso del programa es: " + (int) ProcessHandle.current().pid());
		// -Si este parámetro no se le indica, el valor por defecto es el carácter
		// tabulador.
		String separator = "\t";
		// -El separador, le será indicado por parámetro -s.
		// ->Eje3a
		if (args.length > 0 && args[0].equals("-s")) {
			separator = args[1];
		}
		List<Process> processes = new ArrayList<>();//
		// Debe leer una lista de comandos por la entrada estándar (Un comando por
		// línea).->Eje4b
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		String line;
		List<String> commands = new ArrayList<>();
		while ((line = reader.readLine()) != null) {
			// Además deberá registrar manejadores de señales para INT y TERM. Si se recibe
			// cualquier de las señales,
			// deberá dejar de leer la entrada estándar y terminar, una vez hayan terminado
			// todos los hilos iniciados.->Eje5a
			Signal.handle(new Signal("INT"), new SignalHandler() {
				public void handle(Signal signal) {
					System.err.println("Señal INT recibida, se ejecutaron " + commands.size() + " comandos: "
							+ commands.toString());
					// El valor de retorno,
					// debe ser cero (0) si termina sin ser detenido o uno (1) si termina a través
					// de una de las señales registradas.
					Runtime.getRuntime().halt(1);
				}
			});
			// Manejador de señales TERM
			Signal.handle(new Signal("TERM"), new SignalHandler() {
				public void handle(Signal signal) {
					System.err.println("Señal TERM recibida, se ejecutaron " + commands.size() + " comandos: "
							+ commands.toString());
					Runtime.getRuntime().halt(1);
				}
			});
			commands.add(line);
			String separator2 = separator;
			String[] command = commands.get(commands.size() - 1).split(" ");
			// A cada comando a ejecutar,
			// se le deben pasar las mismas variables de entorno que tiene el proceso
			// actual, salvo las variables HOSTNAME.
			// La variable HOSTNAME debe ser pasada con el valor “prueba”. -> Eje1a
			ProcessBuilder pb = new ProcessBuilder(command);
			pb.environment().put("HOSTNAME", "prueba");
			pb.redirectErrorStream(true);
			Process process = pb.start();
			processes.add(process);
			// Debe ejecutar cada uno de los comandos, en un hilo independiente.
			new Thread(() -> {
				try {
					int exitCode = process.waitFor();
					// Cada hilo debe imprimir por la salida estándar el comando y el valor de
					// retorno, en formato “comando” separador “retorno”.
					System.out.println("Comando: " + commands.get(commands.size() - 1) + separator2 + "Exit code: "
							+ exitCode + "\n" + "Cantidad de comandos ejecutados: " + processes.size()
							+ "\n-----------------------------------------------------------");
					if (exitCode != 0) {
						// Además, si el valor de retorno es distinto de cero (0),
						// debe imprimir por la salida de errores el mensaje “El comando {comando},fallo
						// con valor de retorno {retorno}”.
						System.err.println(
								"El comando " + commands.get(commands.size() - 1) + " fallo con valor de retorno "
										+ exitCode + "\n-----------------------------------------------------------");
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}).start();
			Thread.sleep(1000);
			for (Process process1 : processes) {
				process1.waitFor();
			}
		}
		// Cuando el programa termine, deberá imprimir por la salida estándar la
		// cantidad de líneas leídas o comandos ejecutados.
		System.out.println("Se ejecutaron " + commands.size() + " comandos: " + commands.toString());
	}
}
