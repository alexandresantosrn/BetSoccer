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
			BufferedReader readingFile = new BufferedReader(file);
			
			String line = readingFile.readLine();				
			
			while (line != null) {
				
				String[] vect = line.split(";");
				
				String home = vect[0];
				String away = vect[3];				
				
				System.out.println(home);
				System.out.println(away);
				
				createTeams(home);
				createTeams(away);
				
				line = readingFile.readLine();
			}
			
			readingFile.close();
			
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
	
	public static void createTeams(String team) {
		
		
	}
	
	

}
