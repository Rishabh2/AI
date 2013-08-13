package qbot;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

public class ArenaView extends JPanel {
	private Arena arena;
		
	public ArenaView(Arena arena) {
		this.arena = arena;
		setSize(getSize());
	}
	
	public void paintComponent(Graphics g) {
		g.setColor(Color.white);
		g.fillRect(0, 0, getWidth(), getHeight());
		paintZones(g);
		paintBot(g);
		paintBlocks(g);
	}
	
	private void paintBot(Graphics g) {
		QBot left = arena.getLeft();
		QBot right = arena.getRight();
		g.setColor(Color.gray);
		g.fillOval(left.getX() - left.getRadius() - 2, left.getY() - left.getRadius() - 2, left.getRadius() * 2 + 4, left.getRadius() * 2 + 4);
		g.setColor(Color.red);
		g.fillOval(left.getX() - left.getRadius(), left.getY() - left.getRadius(), left.getRadius() * 2, left.getRadius() * 2);
		g.setColor(Color.gray);
		g.fillOval(right.getX() - right.getRadius() - 2, right.getY() - right.getRadius() - 2, right.getRadius() * 2 + 4, right.getRadius() * 2 + 4);
		g.setColor(Color.blue);
		g.fillOval(right.getX() - right.getRadius(), right.getY() - right.getRadius(), right.getRadius() * 2, right.getRadius() * 2);
	}
	
	private void paintBlocks(Graphics g) {
		g.setColor(Color.darkGray);
		for (Block block : arena.getBlocks()) {
			g.fillRect(block.getX() - block.getRadius(), block.getY() - block.getRadius(), block.getRadius() * 2, block.getRadius() * 2);
		}
	}
	
	private void paintZones(Graphics g) {
		g.setColor(Color.blue);
		g.fillRect(0, 0, arena.getWidth() / 20, arena.getHeight());
		g.setColor(Color.red);
		g.fillRect(arena.getWidth() * 19 / 20, 0, arena.getWidth() / 20, arena.getHeight());
	}

	public Dimension getSize() {
		return new Dimension(600, 600);
	}
}
