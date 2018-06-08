/**
 * Team.java
 */

/**
 * Class to represent a Team of two different Players.
 * 
 * @author Matthew Hoover (mjh4402@rit.edu)
 *
 */
public class Team<T extends Player> {

	private T playerOne, playerTwo;

	/**
	 * 
	 */
	public Team(T pOne, T pTwo) {
		this.playerOne = pOne;
		this.playerTwo = pTwo;
	}

	/**
	 * This is computed for each team as the team's higher intelligence value plus twice the team's lower intelligence value.
	 * 
	 * @return Integer value for team intelligence.
	 */
	public int getIntelligence() {
		if (this.playerOne.getIntelligence() >= this.playerTwo.getIntelligence()) {
			return this.playerOne.getIntelligence() + (2 * this.playerTwo.getIntelligence());
		}
		else {
			return this.playerTwo.getIntelligence() + (2 * this.playerOne.getIntelligence());
		}
	}

	public T getObject(int x) {
		if (x == 1) {
			return this.playerOne;
		}
		else {
			return this.playerTwo;
		}
	}

}
