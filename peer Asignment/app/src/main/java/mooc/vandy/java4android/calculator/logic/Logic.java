package mooc.vandy.java4android.calculator.logic;

import java.util.ArrayList;
import java.util.List;

import mooc.vandy.java4android.calculator.logic.Add;
import mooc.vandy.java4android.calculator.logic.Divide;
import mooc.vandy.java4android.calculator.logic.Multiply;
import mooc.vandy.java4android.calculator.logic.Subtract;
import mooc.vandy.java4android.calculator.ui.ActivityInterface;



/**
 * Performs an operation selected by the user.
 */
public class Logic 
       implements LogicInterface {

    static final int ADDITION = 1;
    static final int SUBTRACTION = 2;
    static final int MULTIPLICATION = 3;
    static final int DIVISION = 4;

    /**
     * Reference to the Activity output.
     */
    protected ActivityInterface mOut;

    /**
     * Constructor initializes the field.
     */
    public Logic(ActivityInterface out){
        mOut = out;
    }

    /**
     * Perform the @a operation on @a argumentOne and @a argumentTwo.
     */
    public void process(int argumentOne, int argumentTwo, int operation){

        //make an araylist with all the calculation options. the first is double because we start
        //counting from 1
        List<ArithmeticInterface> calculators = new ArrayList<>();
        calculators.add(new Add());
        calculators.add(new Add());
        calculators.add(new Subtract());
        calculators.add(new Multiply());
        calculators.add(new Divide());

        //get calculator from operation integer and call return the right calculation.
        mOut.print(calculators.get(operation).calculate(argumentOne,argumentTwo));
    }
}
