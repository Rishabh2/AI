

public class Driver {
	public static void main(String[] args) {
		test("WallaWalla", new MinimaxAgent(4), new MinimaxAgent(4));
		//String[] maps = {"Wallawalla", "Punxsutawney", "Kalamazoo", "Peoria", "Piqua"};
//		int[][] matchups = {{8, 9}, {10, 10}};
//		String map = "Piqua";
//		for (int i = 3 ; i <= 9 ; i++)
//			test(map, new AlphaBetaAgent(i), new AlphaBetaAgent(i));
//		for (int[] matchup : matchups)
//			test(map, new AlphaBetaAgent(matchup[0]), new AlphaBetaAgent(matchup[1]));
//		
		// test("Kalamazoo", new MinimaxAgent(3), new MinimaxAgent(5));
		//test("Punxsutawney", new AlphaBetaAgent(5), new AlphaBetaAgent(5));
		//testEvenly("Sandbox", new AlphaBetaAgent(3), new MinimaxAgent(3));
		//testEvenly("Sandbox", new AlphaBetaAgent(5), new MinimaxAgent(4));
		//test("Sandbox", new AlphaBetaAgent(5), new AlphaBetaAgent(5));
		//test("Sandbox", new MinimaxAgent(5), new MinimaxAgent(5));
		//test("Wallawalla", new HumanAgent(), new AlphaBetaAgent(4));
		//test("Sandbox", new AlphaBetaAgent(3), new AlphaBetaAgent(3));
		//testEvenly("Piqua", new AlphaBetaAgent(8), new AlphaBetaAgent(9));
		//testEvenly("Punxsutawney", new MinimaxAgent(6), new AlphaBetaAgent(6));
	}
	
	public static void test(String location, Agent one, Agent two) {
		new GameView(new Game(new Board(location + ".txt"), one, two));
	}
	
	public static void testEvenly(String location, Agent one, Agent two) {
		new GameView(new Game(new Board(location + ".txt"), one, two));
		new GameView(new Game(new Board(location + ".txt"), two, one));
	}
}
