package controller;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class MainControl {
	
	public static void importMatches() {
		
		Scanner in = new Scanner(System.in);
		
		System.out.print("Informe o caminho do arquivo: ");
		String name = in.nextLine();
		
		try {
			FileReader file = new FileReader(name);
			
		} catch (FileNotFoundException e) {
			System.err.println("Erro na leitura do arquivo!");			
			//e.printStackTrace();
		}		
		
	}
	
	public static void exportMatches() {
		
		
	}

}
