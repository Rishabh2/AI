package aiRishabh;

public class BasicPlayer implements TicTacToePlayer {

	@Override
	public Move getMove(TicTacToeGame game) {
		for (int x=0; x<3; x++)
			for (int y=0; y<3; y++)
				if (game.canPlacePieceAt(x, y))
					return new Move(x,y);
		return null;
					
	}

}
