

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Comparator;
import java.util.EmptyStackException;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.Stack;

/**
 * Board represents an n x n board in the war game.
 * @author Keshav Saharia
 */

public class Board {
	private int[] score;						// the array storing the score of each player
	private int[][] board;						// the array storing square values
	private boolean[][][] control;				// a boolean array for quick control lookup
	private int activePlayer = Game.PLAYER1;	// stores ID of active player
	private int inactivePlayer = Game.PLAYER2;	// store ID of inactive player
	private static final int SIZE = 5;			// The side length of the board
	private int emptySquares;					// The number of empty squares on this board
	
	private Stack <Move> moveHistory;			// Keeps track of all pieces added and pieces that changed hands
	private Stack <Integer> moveLength;			// Keeps track of the number of pieces added/changing hands in each turn
	
	/**
	 * Main constructor, creates a new board from a specified file.
	 * @param file - the name of an input file
	 */
	public Board(String file) {
		initialize();
		loadBoardFromFile(file);
	}
	
	/**
	 * Copy constructor, creates a new board that is a copy of another instance of Board.
	 */
	public Board(Board other) {
		initialize();
		loadBoardFromBoard(other);
	}
	
	/**
	 * Initializes the internal data for this Board instance.
	 */
	private void initialize() {
		moveHistory = new Stack <Move> ();
		moveLength = new Stack <Integer> ();
		
		emptySquares = SIZE * SIZE;
		score = new int[2];
		score[0] = score[1] = 0;
		board = new int[SIZE][SIZE];
		control = new boolean[SIZE][SIZE][2];
		for (int i = 0 ; i < SIZE ; i++)
			for (int j = 0 ; j < SIZE ; j++)
				for (int k = 0 ; k < 2 ; k++)
					control[i][j][k] = false;
	}
	
	/**
	 * Returns an array of legal moves that can be made on this board.
	 */
	public Move[] getLegalMoves() {
		PriorityQueue <Move> moves = new PriorityQueue <Move> (SIZE * SIZE, new MoveComparator());
		// Tests for legal drops by the active player
		for (int i = 0 ; i < SIZE ; i++)
			for (int j = 0 ; j < SIZE ; j++)
				if (canDrop(i, j)) moves.add(new Move(Game.DROP, i, j));
		
		// Tests for legal commando blitzes by the active player
		for (int i = 0 ; i < SIZE ; i++)
			for (int j = 0 ; j < SIZE ; j++)
				if (canBlitz(i, j)) moves.add(new Move(Game.BLITZ, i, j));
		
		return moves.toArray(new Move[moves.size()]);
	}
	
	/**
	 * Used for ordering the priority queue used for move ordering. The moves are ordered by the value that they
	 * generate by being moved.
	 */
	private class MoveComparator implements Comparator <Move> {
		
		/**
		 * Returns a metric for comparing move m0 to move m1.
		 */
		public int compare(Move m0, Move m1) {
			if (m0.getValue() == 0) {
				int m0value = getValue(m0.getX(), m0.getY());
				if (m0.getType() == Game.BLITZ) {
					m0value += (hasOpponentControl(m0.getX() + 1, m0.getY())) ? getValue(m0.getX() + 1, m0.getY()) : 0;
					m0value += (hasOpponentControl(m0.getX(), m0.getY() + 1)) ? getValue(m0.getX(), m0.getY() + 1) : 0;
					m0value += (hasOpponentControl(m0.getX() - 1, m0.getY())) ? getValue(m0.getX() - 1, m0.getY()) : 0;
					m0value += (hasOpponentControl(m0.getX(), m0.getY() - 1)) ? getValue(m0.getX(), m0.getY() - 1) : 0;
				}
				m0.setValue(m0value);
			}
			if (m1.getValue() == 0) {
				int m1value = getValue(m1.getX(), m1.getY());
				if (m1.getType() == Game.BLITZ) {
					m1value += (hasOpponentControl(m1.getX() + 1, m1.getY())) ? getValue(m1.getX() + 1, m1.getY()) : 0;
					m1value += (hasOpponentControl(m1.getX(), m1.getY() + 1))  ? getValue(m1.getX(), m1.getY() + 1) : 0;
					m1value += (hasOpponentControl(m1.getX() - 1, m1.getY())) ? getValue(m1.getX() - 1, m1.getY()) : 0;
					m1value += (hasOpponentControl(m1.getX(), m1.getY() - 1)) ? getValue(m1.getX(), m1.getY() - 1) : 0;
				}
			}
			return m1.getValue() - m0.getValue();
		}
	}
	
	/**
	 * Returns true if the game is over.
	 */
	public boolean gameOver() {
		return (emptySquares <= 0);
	}
	
	/**
	 * Drops a piece belonging to the active player on the given x, y coordinate, returning the score from the drop
	 * if the drop was successful and 0 otherwise.
	 */
	public int drop(int x, int y) {
		if (canDrop(x, y)) {
			setControl(x, y);
			moveHistory.push(new Move(Game.DROP, x, y));
			moveLength.push(1);
			return getValue(x, y);
		}
		return 0;
	}
	
	/**
	 * Returns true if a piece belonging to the active player can be dropped at the given x, y position.
	 */
	public boolean canDrop(int x, int y) {
		return (validCoordinates(x, y) && (!control[x][y][0]) && (!control[x][y][1]));
	}
	
	/**
	 * Blitzes the given square for the active player on the given x, y coordinate, and returns the score generated
	 * from the blitz, or 0 if unsuccessful.
	 */
	public int blitz(int x, int y) {
		if (canBlitz(x, y)) {
			setControl(x, y);
			moveHistory.push(new Move(Game.DROP, x, y));
			int score = getScore(activePlayer);
			int moves = 1;
			if (hasOpponentControl(x - 1, y)) {
				switchControl(x - 1, y);
				moveHistory.push(new Move(Game.BLITZ, x - 1, y));
				moves++;
			}
			if (hasOpponentControl(x, y + 1)) {
				switchControl(x, y + 1);
				moveHistory.push(new Move(Game.BLITZ, x, y + 1));
				moves++;
			}
			if (hasOpponentControl(x + 1, y)) {
				switchControl(x + 1, y);
				moveHistory.push(new Move(Game.BLITZ, x + 1, y));
				moves++;
			}
			if (hasOpponentControl(x, y - 1)) {
				switchControl(x, y - 1);
				moveHistory.push(new Move(Game.BLITZ, x, y - 1));
				moves++;
			}
			moveLength.push(moves);
			return getScore(activePlayer) - score;
		}
		return 0;
	}
	
	/**
	 * Returns true if the active player can blitz the given square.
	 */
	public boolean canBlitz(int x, int y) {
		if (!canDrop(x, y)) return false;
		return ((hasControl(x - 1, y) || hasControl(x, y + 1) || hasControl(x + 1, y) || hasControl(x, y - 1)) && 
			(hasOpponentControl(x - 1, y) || hasOpponentControl(x, y + 1) || hasOpponentControl(x + 1, y) || hasOpponentControl(x, y - 1)));
	}
	
	/**
	 * Moves the board backward by a certain number of moves, by deleting moves from the move history.
	 */
	public void unmove(int steps) {
		for (int i = 0 ; i < steps ; i++) {
			try {
				int length = moveLength.pop();
				for (int j = 0 ; j < length ; j++) {
					Move m = moveHistory.pop();
					if (m.getType() == Game.DROP)
						removeControl(m.getX(), m.getY());
					else if (m.getType() == Game.BLITZ)
						switchControl(m.getX(), m.getY());
				}
			} catch (EmptyStackException e) { }
		}
		if (steps % 2 == 1) switchPlayer();
	}
	
	/**
	 * Returns the intrinsic value of the given square on the board.
	 */
	public int getValue(int x, int y) {
		if (validCoordinates(x, y))
			return board[x][y];
		return 0;
	}
	
	/**
	 * Returns the score of the player with the given ID.
	 */
	public int getScore(int playerID) {
		if (playerID == Game.PLAYER1 || playerID == Game.PLAYER2)
			return score[playerID];
		return 0;
	}
	
	/**
	 * Returns the score advantage of the given player ID.
	 */
	public int getScoreAdvantage(int playerID) {
		if (gameOver()){
			return (score[playerID] > score[1-playerID]) ? Integer.MAX_VALUE : 
				((score[0] == score[1]) ? 0 :  Integer.MIN_VALUE);
		}
		if (playerID == Game.PLAYER1 || playerID == Game.PLAYER2)
			return score[(playerID == Game.PLAYER1) ? 0 : 1] - score[(playerID == Game.PLAYER1) ? 1 : 0];
		return 0;
	}

	/**
	 * Rotates players, making the active player the inactive player and vice versa.
	 * @return a reference to the board instance.
	 */
	public Board switchPlayer() {
		int temp = inactivePlayer;
		inactivePlayer = activePlayer;
		activePlayer = temp;
		return this;
	}
	
	/**
	 * Returns the active player.
	 */
	public int getPlayer() {
		return activePlayer;
	}
	
	/**
	 * Returns true if the given x, y coordinates are valid.
	 */
	private boolean validCoordinates(int x, int y) {
		return (x >= 0 && x < SIZE) && (y >= 0 && y < SIZE);
	}
	
	/**
	 * Returns true if the active player controls the given coordinates.
	 */
	public boolean hasControl(int x, int y) {
		if (! validCoordinates(x, y)) return false;
		return control[x][y][activePlayer];
	}
	
	/**
	 * Returns true if the inactive player controls the given coordinates.
	 */
	public boolean hasOpponentControl(int x, int y) {
		if (! validCoordinates(x, y)) return false;
		return control[x][y][inactivePlayer];
	}
	
	/**
	 * Returns the ID of the player that controls the given square, or
	 * -1 if the square is not controlled.
	 */
	public int getControl(int x, int y) {
		if (! validCoordinates(x, y)) return -1;
		if (control[x][y][0]) return 0;
		if (control[x][y][1]) return 1;
		return -1;
	}
	
	/**
	 * Sets control of the given x, y coordinate to the active player.
	 */
	private void setControl(int x, int y) {
		if (! validCoordinates(x, y)) return;
		control[x][y][activePlayer] = true;
		control[x][y][inactivePlayer] = false;
		emptySquares--;
		score[activePlayer] += getValue(x, y);
	}
	
	/**
	 * Switches control of a given x, y coordinate from one player to another.
	 */
	private void switchControl(int x, int y) {
		if (! validCoordinates(x, y)) return;
		if (control[x][y][activePlayer]) {
			control[x][y][activePlayer] = false;
			control[x][y][inactivePlayer] = true;
			score[activePlayer] -= getValue(x, y);
			score[inactivePlayer] += getValue(x, y);
		}
		else if (control[x][y][inactivePlayer]) {
			control[x][y][activePlayer] = true;
			control[x][y][inactivePlayer] = false;
			score[activePlayer] += getValue(x, y);
			score[inactivePlayer] -= getValue(x, y);
		}
	}
	
	/**
	 * Removes control of a square from any player.
	 */
	private void removeControl(int x, int y) {
		if (! validCoordinates(x, y)) return;
		if (control[x][y][activePlayer])
			score[activePlayer] -= getValue(x, y);
		if (control[x][y][inactivePlayer])
			score[inactivePlayer] -= getValue(x, y);
		control[x][y][activePlayer] = false;
		control[x][y][inactivePlayer] = false;
	}
	
	/**
	 * Loads a representation of a game board from a file.
	 */
	private void loadBoardFromFile(String file) {
		StringBuilder contents = new StringBuilder();
		try {
			Scanner s = new Scanner(new File(file));
			while (s.hasNextLine()) {
				contents.append(s.nextLine());
				contents.append(" ");
			}
		} catch (FileNotFoundException e) {
			System.out.println("File not found: " + file);
			e.printStackTrace();
		}
		if (contents.length() > 0) {
			String[] tokens = contents.toString().split("\\s");
			for (int i = 0 ; i < SIZE ; i++)
				for (int j = 0 ; j < SIZE ; j++)
					if (tokens[i * SIZE + j].equals(""))
						board[i][j] = 0;
					else
						board[i][j] = Integer.parseInt(tokens[i * SIZE + j]);
		}
	}
	
	/**
	 * Loads a board from another board.
	 */
	private void loadBoardFromBoard(Board other) {
		for (int i = 0 ; i < SIZE ; i++) {
			for (int j = 0 ; j < SIZE ; j++) {
				board[i][j] = other.board[i][j];
				control[i][j][0] = other.control[i][j][0];
				control[i][j][1] = other.control[i][j][1];
			}
		}
		this.score[0] = other.score[0];
		this.score[1] = other.score[1];
		this.activePlayer = other.activePlayer;
		this.inactivePlayer = other.inactivePlayer;
		this.emptySquares = other.emptySquares;
		this.moveHistory = new Stack <Move> ();
		this.moveLength = new Stack <Integer> ();
	}
	
	/**
	 * Returns a string representation of the board.
	 */
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (int i = 0 ; i < SIZE ; i++) {
			for (int j = 0 ; j < SIZE ; j++) {
				sb.append(" ");
				if (getControl(j, i) >= 0 || getControl(j - 1, i) >= 0)
					sb.append("=====");
				else
					sb.append("-----");
			}
			sb.append(" \n");
			for (int j = 0 ; j < SIZE ; j++) {
				sb.append("| ");
				if (getControl(j, i) >= 0)
					sb.append("[" + (getControl(j, i) + 1) + "] ");
				else
					sb.append("    ");
			}
			sb.append("|\n");
			for (int j = 0 ; j < SIZE ; j++) {
				sb.append("| ");
				if (getValue(j, i) >= 100)
					sb.append(getValue(j, i));
				else if (getValue(j, i) >= 10)
					sb.append(" " + getValue(j, i));
				else
					sb.append(" " + getValue(j, i) + " ");
				sb.append(" ");
			}
			sb.append("|\n");
			for (int j = 0 ; j < SIZE ; j++) {
				sb.append("|     ");
			}
			sb.append("|\n");
		}
		for (int j = 0 ; j < SIZE ; j++) {
			sb.append(" ");
			if (getControl(j, SIZE - 1) >= 0)
				sb.append("=====");
			else
				sb.append("-----");
		}
		sb.append(" \n");
		sb.append("     Score: [1] : ");
		sb.append(getScore(Game.PLAYER1));
		sb.append("    [2] : ");
		sb.append(getScore(Game.PLAYER2));
		sb.append("\n");
		return sb.toString();
	}
	
	/**
	 * Returns the length of the edge of the board.
	 */
	public int getSize() {
		return SIZE;
	}
	
	/**
	 * Returns the number of empty squares on this board.
	 */
	public int getEmptySquares() {
		return emptySquares;
	}
	
	/**
	 * Returns the number of occupied squares on this board.
	 */
	public int getOccupiedSquares() {
		return SIZE * SIZE - emptySquares;
	}
}
