
/**
 * LoopFun is a collection of fun loop exercises.
 * @author (your name)
 */
public class LoopFun
{
    /**
     * Print the numbers from 1 to 20.
     */
    public void looperScooper()
    {
        int x = 1;
        while (x <= 20)
        {
            System.out.println(x);
            x = x + 1;
        }
    }
    
    /**
     * Use a for loop this time!
     */
    public void looperScooper2()
    {
        for (int x = 1 ; x <= 20 ; x++) {
            System.out.println(x);
        }
    }
    
    /**
     * Print the multiples of 3 between 0 and 100.
     */
    public void multiplesOf3()
    {
        
    }
    
    /**
     * Check this out!
     */
    public int remainderAfterDividingByTwo(int number)
    {
        return number % 2;
    }
    
    public int remainderAfterDividingByThree(int number)
    {
        return number % 3;
    }
    
    public int remainderAfterDividing(int number, int by)
    {
        return number % by;
    }
    
    /**
     * Sum the multiples of 3 between 0 and 100.
     */
    public int sumMultiplesOfThree() {
        int sum = 0;
        
        return sum;
    }
    
    /**
     * Sum all of the multiples of either 3, 5, or both, between 0
     * and 1000.
     */
    public int eulerProblemOne() {
        int sum = 0;
        
        return sum;
    }

    /**
     * Prints the multiples of a number n, up to a limit number.
     */
    public void multiples(int n, int limit)
    {
        int test = 1;
        // 
        while (test < limit)
        {
            // if the remainder of test / n is 0, then it is a multiple of n
            if (test % n == 0)
            {
                System.out.println(test);
            }
        }
    }
    
    /**
     * An example of an infinite loop.
     */
    public void dontRunMe()
    {
        int x = 1;
        while (x < 5) {
            System.out.println("I thought I made it clear that you shouldn't run me.");
        }
    }
    
    /**
     * Returns true if the input number is a prime number.
     */
    public boolean isPrime(int number)
    {
        return false;
    }
    
    /**
     * Prints all the prime numbers between 1 and limit, using the isPrime method from above.
     */
    public void printPrimeNumbers(int limit)
    {
        for (int test = 1 ; test < limit ; test++) {
            if (isPrime(test))
                System.out.println(test);
        }
    }
}
