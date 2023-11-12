package tp3Entregable;

import java.util.concurrent.locks.*;

class AerosillaMonitor {
	private final Lock lock = new ReentrantLock();
	private final Condition sillaDisponible = lock.newCondition();
	private int esquiadoresEsperando = 0;

	public void subirAerosilla(int esquiadorId) throws InterruptedException {
		lock.lock();
		try {
			esquiadoresEsperando++;
			System.out.println("El esquiador " + esquiadorId + " se subió a la aerosilla.");
			if (esquiadoresEsperando  == 4) {
				System.out.println("La aerosilla está llena. se dirigen a la cima: "
						+ esquiadoresEsperando + " esquiadores.");
				// Simular tiempo de viaje
				Thread.sleep(1000);
				System.out.println(
						"Los esquiadores se bajan de la aerosilla en la cima y empiezan a descender por la montaña: "
								+ esquiadoresEsperando + " esquiadores.");
				// Liberar la silla
				esquiadoresEsperando = 0;
				sillaDisponible.signalAll();
			} else {
				sillaDisponible.await();
			}
		} finally {
			lock.unlock();
		}
	}

}