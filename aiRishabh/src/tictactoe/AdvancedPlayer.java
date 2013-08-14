package aiRishabh;

public class AdvancedPlayer implements TicTacToePlayer {

	private char get(TicTacToeGame game, int x, int y){
		return game.getPieceAt(x,y);
		
	}
	
	@Override
	public Move getMove(TicTacToeGame g) {
		for (int x=0; x<3; x++){
			if (get(g, x, 0) != ' ' && get(g,x,0) == get(g,x,1) && get(g,x,2) == ' ')
				return new Move (x,2);
			if (get(g, x, 0) != ' ' && get(g,x,0) == get(g,x,2) && get(g,x,1) == ' ')
				return new Move (x,1);
			if (get(g, x, 1) != ' ' && get(g,x,1) == get(g,x,2) && get(g,x,0) == ' ')
				return new Move (x,0);
				
		}
		for (int y=0; y<3; y++){
			if (get(g, 0, y) != ' ' && get(g,0,y) == get(g,1,y) && get(g,2,y) == ' ')
				return new Move (2,y);
			if (get(g, 0, y) != ' ' && get(g,0,y) == get(g,2,y) && get(g,1,y) == ' ')
				return new Move (1,y);
			if (get(g, 1,y) != ' ' && get(g,1,y) == get(g,2,y) && get(g,0,y) == ' ')
				return new Move (0,y);
		}
			
			if (get(g, 0, 0) != ' ' && get(g, 0, 0) == get(g, 1, 1) && get(g, 2, 2) == ' ')
				return new Move (2, 2);
			if (get(g, 0, 0) != ' ' && get(g, 0, 0) == get(g, 2, 2) && get(g, 1, 1) == ' ')
				return new Move (1, 1);
			if (get(g, 1, 1) != ' ' && get(g, 1, 1) == get(g, 2, 2) && get(g, 0, 0) == ' ')
				return new Move (0, 0);
			if (get(g, 2, 0) != ' ' && get(g, 2, 0) == get(g, 1, 1) && get(g, 0, 2) == ' ')
				return new Move (0, 2);
			if (get(g, 2, 0) != ' ' && get(g, 2, 0) == get(g, 0, 2) && get(g, 1, 1) == ' ')
				return new Move (1, 1);
			if (get(g, 1, 1) != ' ' && get(g, 1, 1) == get(g, 0, 2) && get(g, 2, 0) == ' ')
				return new Move (2, 0);
				
		
		for (int x=0; x<3; x++)
			for (int y=0; y<3; y++)
				if (g.canPlacePieceAt(x, y))
					return new Move(x,y);
		return null;
	}
	
				

}
