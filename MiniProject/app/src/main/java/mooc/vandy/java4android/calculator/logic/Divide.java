package mooc.vandy.java4android.calculator.logic;

/**
 * Perform the Divide operation.
 */
public class Divide {
    // TODO -- start your code

    private int operandOne,operandTwo;

    // initialize values using constructor

    public Divide(int operandOne,int operandTwo)
    {
        this.operandOne=operandOne;

        this.operandTwo=operandTwo;

    }



    // getter method of operand one

    public int getOperandOne() {
        return operandOne;
    }

    // getter method of operand two
    public int getOperandTwo() {
        return operandTwo;
    }

    public String calculation()
    {
        // initialized variables

        int remainder=0,quotient=0;
        try{
                // when remainder is zero
                if(getOperandOne()%getOperandTwo()==0)
                {
                    quotient=getOperandOne()/getOperandTwo();
                    remainder=0;

                }
            else
                {
                    //when remainder is greater then zero

                    quotient=getOperandOne()/getOperandTwo();

                    remainder=getOperandOne()%getOperandTwo();
                }


        }catch (Exception e) // catch warning
        {
            // when user enter zero as a second value the application crash and give warning

            return "warning of division by 0";
        }
        // return quotient and remainder as a String

        return quotient+" R: "+remainder;

    }

}
