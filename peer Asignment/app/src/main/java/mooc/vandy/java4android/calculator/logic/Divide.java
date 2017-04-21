package mooc.vandy.java4android.calculator.logic;

/**
 * Perform the Divide operation.
 */
public class Divide implements ArithmeticInterface  {

    /**
     * returns a string showing how many times second fits in first, and the remainder.
     * @param first first integer
     * @param second second integer
     * @return string result
     */
    public String calculate(int first, int second) {
        //do not devide by zero
        if (second == 0) {
            return "ERROR: DIVISION BY ZERO";
        }

        // calculate and return
        String returnString = "";
        returnString += first / second;
        returnString += " R: " + first % second;
        return returnString;
    }
}
