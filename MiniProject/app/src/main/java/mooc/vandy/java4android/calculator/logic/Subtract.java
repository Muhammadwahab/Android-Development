package mooc.vandy.java4android.calculator.logic;

/**
 * Perform the Subtract operation.
 */
public class Subtract {
    // TODO -- start your code here
    // variable to initilze value
    private int operandOne,operandTwo;

    // initialize values using constructor

    public Subtract(int operandOne,int operandTwo)
    {

        this.operandOne=operandOne;
        this.operandTwo=operandTwo;

    }

    // getter method of operand one

    public int getOperandOne()
    {

        return operandOne;
    }

    // getter method of operand two

    public int getOperandTwo() {

        return operandTwo;
    }

    // Actual process to obtained certain result
    public String calculation()
    {
        // String.value used to convert integer to String
        return String.valueOf(getOperandOne()-getOperandTwo());

    }
}
