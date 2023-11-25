package tp4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.concurrent.BlockingQueue;

public class ProcesoEje2 implements Runnable {
	private String nombre;
	private BlockingQueue<Character> buffer;
	private Socket socket;
	private int caracteresProducidos = 0;
	private int caracteresRecibidos = 0;
	private final int TAMANO_BUFFER;

	public ProcesoEje2(String nombre, BlockingQueue<Character> buffer, Socket socket, final int TAMANO_BUFFER) {
		this.nombre = nombre;
		this.buffer = buffer;
		this.socket = socket;
		this.TAMANO_BUFFER = TAMANO_BUFFER;
	}

	@Override
	public void run() {
		try (BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				PrintWriter output = new PrintWriter(socket.getOutputStream(), true)) {
			while (true) {
				// Lee un caracter del proceso
				char caracter = (char) input.read();

				// Almacena el caracter en el buffer correspondiente
				buffer.put(caracter);

				// Incrementa el contador de caracteres producidos
				caracteresProducidos++;

				// Imprime el contador de caracteres producidos periódicamente
				if (caracteresProducidos % 10 == 0) {
					System.out.println(nombre + " - Caracteres producidos: " + caracteresProducidos);
				}

				// Verifica si el buffer alcanzó el tamaño máximo
				if (buffer.size() == TAMANO_BUFFER) {
					// Componer un string con los caracteres del buffer
					StringBuilder mensaje = new StringBuilder();
					while (!buffer.isEmpty()) {
						mensaje.append(buffer.take());
					}

					// Imprime el mensaje
					System.out.println(nombre + " - Mensaje recibido: " + mensaje);

					// Incrementa el contador de caracteres recibidos
					caracteresRecibidos++;

					// Imprime el contador de caracteres recibidos periódicamente
					if (caracteresRecibidos % 10 == 0) {
						System.out.println(nombre + " - Caracteres recibidos: " + caracteresRecibidos);
					}
				}
			}
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}
	}
}