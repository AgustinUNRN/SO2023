package tp1;

import java.net.InetAddress;
import java.net.UnknownHostException;

//Escriba un programa que imprima por la salida estándar el nombre del usuario y 
//el nombre del host, en formato usuario@host.
//Para esto puede utilizar las variables de entorno USERNAME y HOSTNAME.
//Y alguna o ambas no se encuentran definidas, el programa deberá imprimir un 
//mensaje por la salida estándar indicando que variables no se encuentran definidas.
public class Eje1a {

	public static void main(String[] args) {
		// antes hay que hacer un export HOSTNAME por linea de comando
		try {
			String hostn = InetAddress.getLocalHost().getHostName();
			@SuppressWarnings("unused")
			String hostname = System.getenv("HOSTNAME"), username = System.getenv("USERNAME");
			// por alguna razón no anda System.getenv("HOSTNAME"),
			// tengo que hacer export HOSTNAME antes para que funcione
			if (username != null && hostn != null) {
				System.out.println(username + "@" + hostn);
			} else {
				if (username == null && hostn == null)
					System.out.println("Las variables de entorno USERNAME y HOSTNAME no están definidas.");
				else if (username == null) {
					System.out.println("La variable de entorno USERNAME no está definida.");
					System.out.println("HOSTNAME: " + hostn);
				} else {
					System.out.println("La variable de entorno HOSTNAME no está definida.");
					System.out.println("USERNAME: " + username);
				}
			}
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
