package tp4;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class Coordinador {

	private static final int PUERTO = 1200;
	private static final int TAMANO_BUFFER = 100;

	private static BlockingQueue<Character> bufferP1 = new ArrayBlockingQueue<>(TAMANO_BUFFER);
	private static BlockingQueue<Character> bufferP2 = new ArrayBlockingQueue<>(TAMANO_BUFFER);

	public static void main(String[] args) {
		try (ServerSocket serverSocket = new ServerSocket(PUERTO)) {
			System.out.println("Servidor escuchando en el puerto " + PUERTO + "...");

			Thread procesoP1 = new Thread(new ProcesoEje2("P1", bufferP1, serverSocket.accept(), TAMANO_BUFFER));
			Thread procesoP2 = new Thread(new ProcesoEje2("P2", bufferP2, serverSocket.accept(), TAMANO_BUFFER));

			procesoP1.start();
			procesoP2.start();

			procesoP1.join();
			procesoP2.join();

		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}
	}
}
