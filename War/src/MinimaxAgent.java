
public class MinimaxAgent implements Agent{
	int depth;
	Board board;
	int me;
	int count;
	
	public MinimaxAgent (int depth){
		this.depth = depth;
	}
	
	
	@Override
	public Move getMove() {
		int count = 0;
		long time = System.currentTimeMillis();
		Move m = max(board, depth);
		System.out.println("Game tree had " + count + " nodes. Took " + (System.currentTimeMillis() - time));
		return m;
	}
	
	public Move max(Board board, int depth){
		count++;
		if (depth == 0) return new Move(board.getScoreAdvantage(me));
		Move[] moves = board.getLegalMoves();
		if (moves.length == 0) return new Move(board.getScoreAdvantage(me));
		Move best = moves[0];
		for (Move move : moves){
			Move next = min(move.move(board), depth - 1);
			board.unmove(1);
				move.setValue(next.getValue());
				if (move.getValue() > best.getValue()) 
					best = move;
			
			
		}
	return best;
	}
	
	public Move min(Board board, int depth){
		count++;
		if (depth == 0) return new Move(board.getScoreAdvantage(me));
		Move[] moves = board.getLegalMoves();
		if (moves.length == 0) return new Move(board.getScoreAdvantage(me));
		Move worst = moves[0];
		for (Move move : moves){
			Move next = max(move.move(board), depth - 1);
			board.unmove(1);
				move.setValue(next.getValue());
				if (move.getValue() < worst.getValue()) 
					worst = move;
			
			
		}
	return worst;
	}


	@Override
	public void setID(int id) {
		me = id;
		
	}

	@Override
	public int getID() {
		return me;
	}

	@Override
	public void setBoard(Board board) {
		this.board = board;
		
	}

	@Override
	public int getScore() {
		return board.getScore(me);
	}

	@Override
	public String getName() {
		return "Minimax Agent";
	}

	@Override
	public String getProperty() {
		return ("Depth" + depth);
	}

}
