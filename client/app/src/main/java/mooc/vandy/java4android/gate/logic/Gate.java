package mooc.vandy.java4android.gate.logic;

/**
 * This file defines the Gate class.
 */
public class Gate {
    // TODO -- Fill in your code here
    private int mSwing;
    public  static final int OUT=-1;
    public  static final int IN=1;
    public  static final int CLOSED=0;

    // Constructor to initilaze gate inital point
    public Gate()
    {
     setSwing(CLOSED);
    }

    public boolean setSwing(int direction)
    {
        if(direction==OUT || direction==IN || direction==CLOSED)
        {
            this.mSwing=direction;
            return true;
        }
        return false;

    }
    public boolean open(int direction)
    {
        if(direction==OUT || direction==IN || direction!=CLOSED)
        {
            setSwing(direction);
            return true;
        }
        return false;

    }
    public void close()
    {
        setSwing(this.CLOSED);
    }
    public int thru(int count)
    {
        if(getSwingDirection()==IN)
        {
            return count;
        }
        else if(getSwingDirection()==OUT) {

            return getNegCount(count);
        }
        else
        {
            return 0;
        }

    }

    private int getNegCount(int count) {
        int negCount=0;
        while (count>0)
        {
            count--;
            negCount--;
        }
        return negCount;
    }

    public int getSwingDirection()
    {
        return this.mSwing;
    }
    @Override
    public String toString()
    {
        if(this.getSwingDirection()==Gate.CLOSED)
        {
            return "This gate is closed";
        }
         if(this.getSwingDirection()==Gate.IN)
        {
            return "This gate is open and swings to enter the pen only";
        }
         if(this.getSwingDirection()==Gate.OUT)
        {
            return "This gate is open and swings to exit the pen only";
        }
        else
        {
            return "This gate has an invalid swing direction";
        }


    }

}
