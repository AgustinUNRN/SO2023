package tp3;

import java.util.concurrent.Semaphore;

class Paciente extends Thread {
	private int id;
	private Semaphore recepcionista;
	private Semaphore[] colaPaciente;

	public Paciente(int id, Semaphore recepcionista, Semaphore[] colaPaciente) {
		this.id = id;
		this.recepcionista = recepcionista;
		this.colaPaciente = colaPaciente;
	}

	public void run() {
		while (true) {
			try {
				recepcionista.acquire();
				System.out.println("Paciente " + id + " en recepción");
				int medicoId = id % 3; // Asigna médico en forma alternada
				colaPaciente[medicoId].release();
				Thread.sleep(1000); // Tiempo entre pacientes
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}