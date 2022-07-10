package repository;

import java.util.ArrayList;
import java.util.List;

import model.League;

public class RepositoryLeagues {

	List<League> leagues = new ArrayList<League>();

	public void addLeague(League league) {

		leagues.add(league);
		System.out.println("Liga adicionada com sucesso!!");
	}

}
