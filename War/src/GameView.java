/**
 * GameView sets up a view of a game of war, and manages the associated BoardView.
 * @author Keshav Saharia
 */
public class GameView {
	private Game game;		// A reference to the Game instance that is running this game
	private BoardView view;	// A private reference to the BoardView that this GameView is handling
	
	/**
	 * Constructs a new GameView, which is a GUI to interact with the given game.
	 */
	public GameView(Game game) {
		this.game = game;
		view = new BoardView(game.getBoard());
		view.setAgents(game.getAgent(Game.PLAYER1), game.getAgent(Game.PLAYER2));
		play();
	}
	
	/**
	 * Plays a turn of the game and refreshes the BoardView.
	 */
	private void play() {
		while (!game.getBoard().gameOver()) {
			game.playTurn();
			view.refresh();
		}
	}
}
