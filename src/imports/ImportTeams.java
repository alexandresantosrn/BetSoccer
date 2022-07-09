package imports;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import model.Team;
import repository.RepositoryTeams;

public class ImportTeams {

	public static void importTeams(String name) {

		try {
			FileReader file = new FileReader(name);
			BufferedReader readingFile = new BufferedReader(file);

			String line = readingFile.readLine();

			line = readingFile.readLine();

			while (line != null) {

				String[] vect = line.split(";");

				String home = vect[0];
				String away = vect[3];

				createTeam(home);
				createTeam(away);

				line = readingFile.readLine();
			}

			readingFile.close();

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private static void createTeam(String name) {

		boolean exists = false;

		RepositoryTeams repository = new RepositoryTeams();

		exists = repository.hastTeambyName(name);

		if (!exists) {

			Team team = new Team();
			team.setName(name);

			repository.addTeam(team);
		}

		else {

			System.err.println("Clube já adicionado!!");
		}
	}

}
