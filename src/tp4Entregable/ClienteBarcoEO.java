package tp4Entregable;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 * @author Agustin.Fernandez.Gomez
 */
public class ClienteBarcoEO {
	private final String HOST = "127.0.0.1";
	private final int PUERTO = 1200;

	public static void main(String[] args) {

		new Thread(() -> new ClienteBarcoEO().run()).start();
		new Thread(() -> new ClienteBarcoEO().run()).start();
	}

	public void run() {
		try (Socket socket = new Socket(HOST, PUERTO);
				OutputStream outputStream = socket.getOutputStream();
				InputStream inputStream = socket.getInputStream()) {

			// 1 representa la dirección E-O
			outputStream.write(1);
			outputStream.flush();

			System.out.println("Barco E-O intentando cruzar desde el Océano Pacifico");

			// Esperar la señal del servidor
			byte[] buffer = new byte[1024];
			int bytesRead = inputStream.read(buffer);
			if (bytesRead > 0) {
				String mensaje = new String(buffer, 0, bytesRead);
				System.out.println("Barco E-O: " + mensaje);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
