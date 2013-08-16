package mazes;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import mazes.Maze;

public class MazeView extends JFrame {
	private Maze maze;
	private static final int HORIZONTAL_MARGIN = 30;
	private static final int VERTICAL_MARGIN = 5;
	private static final int CELL_SIZE = 15;

	private boolean labelTargets = false;

	public MazeView(Maze maze) {
		super("Maze");
		this.maze = maze;
		if (maze == null) {
			JOptionPane.showMessageDialog(this, "No solution maze.");
			setEnabled(false);
		} else {
			setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			setVisible(true);
			setPreferredSize(new Dimension(
						maze.getWidth() * CELL_SIZE + 2 * VERTICAL_MARGIN, 
						maze.getHeight() * CELL_SIZE + HORIZONTAL_MARGIN + VERTICAL_MARGIN));
			setSize(getPreferredSize());
			setResizable(false);
		}
	}

	public void paint(Graphics g) {
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, getWidth(), getHeight());
		for (int i = 0 ; i < maze.getWidth() ; i++) {
			for (int j = 0 ; j < maze.getHeight() ; j++) {
				switch (maze.get(i, j).getState()) {
				case Maze.PASSABLE: 
					if (maze.get(i, j).isVisited()) {
						g.setColor(Color.GRAY);
					} else {
						g.setColor(Color.WHITE);
					} break;
				case Maze.IMPASSABLE: g.setColor(Color.BLACK); break;
				case Maze.BEGIN: g.setColor(Color.GREEN); break;
				case Maze.TARGET:
					g.setColor(Color.RED); 
				break;
				}
				g.fillRect(i * CELL_SIZE + VERTICAL_MARGIN, j * CELL_SIZE + HORIZONTAL_MARGIN, CELL_SIZE, CELL_SIZE);
			}
		}

		if (labelTargets) {
			g.setColor(Color.WHITE);
			for (int i = 0 ; i < maze.getWidth() ; i++) {
				for (int j = 0 ; j < maze.getHeight() ; j++) {
					if (maze.get(i,j).getState() == Maze.TARGET) {
						g.drawString("" + maze.get(i, j).data.targetNumber,
								i * CELL_SIZE + VERTICAL_MARGIN, 
								(j + 1) * CELL_SIZE + HORIZONTAL_MARGIN);
					}
				}
			}
		}
	}

}