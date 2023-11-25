package tp4Entregable;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.concurrent.Semaphore;

/**
 * @author Agustin.Fernandez.Gomez
 */
public class BarcoHandler implements Runnable {
	private static final Semaphore esclusaO = new Semaphore(1);
	private static final Semaphore esclusaE = new Semaphore(1);
	private static final MonitorCruce monitorCruce = new MonitorCruce();
	private Socket socket;

	public BarcoHandler(Socket socket) {
		this.socket = socket;
	}

	@Override
	public void run() {
		try {
			int direccion = socket.getInputStream().read();
			System.out.println("Nuevo barco " + (direccion == 1 ? "O-E" : "E-O") + " conectado");

			if (direccion == 1) {
				monitorCruce.entrarOE();
				esclusaO.acquire();
				Thread.sleep(2000); // Simula el tiempo de espera
				System.out.println(
						"esclusa O bajada para barco O-E \nBarco O-E cruzó la esclusa O \nBarco O-E se encuentra en el medio del canal");
				// Controla que solo dos barcos de O-E puedan cruzar a la vez
				esclusaO.release();
				System.out.println("esclusa O levantada para barco O-E \nBarco O-E esperando esclusa E");
				esclusaE.acquire();
				Thread.sleep(2000); // Simula el tiempo de espera
				System.out.println("esclusa E bajada para barco O-E \nBarco O-E cruzó al Océano Atlántico");
				esclusaE.release();
				System.out.println("esclusa E levantada para barco O-E");
				monitorCruce.salirOE();
				// Enviar mensaje al cliente
				OutputStream outputStream = socket.getOutputStream();
				outputStream.write("Ha cruzado al Océano Atlántico".getBytes());
				outputStream.flush();
			} else {
				monitorCruce.entrarEO();
				esclusaE.acquire();
				Thread.sleep(2000); // Simula el tiempo de espera
				System.out.println(
						"esclusa E bajada para barco E-O \nBarco E-O cruzó la esclusa E \nBarco E-O se encuentra en el medio del canal");
				// Controla que solo dos barcos de O-E puedan cruzar a la vez
				esclusaE.release();
				System.out.println("esclusa E levantada para barco E-O \nBarco E-O esperando esclusa O");
				esclusaO.acquire();
				Thread.sleep(2000); // Simula el tiempo de espera
				System.out.println("esclusa O bajada para barco E-O \nBarco E-O cruzó al Océano Pacifico");
				esclusaO.release();
				System.out.println("esclusa O levantada para barco E-O");
				monitorCruce.salirEO();
				OutputStream outputStream = socket.getOutputStream();
				outputStream.write("Ha cruzado al Océano Pácifico".getBytes());
				outputStream.flush();
			}

			socket.close();

		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}
	}
}