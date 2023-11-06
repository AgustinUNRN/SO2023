package tp3;

import java.util.concurrent.Semaphore;

class Esquiador extends Thread {
	private int id;
	private Semaphore sillasSemaphore;

	public Esquiador(int id, Semaphore sillasSemaphore) {
		this.id = id;
		this.sillasSemaphore = sillasSemaphore;
	}

	public void run() {
		try {
			while (true) {
				// Espera una silla disponible
				sillasSemaphore.acquire(); // wait
				System.out.println("Esquiador " + id + " toma una silla.");
				Thread.sleep(1000); // Simulación de tiempo en el ascenso de la silla
				// Libera la silla en la cima
				System.out.println("Esquiador " + id + " libera la silla en la cima.");
				sillasSemaphore.release(); // signal
				// Esquia de regreso a la base
				Thread.sleep(1000); // Simulación de tiempo en el descenso del esquiador
				System.out.println("Esquiador " + id + " descendió por la montaña");
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}