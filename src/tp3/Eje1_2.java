package tp3;

import java.util.concurrent.Semaphore;

public class Eje1_2 {
	private static Semaphore ollaVacia = new Semaphore(1); // Semáforo para controlar si la olla está vacía
	private static Semaphore cocinero = new Semaphore(0); // Semáforo para el cocinero
	private static Semaphore mutex = new Semaphore(1); // Semáforo mutex para la región crítica

	public static void main(String[] args) {
		Thread cocineroThread = new Thread(() -> {
			while (true) {
				try {
					ollaVacia.acquire(); // Espera a que la olla esté vacía
					System.out.println("El cocinero está cocinando y rellenando la olla.");
					Thread.sleep(1000); // Simula la cocción
					ollaVacia.release(); // La olla está llena, permite a los salvajes comer
					cocinero.acquire(); // Espera a que un salvaje lo despierte
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});

		Thread[] salvajes = new Thread[10]; // 10 salvajes en la cena

		for (int i = 0; i < salvajes.length; i++) {
			salvajes[i] = new Thread(() -> {
				while (true) {
					try {
						mutex.acquire(); // Entra en la región crítica
						if (ollaVacia.availablePermits() == 0) {
							System.out.println("Un salvaje despierta al cocinero.");
							cocinero.release(); // Despierta al cocinero si la olla está vacía
						}
						mutex.release(); // Sale de la región crítica

						ollaVacia.acquire(); // Espera a que la olla tenga porciones
						System.out.println("Un salvaje está comiendo.");
						Thread.sleep(1000); // Simula comer
						ollaVacia.release(); // Libera la olla
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			});
		}

		cocineroThread.start();
		for (Thread salvaje : salvajes) {
			salvaje.start();
		}
	}
}
