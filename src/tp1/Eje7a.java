package tp1;
//Escriba un programa que inicie dos (2) hilos. 

//Cada hilo debe imprimir por la salida estándar la frase “Soy el hilo N”, 
//donde N es el número 1 o 2 según corresponda.
//Para imprimir la frase utilice dos (2) sentencias. Una que imprima “Soy el hilo” y la otra imprima el número.

public class Eje7a {
	public static void main(String[] args) {
		String mensaje = "Soy el hilo ";
		Thread thread1 = new Thread(() -> {
			System.out.print(mensaje);
			System.out.println("1");
		});
		Thread thread2 = new Thread(() -> {
			System.out.print(mensaje);
			System.out.println("2");
		});

		thread1.start();
		thread2.start();

	}

}
