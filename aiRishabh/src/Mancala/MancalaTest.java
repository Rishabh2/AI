package Mancala;

import java.util.Scanner;

public class MancalaTest {
	public static void main (String[] args){
		Mancala board = new Mancala();
		MinimaxAgent agent = new MinimaxAgent(1, 5);
		Scanner keyboard = new Scanner(System.in);
		
		for (int i = 0; i < 3; i++){
		board.print();
		board.makeMove(agent.getMove(board));
		board.print();
		board.switchPlayer();
		System.out.print("Pocket: ");
		int pocket = keyboard.nextInt();
		board.makeMove(pocket);
		board.switchPlayer();
		}
	

	}
}
