package mooc.vandy.java4android.calculator.logic;

/**
 * Perform the Multiply operation.
 */
public class Multiply implements ArithmeticInterface  {

    /**
     * returns the result of integer multiplication.
     * @param first first integer
     * @param second second integer
     * @return string of the result
     */
    public String calculate(int first, int second) {
        return "" + (first * second);
    }
}
