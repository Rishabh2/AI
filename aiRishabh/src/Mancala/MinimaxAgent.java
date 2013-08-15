package Mancala;

public class MinimaxAgent implements Agent {
	int depth;
	int me;
	
	public MinimaxAgent(int me, int depth){
		this.depth = depth;
		this.me = me;
	}
	
	@Override
	public Move getMove(Mancala board) {
		return max(board, depth);
	}
	
	public Move max(Mancala board, int depth) {
		Move[] moves = board.getMoves();
			if (depth == 0 || moves.length == 0) {
				Move foo = new Move(0);
				foo.value = board.evaluate(me);
				return foo;
			}
		for (Move move : moves){
			Mancala b = new Mancala (board);
			if (b.makeMove(move)){
				Move continuation = max(b, depth);
				move.value = continuation.value;
			}
			else{
				int value = b.evaluate(me);
				if (value == Integer.MAX_VALUE || value == Integer.MIN_VALUE){
					move.value = value;
				}
				else{
					b.switchPlayer();
				Move next = min(b, depth - 1);
				move.value = next.value;
				}
			}
		}
		Move best = moves[0];
		best.value = Integer.MIN_VALUE;
		for (int i = 1; i < moves.length; i++)
			if (moves[i].value > best.value)
				best = moves[i];
		return best;
	}
	
	public Move min(Mancala board, int depth) {
		Move[] moves = board.getMoves();
		if (depth == 0 || moves.length == 0) {
			Move foo = new Move(0);
			foo.value = board.evaluate(me);
			return foo;
		}
	for (Move move : moves){
		Mancala b = new Mancala (board);
		if (b.makeMove(move)){
			Move continuation = min(b, depth);
			move.value = continuation.value;
		}
		else{
			int value = b.evaluate(me);
			if (value == Integer.MAX_VALUE || value == Integer.MIN_VALUE){
				move.value = value;
			}
			else{
				b.switchPlayer();
			Move next = max(b, depth - 1);
			move.value = next.value;
			}
		}
	}
	Move worst = moves[0];
	worst.value = Integer.MAX_VALUE;
	for (int i = 1; i < moves.length; i++)
		if (moves[i].value < worst.value)
			worst = moves[i];
	return worst;
	}
	
	

}
