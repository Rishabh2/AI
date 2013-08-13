
/**
 * CollatzConjecture tests Lothar Collatz's 3n + 1 conjecture.
 * @author (your name) 
 */
public class CollatzConjecture
{
    public boolean collatz(int number)
    {
        // Before we do anything, print out the number
        System.out.println(number);
        // Test the number to see if it reaches 1 by the Collatz 3n + 1 method.
        if (number == 1) {
            return true;
        }
        else {
            // Your code here!
        }
        return false;
    }
}
