package Mancala;

import static org.junit.Assert.*;

import org.junit.Test;

public class MancalaBoardTest {

	@Test
	public void testBoard() {
		Mancala board = new Mancala();
		board.print();
	}
	
	@Test
	public void testMove() {
		Mancala board = new Mancala();
		board.makeMove(1);
		board.print();
		
	}

}
