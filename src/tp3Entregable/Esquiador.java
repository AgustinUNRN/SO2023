package tp3Entregable;

import java.util.concurrent.Semaphore;

class Esquiador implements Runnable {
	private int id;
	private Semaphore sillaFree;

	public Esquiador(int id, Semaphore sillaFree) {
		this.id = id;
		this.sillaFree = sillaFree;
	}

	@Override
	public void run() {
		System.out.println("Está haciendo la fila el esquiador: " + id);
		try {
			sillaFree.acquire(); // bloquea un lugar en la silla
			System.out.println("El esquiador " + id + " se sienta en la silla");
			Thread.sleep(3000); // simula el tiempo en sentarse
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}