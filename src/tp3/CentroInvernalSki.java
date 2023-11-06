package tp3;

import java.util.concurrent.Semaphore;

public class CentroInvernalSki {
	public static void main(String[] args) {
		int numEsquiadores = 7;
		int capacidadSillas = 4;
		Semaphore sillasSemaphore = new Semaphore(capacidadSillas);

		for (int i = 1; i <= numEsquiadores; i++) {
			new Esquiador(i, sillasSemaphore).start();
		}
	}
}
