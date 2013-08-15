package Mancala;

public class MancalaTest {
	public static void main (String[] args){
		Mancala board = new Mancala();
		MinimaxAgent agent = new MinimaxAgent(1, 5);
		board.print();
		board.makeMove(agent.getMove(board));
		board.print();
	}
	

}
