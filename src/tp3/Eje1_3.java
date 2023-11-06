package tp3;

import java.util.concurrent.Semaphore;

public class Eje1_3 {
	public static void main(String[] args) {
		int numMedicos = 3;
		int numPacientes = 10;

		Semaphore[] consultorios = new Semaphore[numMedicos];
		for (int i = 0; i < numMedicos; i++) {
			consultorios[i] = new Semaphore(1);
		}

		Semaphore recepcionista = new Semaphore(1);
		Semaphore[] colaPaciente = new Semaphore[numPacientes];

		for (int i = 0; i < numPacientes; i++) {
			colaPaciente[i] = new Semaphore(0);
		}

		for (int i = 0; i < numMedicos; i++) {
			new Medico(i + 1, consultorios[i], recepcionista, colaPaciente).start();
		}

		for (int i = 0; i < numPacientes; i++) {
			new Paciente(i + 1, recepcionista, colaPaciente).start();
		}
	}
}
