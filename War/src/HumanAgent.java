

import java.util.Scanner;

/**
 * HumanAgent extends the Agent class to describe a human-controlled agent to play the
 * game of war. 
 * @author Keshav Saharia
 */
public class HumanAgent implements Agent {
	private Scanner s;		// A Scanner for capturing keyboard input from the user
	private Board board;	// A reference to the Board instance that this agent is playing on
	private int ID;			// A unique player ID for this agent

	/**
	 * Default constructor.
	 */
	public HumanAgent() {
		s = new Scanner(System.in);
	}
	
	/**
	 * Returns the move selected by the human agent, by asking the human for input through console input.
	 */
	public Move getMove() {
		System.out.println("Player " + (ID + 1) + ": ");
		while (true) {
			int move = -1, x = -1, y = -1;
			// Ask to drop or blitz on a given square
			System.out.print("     > (D)rop or (B)litz: ");
			String input = s.nextLine();
			if (input.toUpperCase().startsWith("D"))
				move = Game.DROP;
			else if (input.toUpperCase().startsWith("B"))
				move = Game.BLITZ;
			else
				continue;
			// Get the coordinates of a square
			System.out.print("     > Enter coordinates as x, y: ");
			input = s.nextLine();
			String[] coordinates = input.split(",");
			if (coordinates.length == 2) {
				x = Integer.parseInt(coordinates[0].trim());
				y = Integer.parseInt(coordinates[1].trim());
			}
			// If the move is valid, return it, otherwise repeat the prompt.
			if ((move == Game.DROP) ? board.canDrop(x, y) : board.canBlitz(x, y))
				return new Move(move, x, y);
			else
				System.out.println("     > Invalid choice: " + ((move == Game.DROP) ? "Drop" : "Blitz") + " at " + x + ", " + y);
		}
	}

	/**
	 * Sets the internal Board instance to the given Board.
	 */
	public void setBoard(Board board) {
		this.board = board;
	}

	/**
	 * Returns the score of this agent.
	 */
	public int getScore() {
		return this.board.getScore(ID);
	}
	
	/**
	 * Returns the unique player ID of this agent.
	 */
	public int getID() {
		return ID;
	}

	/**
	 * Sets the unique player ID of this agent.
	 */
	public void setID(int id) {
		this.ID = id;
	}
	
	/**
	 * Returns the name of this agent.
	 */
	public String getName() {
		return "Human " + (ID + 1);
	}
	
	/**
	 * Returns any properties associated with this agent.
	 */
	public String getProperty() {
		return "";
	}
}
