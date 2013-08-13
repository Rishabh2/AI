 
/**
 * Write a description of class PigLatin here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class PigLatin
{
    public String convert(String input)
    {
        // StringBuilder is a way to build strings easily.
        StringBuilder pigLatin = new StringBuilder();
        // this splits the input string into an array of words.
        String[] words = input.trim().split(" ");
        // this is a nice and clean way to use a for loop to iterate through an array
        for (String word : words) {
            // appends the output of convertWord(String input) to our StringBuilder object.
            pigLatin.append( convertWord(word) );
            pigLatin.append(" ");
        }
        return pigLatin.toString();
    }
    
    private String convertWord(String input)
    {
        return input;
    }
    
    private boolean isVowel(char letter)
    {
        return false;
    }
}
