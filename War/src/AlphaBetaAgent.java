
public class AlphaBetaAgent implements Agent{
	int depth;
	Board board;
	int me;
	int count;
	
	public AlphaBetaAgent (int depth){
		this.depth = depth;
	}
	
	
	@Override
	public Move getMove() {
		int count = 0;
		long time = System.currentTimeMillis();
		Move m = max(board, depth, new Move(Integer.MIN_VALUE), new Move(Integer.MAX_VALUE));
		System.out.println("Game tree had " + count + "nodes. Took " + (System.currentTimeMillis() - time));
		return m;
	}
	
	public Move max(Board board, int depth, Move alpha, Move beta){
		count++;
		if (depth == 0) return new Move(board.getScoreAdvantage(me));
		Move[] moves = board.getLegalMoves();
		if (moves.length == 0) return new Move(board.getScoreAdvantage(me));
		Move best = new Move(alpha);
		for (Move move : moves){
			Board b = move.move(new Board(board));
				Move next = min(b, depth - 1, best, beta);
				move.setValue(next.getValue());
				if (move.getValue() > best.getValue()) 
					best = move;
				if (move.getValue() >= beta.getValue())
					return move;
			
			
		}
	return best;
	}
	
	public Move min(Board board, int depth, Move alpha, Move beta){
		count++;
		if (depth == 0) return new Move(board.getScoreAdvantage(me));
		Move[] moves = board.getLegalMoves();
		if (moves.length == 0) return new Move(board.getScoreAdvantage(me));
		Move worst = new Move(beta);
		for (Move move : moves){
			Board b = move.move(new Board(board));
				Move next = max(b, depth - 1, alpha, worst);
				move.setValue(next.getValue());
				if (move.getValue() < worst.getValue()) 
					worst = move;
				if (move.getValue() <= alpha.getValue())
					return move;
			
			
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
