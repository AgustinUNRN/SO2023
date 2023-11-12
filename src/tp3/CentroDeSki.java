package tp3;

public class CentroDeSki {
	public static void main(String[] args) {
		int numEsquiadores = 10;
		AerosillaMonitor aerosilla = new AerosillaMonitor();

		for (int i = 1; i <= numEsquiadores; i++) {
			Esquiador esquiador = new Esquiador(i, aerosilla);
			esquiador.start();
		}
	}
}