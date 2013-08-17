

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Runs the war game between two Agent instances.
 * @author Keshav Saharia
 */

public class Game {
	// Players
	public static final int PLAYER1 = 0;
	public static final int PLAYER2 = 1;
	
	// Moves
	public static final int DROP = 2;
	public static final int BLITZ = 3;
	public static final int GAMESTATE = 4;
	
	private Board board;
	private Agent one, two;
	private boolean oneToMove = true;
	
	private String name = "";
	
	public Game(Board board, Agent one, Agent two) {
		this.board = board;
		this.one = one;
		this.one.setBoard(board);
		this.one.setID(PLAYER1);
		this.two = two;
		this.two.setBoard(board);
		this.two.setID(PLAYER2);
	}
	
	public void play() {
		while (! board.gameOver()) {
			playTurn();
		}
	}
	
	public void setName(String name) {
		this.name = name + ", ";
	}
	
	public Agent getAgent(int id) {
		if (id == Game.PLAYER1)
			return one;
		if (id == Game.PLAYER2)
			return two;
		return null;
	}
	
	public void playTurn() {
		if (! board.gameOver()) {
			Move next = null;
			if (oneToMove) {
				next = one.getMove();
				System.out.println(one.getName() + "(" + one.getProperty() +"): " + next);
			}
			else {
				next = two.getMove();
				System.out.println(two.getName() + "(" + two.getProperty() +"): " + next);
			}
			next.move(board);
			oneToMove = ! oneToMove;
		}
	}
	
	public Board getBoard() {
		return board;
	}
}
