/**
 * A Move instance describes a move that an agent makes on a board.
 */

public class Move {
	private int move;
	private int x, y;
	private int value;
	
	/**
	 * Default constructor, takes a type of move (drop or blitz) and the x, y coordinate
	 * to perform the move at.
	 */
	public Move(int move, int x, int y) {
		this.move = move;
		this.x = x;
		this.y = y;
	}
	
	/**
	 * Constructor for storing game state as a Move instance.
	 * @param value
	 */
	public Move(int value) {
		this.move = Game.GAMESTATE;
		this.value = value;
	}
	
	/**
	 * Copy constructor, creates a Move instance identical to the parameter Move.
	 */
	public Move(Move other) {
		this.move = other.move;
		this.x = other.x;
		this.y = other.y;
		this.value = other.value;
	}
	
	/**
	 * 
	 * @param board
	 * @return
	 */
	public Board move(Board board) {
		if (board == null) return null;
		if (move == Game.DROP)
			if(board.drop(x, y) > 0) return board.switchPlayer();
		if (move == Game.BLITZ)
			if (board.blitz(x, y) > 0) return board.switchPlayer();
		return board;
	}
	
	public int getType() {
		return move;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public void setValue(int value) {
		this.value = value;
	}
	
	public int getValue() {
		return value;
	}
	
	public String toString() {
		if (move == Game.DROP) {
			return "Drop at " + x + ", " + y;
		}
		if (move == Game.BLITZ) {
			return "Blitz at " + x + ", " + y;
		}
		if (move == Game.GAMESTATE) {
			return "Terminal value of " + value;
		}
		return "";
	}
}
