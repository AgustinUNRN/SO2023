package tp3;

import java.util.concurrent.Semaphore;

class Aerosilla implements Runnable {
	private int id;
	private Semaphore skierSit;
	private Semaphore sillaFree;
	private Semaphore sillaReady;

	public Aerosilla(int id, Semaphore sillaFree, Semaphore skierSit, Semaphore sillaReady) {
		this.id = id;
		this.skierSit = skierSit;
		this.sillaFree = sillaFree;
		this.sillaReady = sillaReady;
	}

	@Override
	public void run() {
		while (true) {
			try {
				System.out.println("Está en la base la silla: " + id);
				sillaReady.acquire(); // bloquea la silla

				System.out.println("Esperando esquiadores la silla: " + id);
				sillaFree.release(4); // libera los 4 espacios para esperar a los esquiadores

				skierSit.acquire(4); // permite que 4 esquiadores se puedan sentar

				System.out.println("La silla " + id + " se llenó. Empieza a subir");
				Thread.sleep(6000); // Simula el tiempo de viaje hacia la cima

				System.out.println("La silla " + id + " llegó a la cima. Los esquiadores descienden a la base");
				Thread.sleep(6000); // Simula el tiempo de viaje hacia la base

			} catch (InterruptedException e) {
				e.printStackTrace();
			} finally {
				sillaReady.release(); // libera la silla para que se pueda usar

			}
		}
	}
}