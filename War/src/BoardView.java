

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;

import javax.swing.JFrame;

/**
 * BoardView is a GUI instance that displays a Board instance and can dynamically refresh the view of the board
 * during a game of war.
 * @author Keshav Saharia
 */
@SuppressWarnings("serial")
public class BoardView extends JFrame {
	private Board board; 					// Reference to the Board instance that this GUI is representing
	private Agent blue, green;				// Reference to the two agents on the board
	private static final int SIZE = 50;		// The cell size for each square on the board
	private static final int MARGIN = 20; 	// The size of the margin around the board
	
	/**
	 * Default constructor, takes a Board instance to represent.
	 */
	public BoardView(Board board) {
		super("War");
		this.board = board;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		setPreferredSize(new Dimension(5 * SIZE + MARGIN * 2, 5 * SIZE + MARGIN * 5));
		setSize(getPreferredSize());
		setResizable(false);
	}
	
	/**
	 * Sets the Board instance associated with this view to the given one.
	 */
	public void setBoard(Board b) {
		this.board = b;
		refresh();
	}
	
	/**
	 * Sets the reference to the two agents playing on this board to the given ones.
	 */
	public void setAgents(Agent blue, Agent green) {
		this.blue = blue;
		this.green = green;
	}

	/**
	 * Paints the GUI with the given Graphics object.
	 */
	public void paint(Graphics g) {
		g.setColor(Color.WHITE);
		g.setFont(new Font("Arial", 0, 30));
		g.fillRect(0, 0, getWidth(), getHeight());
		if (board != null) {
			for (int i = 0 ; i < board.getSize() ; i++) {
				for (int j = 0 ; j < board.getSize() ; j++) {
					paintCell(g, i, j);
					paintControl(g, i, j);
					paintValue(g, i, j);
				}
			}
			paintScore(g);
		}
	}
	
	/**
	 * Paints a cell at the given (x, y) coordinate using the given Graphics object.
	 */
	private void paintCell(Graphics g, int x, int y) {
		g.setColor(Color.lightGray);
		g.fillRect(MARGIN + x * SIZE, MARGIN * 2 + y * SIZE, 40, 40);
	}
	
	/**
	 * Paints the control pieces of each player with the given Graphics object.
	 */
	private void paintControl(Graphics g, int x, int y) {
		if (board.getControl(x, y) < 0) return;
		if (board.getControl(x, y) == Game.PLAYER1)
			g.setColor(Color.BLUE);
		else
			g.setColor(Color.GREEN);
		g.fillOval(MARGIN + x * SIZE, MARGIN * 2 + y * SIZE, 40, 40);
	}
	
	/**
	 * Paints the value of the square at the given (x, y) coordinate with the given Graphics object.s
	 */
	private void paintValue(Graphics g, int x, int y) {
		g.setColor(Color.BLACK);
		g.drawString("" + board.getValue(x, y), 
				MARGIN * 3 / 2 + x * SIZE, (y + 1) * SIZE + MARGIN);
	}
	
	/**
	 * Paints the score of each player, as well as their names and properties.
	 * @param g
	 */
	private void paintScore(Graphics g) {
		g.setFont(new Font("Arial", 0, 20));
		g.setColor(Color.BLUE);
		g.fillOval(MARGIN * 2, 5 * SIZE + 2 * MARGIN, SIZE / 2, SIZE / 2);
		g.setColor(Color.BLACK);
		g.drawString("" + board.getScore(Game.PLAYER1), 
				MARGIN * 2 + SIZE, 5 * SIZE + 3 * MARGIN);
		g.setColor(Color.GREEN);
		g.fillOval(4 * SIZE - MARGIN * 2, 5 * SIZE + 2 * MARGIN, SIZE / 2, SIZE / 2);
		g.setColor(Color.BLACK);
		g.drawString("" + board.getScore(Game.PLAYER2), 
				5 * SIZE - MARGIN * 2, 5 * SIZE + 3 * MARGIN);
		g.setFont(new Font("Arial", 0, 10));
		if (blue != null) {
			g.drawString(blue.getName(), 
					MARGIN * 2, 5 * SIZE + 4 * MARGIN);
			g.drawString(blue.getProperty(), 
					MARGIN * 2, 5 * SIZE + 9 * MARGIN / 2);
		}
		if (green != null) {
			g.drawString(green.getName(), 
					4 * SIZE - MARGIN * 2, 5 * SIZE + 4 * MARGIN);
			g.drawString(green.getProperty(), 
					4 * SIZE - MARGIN * 2, 5 * SIZE + 9 * MARGIN / 2);
		}
	}
	
	/**
	 * Refreshes this view by repainting the GUI.
	 */
	public void refresh() {
		repaint();
	}
	
	/**
	 * Returns a Point instance giving the decoded coordinates for the given click position.  
	 * @return
	 */
	public static Point decode(int x, int y) {
		return new Point((x - MARGIN) / SIZE, (y - MARGIN * 2) / SIZE);
	}
}
