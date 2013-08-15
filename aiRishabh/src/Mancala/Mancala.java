package Mancala;

public class Mancala {
	private static final int SIZE = 4;
	
	int[] pocket; // array of number of gems per pocket
	boolean player = true; // true = player 1, false = player 2
	
	public Mancala(){
		pocket = new int[14];
		reset();
	}
	
	public Mancala(Mancala other){
		pocket = new int[14];
		this.player = other.player;
		for (int i = 0; i < 14; i++)
			this.pocket[i] = other.pocket[i];
	}
	
	public void reset(){
		pocket[0] = 0;
		pocket[7] = 0;
		for (int i = 1; i < 7; i++)
			pocket[i] = SIZE;
		for (int i = 8; i < 14; i++)
			pocket[i] = SIZE;
		
	}
	
	public int count (int p, int index) {
		if (p == 1)
			return pocket [index];
		else
			return pocket [index+7];
		
	}
	
	public void print(){
		System.out.println("_________________________");
		System.out.print("|  |");
		for (int i = 13; i >= 8; i--){
			if (pocket[i] < 10)
				System.out.print(" ");
			System.out.print(pocket[i] + "|");
		}
		System.out.println("  |");
		System.out.print("|");
		if (pocket[0] < 10) System.out.print(" ");
		System.out.print(pocket[0] +"|");
		for (int i=0; i < 17; i++)
			System.out.print("_");
		System.out.print("|");
		if (pocket[7] < 10) System.out.print(" ");
		System.out.println(pocket[7] +"|");
		System.out.print("|  |");
		for (int i = 1; i <= 6; i++){
			if (pocket[i] < 10)
				System.out.print(" ");
			System.out.print(pocket[i] + "|");
		}
		System.out.println("  |");
		System.out.println("_________________________");
			
	}
	
	public boolean makeMove(Move m){
		return makeMove(m.pocket);
	}
	
	public int evaluate(int p) {
		if (p == 1){
			if (pocket[7] > SIZE * 6)
				return Integer.MAX_VALUE;
			if (pocket[0] > SIZE * 6)
				return Integer.MIN_VALUE;
			return pocket[7] - pocket[0];
		}
		else
			if (pocket[0] > SIZE * 6)
				return Integer.MAX_VALUE;
			if (pocket[7] > SIZE * 6)
				return Integer.MIN_VALUE;
		return pocket[0] - pocket[7];
	}
	
	
	public boolean makeMove(int p){
		int i;
		//int current = 0;
		int p2 = p+7;
		if (p == 0) // placeholder
			return false;
		int movesLeft;
		if (player){
			movesLeft = pocket[p];
			pocket[p] = 0;
			for (i = 1; movesLeft > 0; movesLeft--, i++){
				if (p+i >= 14)
					pocket[p+i-13]++;
				else
					pocket[p+i]++;
			}
		}
		else{
			movesLeft = pocket[p2];
			pocket[p2] = 0;
			for (i = 1; movesLeft > 0; movesLeft--, i++){
				if (p2+i >= 14){
					if (p2+i == 21){
						i--;
						movesLeft++;
					}
					else
					pocket[p2+i-14]++;
				}
				else
				pocket[p2+i]++;
				
		}
		}
		
		if (player && (p + i == 7 || p + 1 == 20))
			return true;
		else if (!player && (p2 + i == 7 || p2 + i == 20))
			return true;
		/*else if (pocket[current] == 1){
			pocket[(player) ? 7 : 0] += 1 + pocket[14-current];
			pocket[current] = 0;
			pocket [14-current] = 0;		
}*/
		return false;
		
		
	}
	public void switchPlayer(){
		player = !player;
	}
	
	public Move[] getMoves() {
		int count = 0;
		if (player){
			for (int i = 1; i < 7; i++)
				if (pocket[i] > 0)
					count++;
		}
		else
			for (int i = 8; i < 14; i++)
				if (pocket[i] > 0)
					count++;
		Move[] moves = new Move[count];
		int index = 0;
		if (player) {
			for (int i = 1; i < 7; i++)
				if (pocket[i] > 0) {
					moves[index] = new Move(i);
					index++;
				}
		}
		else {
			for (int i = 8; i < 13; i++)
				if (pocket[i] > 0) {
					moves[index] = new Move(i - 7);
					index++;
				}
		}
		return moves;
	}
	
	
	
	public boolean playing(int p){
		if (p == 1 && player)
			return true;
		if (p == 2 && !player)
			return true;
		return false;
	}
	
}
