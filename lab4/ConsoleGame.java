/**
 * ConsoleGame.java
 */

/**
 * Represents a console game. Success at console games always depends on dexterity, and may optionally depend on intelligence as well.
 * 
 * @author Matthew Hoover (mjh4402@rit.edu)
 *
 */
public class ConsoleGame extends Game {

	/**
	 * Whether or not the game uses intelligence
	 */
	private boolean usesBrains;

	/**
	 * Constructor, takes three args to set the instance variables
	 * 
	 * @param name Name of the game
	 * @param np Number of players that can play the game at once
	 * @param usesBrains Whether or not the game uses intelligence
	 */
	public ConsoleGame(String name, int np, boolean usesBrains) {
        super(name,np);
        this.usesBrains = usesBrains;
	}

	/**
	 * Plays the game and chooses a winner. 
	 * Winner is chosen either as the player with highest dexterity (if the game does not use intelligence) or highest (dexterity + intelligence) otherwise.
	 */
	@Override
	public void play() {
		System.out.println("Playing " + this.name + "...");
		Player highest = null;

		if (this.usesBrains) {
			for (Player p : this.players) {
				if (highest == null) {
					highest = p;
				}
				else if (this.isPlaying(p)) {
					if ( (p.getDexterity() + p.getIntelligence()) > (highest.getDexterity() + highest.getIntelligence())) {
						highest = p;
					}
				}
			}	
		}
		else {
			for (Player p : this.players) {
				if (highest == null) {
					highest = p;
				}
				else if (this.isPlaying(p)) {
					if (p.getDexterity() > highest.getDexterity()) {
						highest = p;
					}
				} 
			}
		}
		System.out.println("Winner is " + highest.getName());
		highest.youWin();
	}

}