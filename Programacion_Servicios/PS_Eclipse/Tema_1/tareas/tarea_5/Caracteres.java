package tarea_5;

import java.util.ArrayList;
import java.util.Scanner;

public class Caracteres {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		ArrayList<Character> listaCaracteres = new ArrayList<Character>();
		char c='a';
		System.out.println("Introduce caracteres('*' para salir)");
		do {
			System.out.print(">");
			c = sc.nextLine().charAt(0);
			
			// Añadimos el caracter a la lista de caracteres
			listaCaracteres.add(c);
			
			
		} while (c!='*');
		
		//Quitamos el ultimo caracter -> *
		listaCaracteres.removeLast();

		
		for (char string : listaCaracteres) {
			System.out.print(string);
		}
		sc.close();
		
	}

}
