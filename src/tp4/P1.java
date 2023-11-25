package tp4;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class P1 {

    public static void main(String[] args) {
        final String HOST = "127.0.0.1";
        final int PUERTO = 1200;

        try (Socket socket = new Socket(HOST, PUERTO);
             Scanner scanner = new Scanner(System.in);
             PrintWriter output = new PrintWriter(socket.getOutputStream(), true)
        ) {
            while (true) {
                System.out.print("P1 - Ingrese un caracter: ");
                char caracter = scanner.nextLine().charAt(0);

                // Envia el caracter al servidor
                output.write(caracter);
                output.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
