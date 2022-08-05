package controller;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import model.League;
import model.Match;
import model.Team;
import repository.RepositoryLeagues;
import repository.RepositoryMatches;
import repository.RepositoryTeams;

public class MainControl {

	static RepositoryTeams repository = new RepositoryTeams();

	static RepositoryLeagues repositoryl = new RepositoryLeagues();

	static RepositoryMatches repositorym = new RepositoryMatches();

	public static void importMatches() throws IOException {

		final String path = "/home/bzaum/teste.txt"; // The path

		System.out.println("Importando o caminho do arquivo...");

		importLeague(path); // Importing league
		importTeams(path); // Importing teams
		importGames(path); // Importing games
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

		line = readingFile.readLine(); // Going to the second line

		while (line != null) {

			String[] vect = line.split(";");

			String home = vect[0];
			String away = vect[3];

			createTeam(home);
			createTeam(away);

			line = readingFile.readLine(); // Going to the next line
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
		
		//Updating Matches
		
		// Matches
		Match match = new Match();
		match.setIndex(match.getIndex() + 1);

		// Match
		match.setHomeTeam(team1);
		match.setAwayTeam(team2);
		match.setHomeScore(Integer.parseInt(gfHome));
		match.setAwayScore(Integer.parseInt(gfAway));

		// Basic Team1
		match.setAvgGoalsTotalHome(team1.getAvgGoals());
		match.setAvgGoalsTotalForHome(team1.getAvgGoalsFor());
		match.setAvgGoalsTotalAverageHome(team1.getAvgGoalsAverage());

		// Basic Team2
		match.setAvgGoalsTotalAway(team2.getAvgGoals());
		match.setAvgGoalsTotalForAway(team2.getAvgGoalsFor());
		match.setAvgGoalsTotalAverageAway(team2.getAvgGoalsAverage());

		// Team1 Goals
		match.setAvgGoalsHome(team1.getAvgGoalsHome());
		match.setAvgGoalsForHome(team1.getAvgGoalsForHome());
		match.setAvgGoalsAgainstHome(team1.getAvgGoalsAverageHome());

		// Team2 Goals
		match.setAvgGoalsAway(team1.getAvgGoalsAway());
		match.setAvgGoalsForAway(team1.getAvgGoalsForAway());
		match.setAvgGoalsAgainstAway(team1.getAvgGoalsAverageAway());

		// Updating 1.5 Goals
		match.setAvgGoals15HomeTotal(team1.getAvgOver15());
		match.setAvgGoals15Home(team1.getAvgOver15Home());

		match.setAvgGoals15AwayTotal(team2.getAvgOver15());
		match.setAvgGoals15Away(team2.getAvgOver15Away());

		// Updating 2.5 Goals
		match.setAvgGoals25HomeTotal(team1.getAvgOver25());
		match.setAvgGoals25Home(team1.getAvgOver25Home());

		match.setAvgGoals25AwayTotal(team2.getAvgOver25());
		match.setAvgGoals25Away(team2.getAvgOver25Away());

		// Updating teams

		// Team 1 Matches
		team1.setMatches(team1.getMatches() + 1);
		team1.setMatchesHome(team1.getMatchesHome() + 1);

		// Team 1 Goals
		team1.setTotalgoals(team1.getTotalgoals() + Integer.parseInt(gfHome) + Integer.parseInt(gfAway));
		team1.setTotalgoalsFor(team1.getTotalgoalsFor() + Integer.parseInt(gfHome));
		team1.setTotalgoalsAverage(team1.getTotalgoalsAverage() + Integer.parseInt(gfAway));
		team1.setTotalGoalsHome(team1.getTotalGoalsHome() + Integer.parseInt(gfHome) + Integer.parseInt(gfAway));
		team1.setTotalgoalsForHome(team1.getTotalgoalsForHome() + Integer.parseInt(gfHome));
		team1.setTotalgoalsAverageHome(team1.getTotalgoalsAverageHome() + Integer.parseInt(gfAway));

		// Team 1 Goals Avg
		team1.setAvgGoals(team1.getTotalgoals() / team1.getMatches());
		team1.setAvgGoalsFor(team1.getTotalgoalsFor() / team1.getMatches());
		team1.setAvgGoalsAverage(team1.getTotalgoalsAverage() / team1.getMatches());
		team1.setAvgGoalsHome(team1.getTotalGoalsHome() / team1.getMatchesHome());
		team1.setAvgGoalsForHome(team1.getAvgGoalsForHome() / team1.getMatchesHome());
		team1.setAvgGoalsAverageHome(team1.getAvgGoalsAverageHome() / team1.getMatchesHome());

		// Team 1 Overs
		if ((Integer.parseInt(gfHome) + Integer.parseInt(gfAway)) > 1.5) {

			team1.setAvgOver15(team1.getAvgOver15() + 1);
			team1.setAvgOver15Home(team1.getAvgOver15Home() + 1);
		}

		if ((Integer.parseInt(gfHome) + Integer.parseInt(gfAway)) > 2.5) {

			team1.setAvgOver25(team1.getAvgOver25() + 1);
			team1.setAvgOver25Home(team1.getAvgOver25Home() + 1);
		}

		// Team 2
		team2.setMatches(team2.getMatches() + 1);
		team2.setMatchesAway(team2.getMatchesAway() + 1);

		// Team 2 Goals
		team2.setTotalgoals(team2.getTotalgoals() + Integer.parseInt(gfHome) + Integer.parseInt(gfAway));
		team2.setTotalgoalsFor(team2.getTotalgoalsFor() + Integer.parseInt(gfAway));
		team2.setTotalgoalsAverage(team2.getTotalgoalsAverage() + Integer.parseInt(gfHome));
		team2.setTotalGoalsAway(team2.getTotalGoalsAway() + Integer.parseInt(gfHome) + Integer.parseInt(gfAway));
		team2.setTotalgoalsForAway(team2.getTotalgoalsForAway() + Integer.parseInt(gfAway));
		team2.setTotalgoalsAverageAway(team2.getTotalgoalsAverageAway() + Integer.parseInt(gfHome));

		// Team 2 Goals Avg
		team2.setAvgGoals(team2.getTotalgoals() / team2.getMatches());
		team2.setAvgGoalsFor(team2.getTotalgoalsFor() / team2.getMatches());
		team2.setAvgGoalsAverage(team2.getTotalgoalsAverage() / team2.getMatches());
		team2.setAvgGoalsAway(team2.getTotalGoalsHome() / team2.getMatchesAway());
		team2.setAvgGoalsForAway(team2.getAvgGoalsForAway() / team2.getMatchesAway());
		team2.setAvgGoalsAverageAway(team2.getAvgGoalsAverageAway() / team2.getMatchesAway());

		// Team 2 Overs
		if ((Integer.parseInt(gfHome) + Integer.parseInt(gfAway)) > 1.5) {

			team2.setAvgOver15(team2.getAvgOver15() + 1);
			team2.setAvgOver15Home(team2.getAvgOver15Home() + 1);
		}

		if ((Integer.parseInt(gfHome) + Integer.parseInt(gfAway)) > 2.5) {

			team2.setAvgOver25(team2.getAvgOver25() + 1);
			team2.setAvgOver25Home(team2.getAvgOver25Home() + 1);
		}

		repositorym.addMatch(match);
	}

	public static void exportMatches() throws IOException {

		List<Match> matches = new ArrayList<Match>();
		matches = repositorym.getMatches();

		FileWriter file = new FileWriter("file.csv");
		PrintWriter recordingFile = new PrintWriter(file);

		// First line
		recordingFile.printf("Indice");
		recordingFile.printf(";");
		recordingFile.printf("Casa");
		recordingFile.printf(";");
		recordingFile.printf("Fora");
		recordingFile.printf(";");
		recordingFile.printf("PlacarCasa");
		recordingFile.printf(";");
		recordingFile.printf("PlacarFora");
		recordingFile.printf(";");
		recordingFile.printf("MediaTotalCasa");
		recordingFile.printf(";");
		recordingFile.printf("MediaTotalProCasa");
		recordingFile.printf(";");
		recordingFile.printf("MediaTotalContraCasa");
		recordingFile.printf(";");
		recordingFile.printf("MediaTotalEmCasa");
		recordingFile.printf(";");
		recordingFile.printf("MediaTotalProEmCasa");
		recordingFile.printf(";");
		recordingFile.printf("MediaTotalContraEmCasa");
		recordingFile.printf(";");
		recordingFile.printf("MediaTotalFora");
		recordingFile.printf(";");
		recordingFile.printf("MediaTotalProFora");
		recordingFile.printf(";");
		recordingFile.printf("MediaTotalContraFora");
		recordingFile.printf(";");
		recordingFile.printf("MediaTotalEmFora");
		recordingFile.printf(";");
		recordingFile.printf("MediaTotalProEmFora");
		recordingFile.printf(";");
		recordingFile.printf("MediaTotalContraEmFora");
		recordingFile.printf(";");
		recordingFile.printf("Total15Casa");
		recordingFile.printf(";");
		recordingFile.printf("Total15EmCasa");
		recordingFile.printf(";");
		recordingFile.printf("Total25Casa");
		recordingFile.printf(";");
		recordingFile.printf("Total25EmCasa");
		recordingFile.printf(";");
		recordingFile.printf("Total15Fora");
		recordingFile.printf(";");
		recordingFile.printf("Total15EmFora");
		recordingFile.printf(";");
		recordingFile.printf("Total25Fora");
		recordingFile.printf(";");
		recordingFile.printf("Total25EmFora");
		recordingFile.println(";");

		for (Match match : matches) {

			recordingFile.print(match.getIndex());
			recordingFile.print(";");
			recordingFile.print(match.getHomeTeam().getName());
			recordingFile.print(";");
			recordingFile.print(match.getAwayTeam().getName());
			recordingFile.print(";");
			recordingFile.print(match.getHomeScore());
			recordingFile.print(";");
			recordingFile.print(match.getAwayScore());
			recordingFile.print(";");
			recordingFile.print(match.getAvgGoalsTotalHome());
			recordingFile.print(";");
			recordingFile.print(match.getAvgGoalsTotalForHome());
			recordingFile.print(";");
			recordingFile.println(match.getAvgGoalsTotalAverageHome());		
			recordingFile.print(";");
			recordingFile.print(match.getAvgGoalsHome());
			recordingFile.print(";");
			recordingFile.print(match.getAvgGoalsForHome());
			recordingFile.print(";");
			recordingFile.print(match.getAvgGoalsAgainstHome());
			recordingFile.print(";");
		}

		file.close();
	}

}
