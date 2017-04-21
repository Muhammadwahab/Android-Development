package mooc.vandy.java4android.calculator.logic;

/**
 * Perform the Add operation.
 */
public class Add implements ArithmeticInterface {

    //adds to integers and returns the string.
    public String calculate(int first, int second) {
        return "" +(first + second);
    }
}
