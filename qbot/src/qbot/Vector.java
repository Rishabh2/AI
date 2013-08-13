package qbot;

public class Vector {
	public static final int UP = 0;
	public static final int LEFT = 1;
	public static final int RIGHT = 2;
	public static final int DOWN = 3;
	
	private int x;
	private int y;
	
	public Vector(int direction) {
		switch (direction) {
		case UP:
			x = 0; y = 1; break;
		case LEFT:
			x = -1; y = 0; break;
		case RIGHT:
			x = 1; y = 0; break;
		case DOWN:
			x = 0; y = -1; break;
		default:
			x = 0; y = 0; break;
		}
	}
	
	public Vector(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public Vector(double x, double y) {
		this.x = (int) x;
		this.y = (int) y;
	}
	
	public Vector add(Vector other) {
		this.x += other.x;
		this.y += other.y;
		return this;
	}
	
	public double magnitude() {
		return Math.sqrt(x * x + y * y);
	}
	
	public Vector normalize() {
		return new Vector(x / magnitude(), y / magnitude());
	}
	
	public Vector flip() {
		x *= -1;
		return this;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
}
