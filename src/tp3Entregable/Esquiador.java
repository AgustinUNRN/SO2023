package tp3Entregable;

class Esquiador extends Thread {
    private int esquiadorId;
    private AerosillaMonitor aerosilla;

    public Esquiador(int esquiadorId, AerosillaMonitor aerosilla) {
        this.esquiadorId = esquiadorId;
        this.aerosilla = aerosilla;
    }

    public void run() {
        while (true) {
            try {
                aerosilla.subirAerosilla(esquiadorId);
                // Simular esquí
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}