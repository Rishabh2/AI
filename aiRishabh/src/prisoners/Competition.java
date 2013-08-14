package prisoners;

public class Competition {
	
	Agent one, two;
	int oneScore = 0, twoScore = 0;
	int oneWins = 0, twoWins = 0, draws = 0;
	
	public static void main(String[] args){
		Competition c = new Competition(new RandomAgent(), new SimpleAgent());
		c.iterate(1000, 10000);
		
	}

	public Competition(Agent one, Agent two){
		this.one = one;
		this.two = two;
	}
	public void iterate(int times, int rounds){
		for (int i=0; i < rounds; i++)
			iterate(times);
		System.out.println(" One " + oneWins+ " Two " + twoWins + " Draws " + draws);
	}
	
	public void iterate(int times){
		for (int i=0; i < times; i++)
			iterate();
		if (oneScore < twoScore)
			oneWins++;
		else if(twoScore < oneScore)
			twoWins++;
		else
			draws++;
		oneScore = 0;
		twoScore = 0;
		one.reset();
		two.reset();
	}
	
	public void iterate(){
		boolean oneCooperates = one.cooperate();
		boolean twoCooperates = two.cooperate();
		one.remember(twoCooperates);
		two.remember(oneCooperates);
		
		if (oneCooperates && twoCooperates){
			oneScore +=1;
			twoScore +=1;
		}
		if (oneCooperates && !twoCooperates){
			oneScore +=10;
		}
		if (!oneCooperates && twoCooperates){
			twoScore += 10;
		}
		if (!oneCooperates && !twoCooperates){
			oneScore +=5;
			twoScore +=5;
		}
		
	}
}

