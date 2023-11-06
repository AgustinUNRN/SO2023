package tp3;

import java.util.concurrent.Semaphore;

//T1 -> T2 -> T4 ->T5
//T1 -> T3 -> T5

public class Eje1_1 {
	public static void main(String[] args) {
		Semaphore semT2 = new Semaphore(0);
		Semaphore semT3 = new Semaphore(0);
		Semaphore semT4 = new Semaphore(0);
		Semaphore semT5 = new Semaphore(0);

		Thread tarea1 = new Thread(() -> {
			// Realiza T1
			System.out.println("T1 terminó");
			semT2.release(); // signal T2
			semT3.release();// signal T3
		});

		// T2 y T3 se pueden ejecutar en paralelo
		Thread tarea2 = new Thread(() -> { // No se ejecuta hasta que termine T1
			try {
				semT2.acquire(); // wait T2
				// Realiza T2
				System.out.println("T2 terminó");
				semT4.release();// signal T4
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		});

		Thread tarea3 = new Thread(() -> { // No se ejecuta hasta que termine T1
			try {
				semT3.acquire(); // wait T3
				// Realiza T3
				System.out.println("T3 terminó");
				semT5.release();// signal T5
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		});

		Thread tarea4 = new Thread(() -> { // No se ejecuta hasta que termine T2
			try {
				semT4.acquire(); // wait T4
				// Realiza T4
				System.out.println("T4 terminó");
				semT5.release();// signal T5
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		});

		Thread tarea5 = new Thread(() -> { // No se ejecuta hasta que termine T3 y T4
			try {
				semT5.acquire(2); // wait T5, Espera a que T4 y T3 terminen
				// Realiza T5
				System.out.println("T5 terminó");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		});

		// Inicia las tareas
		tarea1.start();
		tarea2.start();
		tarea3.start();
		tarea4.start();
		tarea5.start();
	}
}
