package controller;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class MainControl {
	
	public static void importMatches() {
		
		Scanner in = new Scanner(System.in);
		
		System.out.print("Informe o caminho do arquivo: ");
		String name = in.nextLine();
		
		try {
			FileReader file = new FileReader(name);
			BufferedReader readFile = new BufferedReader(file);
			
			String line = readFile.readLine();	
			
			System.out.println(line);
			
		} catch (FileNotFoundException e) {
			System.err.println("Erro na leitura do arquivo!");			
			//e.printStackTrace();
			
		} catch (IOException e) {
			System.err.println("Problema na leitura dos dados!");
			//e.printStackTrace();
		}		
		
	}
	
	public static void exportMatches() {
		
		
	}

}
