package tp4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 
 * @author agustin
 */
public class HolaMundo_servidor {

	public static void main(String[] args) {
		final int puerto = 1200;

		try (ServerSocket serverSocket = new ServerSocket(puerto)) {
			System.out.println("Servidor escuchando en el puerto: " + puerto + "...");

			while (true) {
				Socket clientSocket = serverSocket.accept();
				System.out.println("Cliente conectado desde: " + clientSocket.getInetAddress());

				// Configuración de entrada y salida
				BufferedReader input = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
				PrintWriter output = new PrintWriter(clientSocket.getOutputStream(), true);

				// Recibe el nombre del cliente
				String clientName = input.readLine();
				System.out.println("Nombre recibido: " + clientName);

				// Concatenar la frase "Hola mundo" al nombre
				String message = "Hola mundo " + clientName + "!";

				// Envia el mensaje modificado al cliente
				output.println(message);

				// Cierra la conexión con el cliente
				input.close();
				output.close();
				clientSocket.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
