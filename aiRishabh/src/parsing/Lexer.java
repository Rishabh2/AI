package parsing;

public class Lexer {
	
	String[] lexed;
	int position;
	
	public void lex(String input) {
		lexed =  input.split(" ");
		position = 0;
	}
	
	public String getNext(){
		if (hasNext()){
		position++;
		return lexed[position-1];
		}
		return " ";
	}	
	
	public String peekNext() {
		return lexed[position];
		
	}
	
	public boolean hasNext(){
		return position < lexed.length;
	}
}
