package repository;

import java.util.ArrayList;
import java.util.List;

import model.Match;

public class RepositoryMatches {

	List<Match> matches = new ArrayList<Match>();

	public void addMatch(Match match) {

		matches.add(match);
		System.out.println("Partida adicionada com sucesso!!");
	}

	public List<Match> getMatches() {
		return matches;
	}

}
