package qbot;

import java.awt.Dimension;

public class Arena {
	public static final int ARENA_MINI = 1;
	public static final int ARENA_SMALL = 2;
	public static final int ARENA_MEDIUM = 3;
	public static final int ARENA_LARGE = 4;
	public static final int ARENA_HUGE = 6;
	
	private Block[] blocks;
	private QBot left, right;
	private Dimension size;
	
	public Arena(QBot left, QBot right) {
		this(left, right, ARENA_LARGE);
	}
	
	public Arena(QBot left, QBot right, int size) {
		this.size = getDimensions(size);
		initializeBlocks(left, right, size);
		initializeBots(left, right);
	}
	
	public void update() {
		left.update(new Snapshot(left.getPosition(), right.getPosition(), blocks));
		right.update(new Snapshot(right.getPosition(), left.getPosition(), blocks));
	}

	private void initializeBots(QBot left, QBot right) {
		this.left = left;
		left.setPosition(new Vector(size.getWidth() / 4, size.getHeight() / 2));
		this.right = right;
		right.setPosition(new Vector(size.getWidth() * 3 / 4, size.getHeight() / 2));
		right.setReverse();
	}

	private void initializeBlocks(QBot left, QBot right, int s) {
		blocks = new Block[s * 4 - 2];
		for (int i = 0 ; i < blocks.length ; i++) {
			blocks[i] = new Block(new QBot[] { left, right }, new Vector(size.getWidth() / 2, size.getHeight() * (i + 1) / s / 4));
		}
		// Random block generation
		/*for (int i = 0 ; i < blocks.length ; i++) {
			int dx = (int) (Math.random() * size.getWidth() / 4);
			int dy = (int) (Math.random() * size.getHeight() * 5 / 6);
			blocks[i] = new Block(new Vector(size.getWidth() * 3 / 8 + dx, size.getHeight() / 12 + dy));
		}*/
	}
	
	public Block[] getBlocks() {
		return blocks;
	}
	
	public QBot getLeft() {
		return left;
	}
	
	public QBot getRight() {
		return right;
	}
	
	public int getWidth() {
		return (int) size.getWidth();
	}
	
	public int getHeight() {
		return (int) size.getHeight();
	}
	
	private Dimension getDimensions(int size) {
		if (size == ARENA_MINI)
			return new Dimension(300, 300);
		if (size == ARENA_SMALL)
			return new Dimension(400, 400);
		if (size == ARENA_MEDIUM)
			return new Dimension(500, 500);
		if (size == ARENA_HUGE)
			return new Dimension(1000, 1000);
		return new Dimension(600, 600);
	}
}
