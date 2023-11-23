package tp3Entregable;

import java.util.concurrent.Semaphore;

public class CentroDeSki {
	private static final int SKIER = 8; // cantidad de esquiadores
	private static final int AEROSILLAS = 3; // cantidad de aerosillas

	public static void main(String[] args) {
		Semaphore sillaFree = new Semaphore(0);// si tiene lugar (4)
		Semaphore sillaReady = new Semaphore(1);// si esta lista para ser usada

		for (int i = 1; i <= SKIER; i++) {
			new Thread(new Esquiador(i, sillaFree)).start();
		}

		for (int i = 1; i <= AEROSILLAS; i++) {
			new Thread(new Aerosilla(i, sillaFree, sillaReady)).start();
		}
	}
}
