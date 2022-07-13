package controller;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import model.League;
import model.Team;
import repository.RepositoryLeagues;
import repository.RepositoryTeams;

public class MainControl {

	static RepositoryTeams repository = new RepositoryTeams();

	static RepositoryLeagues repositoryl = new RepositoryLeagues();

	public static void importMatches() throws IOException {

		final String path = "/home/bzaum/teste.txt";

		System.out.println("Importando o caminho do arquivo...");

		importLeague(path);
		importTeams(path);
		// importGames(path);
	}

	private static void importLeague(String path) throws IOException {

		FileReader file = new FileReader(path);
		BufferedReader readingFile = new BufferedReader(file);

		String line = readingFile.readLine();

		String[] vect = line.split(";");
		String country = vect[0];
		String leagueName = vect[1];

		League league = new League(country, leagueName);

		repositoryl.addLeague(league);

		readingFile.close();
	}

	private static void importTeams(String path) throws IOException {

		FileReader file = new FileReader(path);
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
	}

	private static void createTeam(String name) {

		Team team = new Team();

		boolean exists = true;

		exists = repository.hastTeambyName(name);

		if (!exists) {

			team.setName(name);
			repository.addTeam(team);
		}

		else {

			System.err.println("Clube já adicionado!!");
		}
	}

	private static void importGames(String path) throws IOException {

		FileReader file = new FileReader(path);
		BufferedReader readingFile = new BufferedReader(file);

		String line = readingFile.readLine();

		line = readingFile.readLine();

		while (line != null) {

			String[] vect = line.split(";");

			String home = vect[0];
			String gfHome = vect[1];
			String gfAway = vect[2];
			String away = vect[3];

			createMatch(home, away, gfHome, gfAway);

			line = readingFile.readLine();
		}

		readingFile.close();
	}

	private static void createMatch(String home, String away, String gfHome, String gfAway) {

		Team team1 = repository.searchTeam(home);
		Team team2 = repository.searchTeam(away);

		team1.setMatches();
	}

	

	public static void exportMatches() {

	}

}
