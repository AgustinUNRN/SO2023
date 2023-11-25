package socketExample;
import java.io.InputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Inet4Address;

import java.net.Socket;
import java.net.ServerSocket;

/**
 *
 * @author german.tejero
 */
public class SimpleSockets {

    public static void main(String[] args) {
        
        try(var server = new ServerSocket(1200)){

            System.out.println("Ready to accept connectios");
            while(true){

                try (Socket socket = server.accept()) {
                    String line = "";
                    
                    InputStream input = socket.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(input));
                    line = reader.readLine();
                    
                    line = line.toUpperCase();
                    
                    OutputStream output = socket.getOutputStream();
                    PrintWriter writer = new PrintWriter(output, true);
                    writer.println(line);
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
