package model;

public class Team {

	String name;
	int matches;
	int matchGoals;
	int matchGoalsFor;
	int matchGoalsAverage;
	int over15;
	int over25;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getMatches() {
		return matches;
	}

	public void setMatches(int matches) {
		this.matches = matches;
	}

	public int getMatchGoals() {
		return matchGoals;
	}

	public void setMatchGoals(int matchGoals) {
		this.matchGoals = matchGoals;
	}

	public int getMatchGoalsFor() {
		return matchGoalsFor;
	}

	public void setMatchGoalsFor(int matchGoalsFor) {
		this.matchGoalsFor = matchGoalsFor;
	}

	public int getMatchGoalsAverage() {
		return matchGoalsAverage;
	}

	public void setMatchGoalsAverage(int matchGoalsAverage) {
		this.matchGoalsAverage = matchGoalsAverage;
	}

	public int getOver15() {
		return over15;
	}

	public void setOver15(int over15) {
		this.over15 = over15;
	}

	public int getOver25() {
		return over25;
	}

	public void setOver25(int over25) {
		this.over25 = over25;
	}

}
