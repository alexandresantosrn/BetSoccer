package repository;

import java.util.ArrayList;
import java.util.List;

import model.Team;

public class RepositoryTeams {

	List<Team> teams = new ArrayList<Team>();

	public void addTeam(Team team) {

		teams.add(team);
		System.out.println("Clube adicionado com sucesso!!");

	}

}
