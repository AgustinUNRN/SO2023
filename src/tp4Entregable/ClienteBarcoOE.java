package tp4Entregable;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 * @author Agustin.Fernandez.Gomez
 */
public class ClienteBarcoOE {
    private final String HOST = "127.0.0.1";
    private final int PUERTO = 1200;

    public static void main(String[] args) {

        new Thread(() -> new ClienteBarcoOE().run()).start();
        new Thread(() -> new ClienteBarcoOE().run()).start();
    }

    public void run() {
        try (Socket socket = new Socket(HOST, PUERTO);
                OutputStream outputStream = socket.getOutputStream();
                InputStream inputStream = socket.getInputStream()) {

            // 0 representa la dirección O-E
            outputStream.write(0);
            outputStream.flush();

            System.out.println("Barco O-E intentando cruzar desde el Océano Atlántico");

            // Esperar la señal del servidor
            byte[] buffer = new byte[1024];
            int bytesRead = inputStream.read(buffer);
            if (bytesRead > 0) {
                String mensaje = new String(buffer, 0, bytesRead);
                System.out.println("Barco O-E: " + mensaje);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
