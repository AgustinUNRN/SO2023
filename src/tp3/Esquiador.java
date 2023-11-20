package tp3;

import java.util.concurrent.Semaphore;

class Esquiador implements Runnable {
	private int id;
	private Semaphore sillaFree;
	private Semaphore skierSit;

	public Esquiador(int id, Semaphore sillaFree, Semaphore skierSit) {
		this.id = id;
		this.sillaFree = sillaFree;
		this.skierSit = skierSit;
	}

	@Override
	public void run() {
		System.out.println("Está haciendo la fila el esquiador: " + id);
		try {
			sillaFree.acquire(); // bloquea un lugar en la silla
			System.out.println("El esquiador " + id + " se sienta en la silla");
			Thread.sleep(3000); // simula el tiempo en sentarse
			skierSit.release(); // se sienta
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}