package repository;

import java.util.ArrayList;
import java.util.List;

import model.Team;

public class RepositoryTeams {

	List<Team> teams = new ArrayList<Team>();

	public void addTeam(Team team) {

		teams.add(team);
		System.out.println("Clube adicionado com sucesso!!" + team.getName());

	}

	public boolean hastTeambyName(String name) {

		boolean exist = false;

		for (Team team : teams) {

			if (team.getName().equals(name)) {

				exist = true;
			}
		}

		return exist;
	}

	public Team searchTeam(String name) {

		Team teamx = new Team();

		for (Team team : teams) {

			if (team.getName().equals(name)) {

				teamx = team;
			}
		}

		return teamx;

	}

	public List<Team> getTeams() {
		return teams;
	}

}
