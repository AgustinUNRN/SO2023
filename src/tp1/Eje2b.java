package tp1;
//Escriba un programa que verifique el directorio de trabajo actual (current working directory).

//Si el directorio de trabajo actual está dentro del directorio HOME del usuario (USERNAME),
//deberá retornar un valor de retorno que indique la correcta finalización (0).
//Si el directorio de trabajo actual no está dentro del directorio HOME del usuario (USERNAME), 
//deberá retornar un valor de retorno que indique la finalización con error (1).
//Ejecute el programa por la línea de comandos y compruebe el valor de retorno para cada variante.

public class Eje2b {
	public static void main(String[] args) {
		String home = System.getProperty("user.home");
		String pwd = System.getProperty("user.dir");

		if (pwd.startsWith(home)) {
			System.out.println("El directorio de trabajo actual está dentro del directorio HOME del usuario.");
			System.out.println("HOME: " + home);
			System.out.println("PDW: " + pwd);
			System.exit(0);
		} else {
			System.out.println("El directorio de trabajo actual NO está dentro del directorio HOME del usuario.");
			System.out.println("HOME: " + home);
			System.out.println("PDW: " + pwd);
			System.exit(1);
		}
	}

}
