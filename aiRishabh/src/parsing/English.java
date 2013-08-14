package parsing;

import java.util.HashMap;
import java.util.Map;

public class English {
	
	private static English instance = new English();
	private Map <String, String> subject;
	private Map <String, String> verb;
	private Map <String, String> noun;

	private English(){
		initializeSubjects();
		initializeVerbs();
		initializeNouns();
	}
	
	private void initializeNouns() {
		noun = new HashMap <String, String> ();
		noun.put("beach", "location");
		noun.put("store", "location");
		noun.put("dog", "animal");	
	}

	private void initializeVerbs() {
		verb = new HashMap <String, String> ();
		verb.put("went", "past");
		verb.put("go", "present");
		verb.put("going", "gerund");
		
	}

	public static English get(){
		return instance;
	}
	
	public String getSubject(String subj){
		return subject.get(subj);
	}
	
	public String getVerb(String vrb){
		return subject.get(vrb);
	}
	
	public String getNoun(String nou){
		return subject.get(nou);
	}

	private void initializeSubjects() {
		subject = new HashMap <String, String> ();
		subject.put("I", "FP");
		subject.put("you", "SP");
		subject.put("he", "TP");
		subject.put("she", "TP");
		subject.put("it", "TP");
		subject.put("me", "FP");
		subject.put("we", "FP");
		subject.put("us", "FP");
	}
}
