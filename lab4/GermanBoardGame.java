import java.util.ArrayList;
import java.util.Random;

/**
 * GermanBoardGame.java
 */

/**
 * Class to represent German board games. These games are like regular board games with two exceptions. 
 * They never rely on any luck, and only people over 10 years of age play them.
 * 
 * @author Matthew Hoover (mjh4402@rit.edu)
 *
 */
public class GermanBoardGame extends BoardGame {

	/**
	 * Constructor, takes a name and number of players
	 * 
	 * @param n Name of the game
	 * @param np Number of players of the game
	 */
	public GermanBoardGame(String n, int np) {
		super(n, np, 0);
	}

	/**
	 * Chooses players to play the game at random, but will not choose any player age 10 or under.
	 * 
	 * @param players List of players to choose from
	 * @param The number of players to pick
	 */
	@Override
	public void pickPlayers(ArrayList<Player> players, int num) {
		while (this.players.size() < num) {
			int rand = new Random().nextInt(players.size());
			if (players.get(rand).getAge() > 10) {
				if (!(this.isPlaying(players.get(rand)))) {
					this.players.add(players.get(rand));

				}
			}
		}
	}
}