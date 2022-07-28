package controller;

import java.io.BufferedReader;
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
		importGames(path);
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
		
		//Team 1 Matches
		team1.setMatches(team1.getMatches()+1);
		team1.setMatchesHome(team1.getMatchesHome()+1);
		
		//Team 1 Goals
		team1.setTotalgoals(team1.getTotalgoals()+Integer.parseInt(gfHome)+Integer.parseInt(gfAway));	
		team1.setTotalgoalsFor(team1.getTotalgoalsFor()+Integer.parseInt(gfHome));
		team1.setTotalgoalsAverage(team1.getTotalgoalsAverage()+Integer.parseInt(gfAway));
		team1.setTotalgoalsForHome(team1.getTotalgoalsForHome()+Integer.parseInt(gfHome));
		team1.setTotalgoalsAverageHome(team1.getTotalgoalsAverageHome()+Integer.parseInt(gfAway));
		
		//Team 1 Goals Avg
		team1.setAvgGoals(team1.getTotalgoals()/team1.getMatches());
		team1.setAvgGoalsFor(team1.getTotalgoalsFor()/team1.getMatches());
		team1.setAvgGoalsAverage(team1.getTotalgoalsAverage()/team1.getMatches());
		team1.setAvgGoalsForHome(team1.getAvgGoalsForHome()/team1.getMatchesHome());
		team1.setAvgGoalsAverageHome(team1.getAvgGoalsAverageHome()/team1.getMatchesHome());
		
		//Team 1 Overs		
		if((Integer.parseInt(gfHome)+Integer.parseInt(gfAway)) > 1.5) {
			
			team1.setAvgOver15(team1.getAvgOver15()+1);
			team1.setAvgOver15Home(team1.getAvgOver15Home()+1);			
		}
		
		if((Integer.parseInt(gfHome)+Integer.parseInt(gfAway)) > 2.5) {
			
			team1.setAvgOver25(team1.getAvgOver25()+1);
			team1.setAvgOver25Home(team1.getAvgOver25Home()+1);			
		}
		
		//Team 2
		team2.setMatches(team1.getMatches()+1);
		team2.setMatchesAway(team2.getMatchesAway()+1);
		
		//Team 2 Goals
		team2.setTotalgoals(team2.getTotalgoals()+Integer.parseInt(gfHome)+Integer.parseInt(gfAway));	
		team2.setTotalgoalsFor(team2.getTotalgoalsFor()+Integer.parseInt(gfAway));
		team2.setTotalgoalsAverage(team2.getTotalgoalsAverage()+Integer.parseInt(gfHome));
		team2.setTotalgoalsForAway(team2.getTotalgoalsForAway()+Integer.parseInt(gfAway));
		team2.setTotalgoalsAverageAway(team2.getTotalgoalsAverageAway()+Integer.parseInt(gfHome));
		
		//Team 2 Goals Avg
		team2.setAvgGoals(team2.getTotalgoals()/team2.getMatches());
		team2.setAvgGoalsFor(team2.getTotalgoalsFor()/team2.getMatches());
		team2.setAvgGoalsAverage(team2.getTotalgoalsAverage()/team2.getMatches());
		team2.setAvgGoalsForAway(team2.getAvgGoalsForAway()/team2.getMatchesAway());
		team2.setAvgGoalsAverageAway(team2.getAvgGoalsAverageAway()/team2.getMatchesAway());
		
		//Team 2 Overs		
		if((Integer.parseInt(gfHome)+Integer.parseInt(gfAway)) > 1.5) {
			
			team2.setAvgOver15(team2.getAvgOver15()+1);
			team2.setAvgOver15Home(team2.getAvgOver15Home()+1);			
		}
		
		if((Integer.parseInt(gfHome)+Integer.parseInt(gfAway)) > 2.5) {
			
			team2.setAvgOver25(team2.getAvgOver25()+1);
			team2.setAvgOver25Home(team2.getAvgOver25Home()+1);			
		}

	}

	public static void exportMatches() {

	}

}
