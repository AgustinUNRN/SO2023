package tp1;
//Escriba un programa que ejecute el programa “echo $VARIABLE”.

//Además deberá pasar a la ejecución del mismo, la variable VARIABLE con un valor aleatorio.

import java.io.IOException;
import java.util.Random;

public class Eje6a {
	public static void main(String[] args) throws IOException {
		Random random = new Random();
		String variable = "VARIABLE=" + random.nextInt(100);
		ProcessBuilder pb = new ProcessBuilder("/bin/sh", "-c", "echo $" + variable);
		pb.environment().put("VARIABLE", variable);
		pb.start();
	}
}
