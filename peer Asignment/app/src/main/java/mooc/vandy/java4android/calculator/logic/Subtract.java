package mooc.vandy.java4android.calculator.logic;

/**
 * Perform the Subtract operation.
 */
public class Subtract implements ArithmeticInterface  {

    /**
     * subtract the first integer from the second.
     * @param first first integer
     * @param second second integer
     * @return a string with the result
     */
    public String calculate(int first, int second) {
        return "" + (first - second);
    }
}
