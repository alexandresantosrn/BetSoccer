package view;

import java.util.Scanner;

public class Home {

	public static void main(String[] args) {

		int option = 100;
		try (Scanner in = new Scanner(System.in)) {

			while (option != 0) {

				System.out.println("Informe a opção desejada: ");
				System.out.println("1 - Importar Partidas");
				System.out.println("2 - Extrair Partidas");
				System.out.println("0 - Sair");
				System.out.print("Opção: ");
				option = in.nextInt();

			}
		}
		
		System.out.println(" \n" + "Até logo!!");

	}
}
