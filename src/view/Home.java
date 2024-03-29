package view;

import java.io.IOException;
import java.util.Scanner;

import controller.MainControl;

public class Home {

	public static void main(String[] args) throws IOException {

		int option = 100;

		try (Scanner in = new Scanner(System.in)) {

			while (option != 0) {

				System.out.println("Informe a opção desejada: ");
				System.out.println("1 - Importar Partidas");
				System.out.println("2 - Extrair Partidas");
				System.out.println("0 - Sair");
				System.out.print("Opção: ");
				option = in.nextInt();

				switch (option) {

				case 1:
					System.out.println("Importando partidas...");
					MainControl.importMatches();
					break;

				case 2:
					System.out.println("Extraindo partidas...");
					MainControl.exportMatches();
					break;

				default:
					break;
				}

			}
		}

		System.out.println(" \n" + "Até logo!!");

	}
}
