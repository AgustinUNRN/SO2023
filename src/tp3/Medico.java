package tp3;

import java.util.concurrent.Semaphore;

class Medico extends Thread {
	private int id;
	private Semaphore consultorio;
	private Semaphore recepcionista;
	private Semaphore[] colaPaciente;

	public Medico(int id, Semaphore consultorio, Semaphore recepcionista, Semaphore[] colaPaciente) {
		this.id = id;
		this.consultorio = consultorio;
		this.recepcionista = recepcionista;
		this.colaPaciente = colaPaciente;
	}

	public void run() {
		while (true) {
			try {
				consultorio.acquire();
				System.out.println("Médico " + id + " atendiendo paciente en consultorio " + id);
				Thread.sleep(1000); // Simula la atención médica
				consultorio.release();
				Thread.sleep(1000); // Tiempo entre pacientes
				recepcionista.release();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}