package socketExample;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

/**
 *
 * @author german.tejero
 */
public class SimpleSocketsClient {

    public static void main(String[] args) {
        
        try (Socket socket = new Socket("127.0.0.1", 1200)) {
 
            String text = "Hola";
            
            OutputStream output = socket.getOutputStream();
            PrintWriter writer = new PrintWriter(output, true);
            System.out.println(String.format("Send to server %s", text));
            writer.println(text);
            
            InputStream input = socket.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));
            text = reader.readLine();
            System.out.println(String.format("Receive from server %s", text));
        } catch(Exception e) {
             e.printStackTrace();
        }
    }
}