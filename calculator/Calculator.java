
/**
 * Calculator is a simple calculator written in Java.
 * 
 * @author (your name)
 */
public class Calculator
{
    /**
     * Returns whatever input you give to it.
     */
    public int returnMe(int num)
    {
        return num;
    }
	
	/**
	 * Returns the input you give it, plus one.
	 */
	public int addOne(int num)
	{
		return num + 1;
	}
    
    /**
     * Returns the sum of the two inputs.
     */
    public int add(int x, int y)
    {
        int sum;	// Create a space in memory named sum
		sum = 0;	// Assign 0 to that place in memory
        return sum;	// Return the value in that named place in memory
    }
    
    /**
     * Returns the difference between the two inputs.
     */
    public int subtract(int x, int y)
    {
        int difference = 0;
        return difference;
    }
    
    /**
     * Returns the product of the two inputs.
     */
    public int multiply(int x, int y)
    {
        // Your code here
        return 0;
    }
    
    /**
     * Returns the first input divided by the second input.
     */
    public int divide(int x, int y)
    {
        // Your code here
        return 0;
    }
    
    /**
     * Returns the value of the greatest of the two inputs.
     */
    public int whichIsGreater(int x, int y)
    {
        return 0;
    }
    
    /**
     * Returns the value of the greatest of the three inputs.
     */
    public int whichIsGreater(int x, int y, int z)
    {
		return 0;
    }

	/**
	 *	FORESHADOWING
	 *	Saving data into this calculator (i.e. saving data in a class)
	 */
	
	int saved;
	
	public void save(int number) {
		saved = number;
	}
	
	public int retrieve() {
		return saved;
	}
}
