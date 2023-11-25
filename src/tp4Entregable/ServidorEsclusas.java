package tp4Entregable;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author Agustin.Fernandez.Gomez
 */
public class ServidorEsclusas {

	private static final int PUERTO = 1200;

	public static void main(String[] args) {
		try (ServerSocket serverSocket = new ServerSocket(PUERTO)) {
			System.out.println("Servidor de esclusas esperando conexiones en el puerto " + PUERTO + "...");

			while (true) {
				Socket clientSocket = serverSocket.accept();
				new Thread(new BarcoHandler(clientSocket)).start();
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
