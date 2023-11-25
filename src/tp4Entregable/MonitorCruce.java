package tp4Entregable;

/**
 * @author Agustin.Fernandez.Gomez
 */
class MonitorCruce {
	private int barcosOECruzando = 0;
	private int barcosEOCruzando = 0;

	public synchronized void entrarOE() {
		while (barcosEOCruzando > 0 || barcosOECruzando == 2) {
			try {
				wait(); // Espera si hay barcos E-O cruzando o ya hay dos barcos O-E cruzando
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		barcosOECruzando++;
	}

	public synchronized void salirOE() {
		barcosOECruzando--;
		notifyAll(); // Notifica a otros hilos que puedan intentar cruzar
	}

	public synchronized void entrarEO() {
		while (barcosOECruzando > 0 || barcosEOCruzando == 2) {
			try {
				wait(); // Espera si hay barcos O-E cruzando o ya hay dos barcos E-O cruzando
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		barcosEOCruzando++;
	}

	public synchronized void salirEO() {
		barcosEOCruzando--;
		notifyAll(); // Notifica a otros hilos que puedan intentar cruzar
	}
}
