package mooc.vandy.java4android.calculator.logic;

import mooc.vandy.java4android.calculator.logic.Add;
import mooc.vandy.java4android.calculator.logic.Divide;
import mooc.vandy.java4android.calculator.logic.Multiply;
import mooc.vandy.java4android.calculator.logic.Subtract;
import mooc.vandy.java4android.calculator.ui.ActivityInterface;

/**
 * Performs an operation selected by the user.
 */
public class Logic 
       implements LogicInterface,printString {
    /**
     * Reference to the Activity output.
     */
    public Add add;
    public Subtract subract;
    public Multiply multiply;
    public Divide divide;
    protected ActivityInterface mOut;
    printString printInterface;

    /**
     * Constructor initializes the field.
     */
    public Logic(ActivityInterface out){
        mOut = out;
    }

    /**
     * Perform the @a operation on @a argumentOne and @a argumentTwo.
     */

    public void process(int argumentOne,
                        int argumentTwo,
                        int operation){
        // TODO -- start your code here

        if(operation==1)
        {
            add=new Add(argumentOne,argumentTwo);
            print(add.calculation());


        }
        else if(operation==2)
        {
            subract=new Subtract(argumentOne,argumentTwo);

            //interface for print output
            print(subract.calculation());
        }
        else if(operation==3)
        {
            multiply=new Multiply(argumentOne,argumentTwo);

            //interface for print output
            print(multiply.calculation());
        }
        else if(operation==4)
        {
           divide=new Divide(argumentOne,argumentTwo);

            //interface for print output
            print(divide.calculation());
        }
        else
        {
            // when any other operation then *,/,-.+
            mOut.print("Invalid Operation");
        }
    }

    @Override
    // interface method
    public void print(String calculation) {
        mOut.print(calculation);
    }
}
