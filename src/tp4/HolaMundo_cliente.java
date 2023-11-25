package tp4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * 
 * @author agustin
 */
public class HolaMundo_cliente {

    public static void main(String[] args) {
        final String host = "127.0.0.1";
        final int puerto = 1200;

        try (Socket socket = new Socket(host, puerto)) {
            // Configuración de entrada y salida
            BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter output = new PrintWriter(socket.getOutputStream(), true);

            // Ingrese el nombre
            System.out.print("Ingrese su nombre: ");
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String name = reader.readLine();

            // Envia el nombre al servidor
            output.println(name);

            // Recibe y muestra el mensaje del servidor
            String message = input.readLine();
            System.out.println("Mensaje recibido: " + message);

            // Cierra la conexión con el servidor
            input.close();
            output.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}