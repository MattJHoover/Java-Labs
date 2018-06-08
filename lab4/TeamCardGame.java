import java.util.ArrayList;

/**
 * TeamCardGame.java
 */

/**
 * Represents a card game played with two teams of two (such as bridge, spades, etc).
 * 
 * @author Matthew Hoover (mjh4402@rit.edu)
 *
 */
public class TeamCardGame extends Game {

	/**
	 * Constructor. Assumes all such games are played by four players.
	 * 
	 * @param n
	 * @param np
	 */
	public TeamCardGame(String n) {
		super(n, 4);
	}

	/**
	 * Plays the game, selecting a winning team according to total team intelligence. 
	 * This is computed for each team as the team's higher intelligence value plus twice the team's lower intelligence value.
	 */
	@Override
	public void play() {
		System.out.println("Playing " + this.name + "...");
		ArrayList<Player> playing = new ArrayList<Player>();

		for (Player p : this.players) {
			if (this.isPlaying(p)) {
				playing.add(p);
			}
		}

		Team<Player> teamOne = new Team<Player>(playing.get(0), playing.get(1));
		Team<Player> teamTwo = new Team<Player>(playing.get(2), playing.get(3));

		if (teamOne.getIntelligence() > teamTwo.getIntelligence()) {
			teamOne.getObject(1).youWin(); teamOne.getObject(2).youWin();
			System.out.println("Winning team is " + teamOne.getObject(1).getName() + " and " + teamOne.getObject(2).getName());
		}
		else {
			teamTwo.getObject(1).youWin(); teamTwo.getObject(2).youWin();
			System.out.println("Winning team is " + teamTwo.getObject(1).getName() + " and " + teamTwo.getObject(2).getName());
		}

	}

}
