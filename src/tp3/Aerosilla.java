package tp3;

import java.util.concurrent.Semaphore;

class Aerosilla {
    private Semaphore sillasDisponibles;

    public Aerosilla(int numSillas) {
        sillasDisponibles = new Semaphore(numSillas);
    }

    public void abordarAerosilla(int idEsquiador) {
        try {
            sillasDisponibles.acquire(4); // Esperar hasta que haya espacio para 4 esquiadores
            System.out.println("Esquiador " + idEsquiador + " aborda la aerosilla.");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public void liberarAerosilla() {
        sillasDisponibles.release(4); // Liberar la silla cuádruple
    }
}