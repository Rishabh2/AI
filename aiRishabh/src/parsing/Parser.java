package parsing;

public class Parser {
	
	Lexer lexer;
	
	public Parser(){
		lexer = new Lexer();
	}
	
	public Sentence parse(String input){
		lexer.lex(input);
		return parseSentence();
	}
	
	public Sentence parseSentence(){
		Subject subject = parseSubject();
		Verb verb = parseVerb();
		Predicate predicate = parsePredicate();
		return null;
	}
	
	private Predicate parsePredicate() {
		// TODO Auto-generated method stub
		return null;
	}

	public Subject parseSubject(){
		String subject = English.get().getSubject( lexer.peekNext() );
		if (subject != null) {
			System.out.println("Subject [" + lexer.getNext() + "]");
		}
		return null;
	}
	
	public Noun parseNoun(){
		String noun = English.get().getNoun( lexer.peekNext() );
		if (noun != null) {
			System.out.println("Noun [" + lexer.getNext() + "]");
		}
		return null;
	}
	public Verb parseVerb(){
		String verb = English.get().getVerb( lexer.peekNext() );
		if (verb != null) {
			System.out.println("Verb [" + lexer.getNext() + "]");			
		}
		return null;
	}
	
}
	
