
/**
 * Calculate uses the calculator class to demonstrate how to use methods of another class.
 * @author (your name)
 */
public class Calculate
{
    public static void main(String[] args)
    {
        // Call the onePlusOne method
        onePlusOne();
        // make a new calculator
        Calculator calc = new Calculator();
        
        // Your code here
    }
    
    /**
     * This provided method shows you what 1 + 1 is.
     */
    public static void onePlusOne()
    {
        // make a new calculator object
        Calculator calc = new Calculator();
        // set the variable sum to the output of the add method from the Calculator class.
        int sum = calc.add(1, 1);
        // print out the result
        System.out.println("1 + 1 = " + sum);
    }
    
    /**
     * A sample static method.
     */
    public static void sampleMethod()
    {
        
    }
}
