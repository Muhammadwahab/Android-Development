package mooc.vandy.java4android.diamonds.logic;

import android.util.Log;
import mooc.vandy.java4android.diamonds.ui.OutputInterface;

/**
 * This is where the logic of this App is centralized for this assignment.
 * <p>
 * The assignments are designed this way to simplify your early
 * Android interactions.  Designing the assignments this way allows
 * you to first learn key 'Java' features without having to beforehand
 * learn the complexities of Android.
 */
public class Logic
       implements LogicInterface {
    /**
     * This is a String to be used in Logging (if/when you decide you
     * need it for debugging).
     */
    public static final String TAG = Logic.class.getName();

    /**
     * This is the variable that stores our OutputInterface instance.
     * <p>
     * This is how we will interact with the User Interface [MainActivity.java].
     * <p>
     * It is called 'out' because it is where we 'out-put' our
     * results. (It is also the 'in-put' from where we get values
     * from, but it only needs 1 name, and 'out' is good enough).
     */
    private OutputInterface mOut;



    /**
     * This is the constructor of this class.
     * <p>
     * It assigns the passed in [MainActivity] instance (which
     * implements [OutputInterface]) to 'out'.
     */
    public Logic(OutputInterface out){
        mOut = out;
    }

    /**
     * This is the method that will (eventually) get called when the
     * on-screen button labeled 'Process...' is pressed.
     */
    public void process(int size) {

        // TODO -- add your code here


      //  mOut.println("daimond asignemnt");
        //mOut.println();
        int isTop=1;
        int count=2;
         int spaceCount=0;
        boolean even=false;
       int lenght=0;
        header(size);

        mOut.println("");
        lenght++;


        for(int i=0;i<size;i++)
        {

            // indentication
            for(int j=i;j<size;j++)     // HEIGHT OF DAIMON
            {
                if(j==i)				// FOR LEVEL ONE
                {
                    mOut.print("|");
                    lenght++; // LEFT WALL
                }
                else
                {
                    mOut.print(" "); //INDENTICATION
                    lenght++;
                }

            }
            // print the stars

            for(int stars=0;stars<=i;stars++)
            {
                if(size==1)
                {
                    mOut.print("<>|");
                    lenght+=3;
                    break;
                }
                else if(stars==0 && size>1 && isTop==1)
                {
                    mOut.print("/\\");
                    lenght+=2;
                    isTop++;
                }
                else if(stars==0&&i==size-1)
                {
                    //mOut.print("< ");
                    if(even)
                    {
                        mOut.print("<-");
                        lenght+=2;
                    }
                    else
                    {
                        mOut.print("<=");
                        lenght+=2;
                    }
                }
                else if(stars==0)
                {
                    if(even)
                    {
                        mOut.print("/-");
                        lenght+=2;
                    }
                    else
                    {
                        mOut.print("/=");
                        lenght+=2;
                    }

                }
                else if(stars==i && i==size-1){
                    if(even)
                    {
                        //mOut.print("/-");
                        mOut.print("->");
                        lenght+=2;
                    }
                    else
                    {
                        //mOut.print("/=");
                        mOut.print("=>");
                        lenght+=2;
                    }

                }
                else if(stars==i){

                    if(even)
                    {
                        mOut.print("-\\");
                        lenght+=2;
                    }
                    else
                    {
                        //mOut.print("/=");
                        mOut.print("=\\");
                        lenght+=2;
                    }
                }
                else{

                    if(even)
                    {
                        mOut.print("--");
                        lenght+=2;
                    }
                    else
                    {
                        //mOut.print("/=");
                        mOut.print("==");
                        lenght+=2;
                    }
                }




                // print right side wall
                if(stars==i)
                { boolean check=false;
                    for(int wall=0;wall<=size-1;wall++)
                    {
                        if(wall==size-1-i)
                        {
                            mOut.print("|");
                            lenght+=1;
                            check=true;
                        }
                        else if(!check){
                            mOut.print(" ");
                            lenght+=1;
                        }
                    }

                }

            }
            mOut.print("\n");
            lenght+=1;
            even=evenOdd(even);


        }

        // mOut.println("Bottom PArt");

        size=size-1;
        if(size!=0)
        {
            mOut.print("|");
            mOut.print(" ");
            lenght+=2;
            spaceCount=2;
        }


        for(int i=0;i<size;i++ )
        {
            //mOut.print("|");
            for(int reversePrint=i;reversePrint<size;reversePrint++)
            {
                //mOut.print("* ");
                // bottom part forward or backlash work
                if(size==1 || i+1==size)
                {
                    mOut.print("\\/");
                    lenght+=2;
                }
                else if(reversePrint==i)
                {
                    if(even)
                    {
                        mOut.print("\\-");
                        lenght+=2;
                    }
                    else
                    {
                        mOut.print("\\=");
                        lenght+=2;
                    }

                }
                else if(reversePrint+1==size)
                {
                    if(even)
                    {
                        mOut.print("-/");
                        lenght+=2;
                    }
                    else
                    {
                        mOut.print("=/");
                        lenght+=2;
                    }
                }
                else
                {
                    if(even)
                    {
                        mOut.print("--");
                        lenght+=2;
                    }
                    else
                    {
                        mOut.print("==");
                        lenght+=2;
                    }

                }

                //  right wall
                int ValueRange=0;
                while (ValueRange<count && reversePrint+1==size) {
                    ValueRange++;

                    if(ValueRange==count)
                    {
                        mOut.print("|");
                        lenght+=1;
                    }
                    else
                    {
                        mOut.print(" ");
                        lenght+=1;
                    }



                }


            }
            count++;
            mOut.print("\n");
            lenght+=1;
            even=evenOdd(even);
            if(i!=size-1)
            {
                mOut.print("|");
                lenght+=1;
            }

            // indenttion
            if(i+1==size)
            {
                break;
            }
            int value=0;
            while (value<spaceCount) {
                if(spaceCount==1)
                {
                    mOut.print(" ");
                    lenght+=1;
                    value++;
                    break;
                }
                else
                {
                    mOut.print(" ");
                    lenght+=1;
                }
                value++;

            }
            spaceCount=spaceCount+1;

            //	for(int j=-1;j<=i;j++)
            //	{
            // indentation
            //if(i==3)
            //{
            //			break;
            //}

            //	mOut.print(" ");
            //	}


        }
        //mOut.println();
        header(size+1);
        // mOut.print("lenght is"+lenght);
        //   mOut.print("Actual is  is"+expected.lenght);



    }
    public  void header(int size)
    {
        for(int i=0;i<=(2*size)+1;i++)
        {
            if(i==0 || i==(2*size)+1)
            {
                mOut.print("+");
            }
            else
            {
                mOut.print("-");
            }

        }

    }
    public boolean evenOdd(boolean even) {
        // TODO Auto-generated method stub
        if(even)
        {
            even=false;
        }
        else
        {
            even=true;
        }
        return even;

    }



}
