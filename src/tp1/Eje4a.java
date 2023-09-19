package tp1;
//Escriba un programa que acepte tres (3) parámetros:

//-o1: operando uno (1). Debe ser un número entero, que representará el primer operando de la operación.
//-o2: operando dos (2). Debe ser un número entero, que representará el segundo operando de la operación.
//-op: operador. Debe ser uno de ’+’, ’-’, ’/’ o ’*’, representa el operador de la operación a realizar.
//El programa deberá realizar la operación solicitada sobre los operandos e imprimir el resultado por la salida estándar. 
//Si la operación da un error (por ejemplo por una división por cero), deberá imprimir el error por la salida de errores.
//Todos los parámetros son obligatorios, por lo que debe comprobarse que sean pasados. 
//El orden en que se indiquen no debe ser relevante.

public class Eje4a {
	public static void main(String[] args) {
		int o1 = 0, o2 = 0;
		char op = ' ';
		// verificamos que esten los 3 operadores
		if (args.length != 6) {
			System.err.println("Error: Debes pasar los tres parámetros.");
			System.exit(1);
		}
		// obtenemos los valores de los operadores
		for (int i = 0; i < args.length; i += 2) {// uso el for porque los opearadores pueden estar en cualquier orden
			if (args[i].equals("-o1")) // si es -o1 el operador guardo en la variable o1
				o1 = Integer.parseInt(args[i + 1]);
			else if (args[i].equals("-o2")) // si es -o2 el operador guardo en la variable o2
				o2 = Integer.parseInt(args[i + 1]);
			else if (args[i].equals("-op")) // si es -op el operador guardo en la variable op
				op = args[i + 1].charAt(0);
		}
		// Realizar la operación solicitada
		int resultado = 0;
		String mensajeError = null;
		switch (op) {
		case '+':
			resultado = o1 + o2;
			break;
		case '-':
			resultado = o1 - o2;
			break;
		case '*':
			resultado = o1 * o2;
			break;
		case '/':
			if (o2 != 0) {
				resultado = o1 / o2;
			} else {
				mensajeError = "Error: División por cero.";
			}
			break;
		default:
			mensajeError = "Error: Operador inválido.";
			break;
		}
		// imprimimos el resultado o el mensaje de error
		if (mensajeError != null) {
			System.err.println(mensajeError);
			System.exit(1);
		} else {
			System.out.println(resultado);
			System.exit(0);
		}
	}
}
