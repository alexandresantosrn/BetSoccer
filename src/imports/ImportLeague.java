package imports;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import model.League;

public class ImportLeague {

	public static void importLeague(String name) {

		try {
			FileReader file = new FileReader(name);
			BufferedReader readingFile = new BufferedReader(file);

			String line = readingFile.readLine();

			String[] vect = line.split(";");

			String country = vect[0];
			String leagueName = vect[1];
			
			createLeague(country,leagueName);
			
			readingFile.close();

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private static void createLeague(String country, String leagueName) {
		
		new League(country, leagueName);
		System.out.println(country);
	}

}
