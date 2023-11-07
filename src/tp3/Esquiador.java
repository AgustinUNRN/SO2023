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
				System.out.println("El esquiador " + id + " se sube a la aerosilla en la base");
				Thread.sleep(1000); // Simulación de tiempo en el ascenso de la silla
				// Libera la silla en la cima
				System.out.println(
						"El esquiador " + id + " se baja de la aerosilla en la cima. Empieza a descender por la montaña");
				sillasSemaphore.release(); // signal
				// Esquia de regreso a la base
				Thread.sleep(1000); // Simulación de tiempo en el descenso del esquiador
				System.out.println("El esquiador " + id + " descendió hasta la base. Está listo para volver a subir");
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}