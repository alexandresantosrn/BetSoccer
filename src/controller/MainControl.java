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

		repositoryl.addLeague(league); // Add league to repository leagues

		readingFile.close(); // Closing
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

			line = readingFile.readLine(); // Going to the next line
		}

		readingFile.close();
	}

	private static void createMatch(String home, String away, String gfHome, String gfAway) {

		Team team1 = repository.searchTeam(home);
		Team team2 = repository.searchTeam(away);

		// Updating Matches

		// Matches
		Match match = new Match();

		// Match
		match.setHomeTeam(team1);
		match.setAwayTeam(team2);
		match.setHomeScore(Integer.parseInt(gfHome));
		match.setAwayScore(Integer.parseInt(gfAway));

		// Updating Matches
		match.setTotalmatchesHome(team1.getMatches());
		match.setTotalmatchesAway(team2.getMatches());
		match.setMatchesHome(team1.getMatchesHome());
		match.setMatchesAway(team2.getMatchesAway());

		// Basic Team1
		match.setGoalsTotalHome(team1.getTotalgoals());
		match.setGoalsTotalForHome(team1.getTotalgoalsFor());
		match.setGoalsTotalAverageHome(team1.getTotalgoalsAverage());

		// Basic Team2
		match.setGoalsTotalAway(team2.getTotalgoals());
		match.setGoalsTotalForAway(team2.getTotalgoalsFor());
		match.setGoalsTotalAverageAway(team2.getTotalgoalsAverage());

		// Team1 Goals
		match.setGoalsHome(team1.getTotalGoalsHome());
		match.setGoalsForHome(team1.getTotalgoalsForHome());
		match.setGoalsAgainstHome(team1.getTotalgoalsAverageHome());

		// Team2 Goals
		match.setGoalsAway(team2.getTotalGoalsAway());
		match.setGoalsForAway(team2.getTotalgoalsForAway());
		match.setGoalsAgainstAway(team2.getTotalgoalsAverageAway());

		// Updating 1.5 Goals
		match.setGoals15HomeTotal(team1.getOver15total());
		match.setGoals15Home(team1.getOver15home());

		match.setGoals15AwayTotal(team2.getOver15total());
		match.setGoals15Away(team2.getOver15away());

		// Updating 2.5 Goals
		match.setGoals25HomeTotal(team1.getOver25total());
		match.setGoals25Home(team1.getOver25home());

		match.setGoals25AwayTotal(team2.getOver25total());
		match.setGoals25Away(team2.getOver25away());

		// Updating matches
		if (team1.getMatches() == 0) {

			match.setAvgGoalsHome(0);
		}

		else {

			match.setAvgGoalsHome(team1.getTotalgoals() / team1.getMatches());
			match.setAvgGoalsAway(team2.getTotalgoals() / team2.getMatches());
		}

		if (team2.getMatches() == 0) {

			match.setAvgGoalsAway(0);
		}

		else {
			
			match.setAvgGoalsHome(team1.getTotalgoals() / team1.getMatches());
			match.setAvgGoalsAway(team2.getTotalgoals() / team2.getMatches());
		}

		// Updating teams

		// Team 1 Matches
		team1.setMatches(team1.getMatches() + 1);
		team1.setMatchesHome(team1.getMatchesHome() + 1);

		// Team 2 Matches
		team2.setMatches(team2.getMatches() + 1);
		team2.setMatchesAway(team2.getMatchesAway() + 1);

		// Team 1 Goals
		team1.setTotalgoals(team1.getTotalgoals() + Integer.parseInt(gfHome) + Integer.parseInt(gfAway));
		team1.setTotalgoalsFor(team1.getTotalgoalsFor() + Integer.parseInt(gfHome));
		team1.setTotalgoalsAverage(team1.getTotalgoalsAverage() + Integer.parseInt(gfAway));
		team1.setTotalGoalsHome(team1.getTotalGoalsHome() + Integer.parseInt(gfHome) + Integer.parseInt(gfAway));
		team1.setTotalgoalsForHome(team1.getTotalgoalsForHome() + Integer.parseInt(gfHome));
		team1.setTotalgoalsAverageHome(team1.getTotalgoalsAverageHome() + Integer.parseInt(gfAway));

		// Team 1 Overs
		if ((Integer.parseInt(gfHome) + Integer.parseInt(gfAway)) > 1.5) {

			team1.setOver15total(team1.getOver15total() + 1);
			team1.setOver15home(team1.getOver15home() + 1);
		}

		if ((Integer.parseInt(gfHome) + Integer.parseInt(gfAway)) > 2.5) {

			team1.setOver25total(team1.getOver25total() + 1);
			team1.setOver25home(team1.getOver25home() + 1);
		}

		// Team 2 Goals
		team2.setTotalgoals(team2.getTotalgoals() + Integer.parseInt(gfHome) + Integer.parseInt(gfAway));
		team2.setTotalgoalsFor(team2.getTotalgoalsFor() + Integer.parseInt(gfAway));
		team2.setTotalgoalsAverage(team2.getTotalgoalsAverage() + Integer.parseInt(gfHome));
		team2.setTotalGoalsAway(team2.getTotalGoalsAway() + Integer.parseInt(gfHome) + Integer.parseInt(gfAway));
		team2.setTotalgoalsForAway(team2.getTotalgoalsForAway() + Integer.parseInt(gfAway));
		team2.setTotalgoalsAverageAway(team2.getTotalgoalsAverageAway() + Integer.parseInt(gfHome));

		// Team 2 Overs
		if ((Integer.parseInt(gfHome) + Integer.parseInt(gfAway)) > 1.5) {

			team2.setOver15total(team2.getOver15total() + 1);
			team2.setOver15away(team2.getOver15away() + 1);
		}

		if ((Integer.parseInt(gfHome) + Integer.parseInt(gfAway)) > 2.5) {

			team2.setOver25total(team2.getOver25total() + 1);
			team2.setOver25away(team2.getOver25away() + 1);
		}

		repositorym.addMatch(match);
	}

	public static void exportMatches() throws IOException {

		List<Match> matches = new ArrayList<Match>();
		matches = repositorym.getMatches();

		FileWriter file = new FileWriter("file.csv");
		PrintWriter recordingFile = new PrintWriter(file);

		// First line
		recordingFile.printf("Casa");
		recordingFile.printf(";");
		recordingFile.printf("Fora");
		recordingFile.printf(";");
		recordingFile.printf("PlacarCasa");
		recordingFile.printf(";");
		recordingFile.printf("PlacarFora");
		recordingFile.printf(";");
		recordingFile.printf("PartidasCasa");
		recordingFile.printf(";");
		recordingFile.printf("PartidasFora");
		recordingFile.printf(";");
		recordingFile.printf("PartidasEmCasa");
		recordingFile.printf(";");
		recordingFile.printf("PartidasEmFora");
		recordingFile.printf(";");
		recordingFile.printf("TotalCasa");
		recordingFile.printf(";");
		recordingFile.printf("TotalProCasa");
		recordingFile.printf(";");
		recordingFile.printf("TotalContraCasa");
		recordingFile.printf(";");
		recordingFile.printf("TotalEmCasa");
		recordingFile.printf(";");
		recordingFile.printf("TotalProEmCasa");
		recordingFile.printf(";");
		recordingFile.printf("TotalContraEmCasa");
		recordingFile.printf(";");
		recordingFile.printf("TotalFora");
		recordingFile.printf(";");
		recordingFile.printf("TotalProFora");
		recordingFile.printf(";");
		recordingFile.printf("TotalContraFora");
		recordingFile.printf(";");
		recordingFile.printf("TotalEmFora");
		recordingFile.printf(";");
		recordingFile.printf("TotalProEmFora");
		recordingFile.printf(";");
		recordingFile.printf("TotalContraEmFora");
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
		recordingFile.print(";");
		recordingFile.printf("TotalMediaGolsCasa");
		recordingFile.printf(";");
		recordingFile.printf("TotalMediaGolsFora");
		recordingFile.println(";");

		for (Match match : matches) {

			recordingFile.print(match.getHomeTeam().getName());
			recordingFile.print(";");
			recordingFile.print(match.getAwayTeam().getName());
			recordingFile.print(";");
			recordingFile.print(match.getHomeScore());
			recordingFile.print(";");
			recordingFile.print(match.getAwayScore());
			recordingFile.print(";");
			recordingFile.print(match.getTotalmatchesHome());
			recordingFile.print(";");
			recordingFile.print(match.getTotalmatchesAway());
			recordingFile.print(";");
			recordingFile.print(match.getMatchesHome());
			recordingFile.print(";");
			recordingFile.print(match.getMatchesAway());
			recordingFile.print(";");
			recordingFile.print(match.getGoalsTotalHome());
			recordingFile.print(";");
			recordingFile.print(match.getGoalsTotalForHome());
			recordingFile.print(";");
			recordingFile.print(match.getGoalsTotalAverageHome());
			recordingFile.print(";");
			recordingFile.print(match.getGoalsHome());
			recordingFile.print(";");
			recordingFile.print(match.getGoalsForHome());
			recordingFile.print(";");
			recordingFile.print(match.getGoalsAgainstHome());
			recordingFile.print(";");
			recordingFile.print(match.getGoalsTotalAway());
			recordingFile.print(";");
			recordingFile.print(match.getGoalsTotalForAway());
			recordingFile.print(";");
			recordingFile.print(match.getGoalsTotalAverageAway());
			recordingFile.print(";");
			recordingFile.print(match.getGoalsAway());
			recordingFile.print(";");
			recordingFile.print(match.getGoalsForAway());
			recordingFile.print(";");
			recordingFile.print(match.getGoalsAgainstAway());
			recordingFile.print(";");
			recordingFile.print(match.getGoals15HomeTotal());
			recordingFile.print(";");
			recordingFile.print(match.getGoals15Home());
			recordingFile.print(";");
			recordingFile.print(match.getGoals25HomeTotal());
			recordingFile.print(";");
			recordingFile.print(match.getGoals25Home());
			recordingFile.print(";");
			recordingFile.print(match.getGoals15AwayTotal());
			recordingFile.print(";");
			recordingFile.print(match.getGoals15Away());
			recordingFile.print(";");
			recordingFile.print(match.getGoals25AwayTotal());
			recordingFile.print(";");
			recordingFile.print(match.getGoals25Away());
			recordingFile.print(";");
			recordingFile.print(match.getAvgGoalsHome());
			recordingFile.print(";");
			recordingFile.println(match.getAvgGoalsAway());
		}

		file.close();
	}

}
