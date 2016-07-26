package mooc.vandy.java4android.gate.logic;

import java.util.Random;

import mooc.vandy.java4android.gate.ui.OutputInterface;

/**
 * This class uses your Gate class to fill the corral with snails.  We
 * have supplied you will the code necessary to execute as an app.
 * You must fill in the missing logic below.
 */
public class FillTheCorral {
    /**
     * Reference to the OutputInterface.
     */
    private OutputInterface mOut;

    /**
     * Constructor initializes the field.
     */
    FillTheCorral(OutputInterface out) {
        mOut = out;
    }

    // TODO -- Fill your code in here

    // set state to gates randomly
    public void setCorralGates(Gate[] gates, Random selectDirection){

        //selectDirection.setSeed(1234);

        for (Gate gt:gates){
            int randomDirection = selectDirection.nextInt(3)-1;
            gt.setSwing(randomDirection);
        }

    }

    // check if at least one is open, i.e. swing =1
    public boolean anyCorralAvailable(Gate[] corral){

        for (Gate gt:corral){
            if (gt.getSwingDirection()==1){
                return true;
            }
        }
        return false;
    }

    public int corralSnails(Gate[] corral, Random rand){

        int snailsOnPasture = 5;
        int attempts = 0;

        mOut.println("Initial gate setup:");
        int i=0;
        for (Gate gt:corral){
            mOut.println("Gate "+i+" "+gt.toString());
            i++;
        }

        do {
            // select random number of snails from pasture
            int s = rand.nextInt(snailsOnPasture) + 1;
            // pick a gate
            int corralGate = rand.nextInt(corral.length);
            Gate G = corral[corralGate];
            // try to move s though G
            mOut.println(s+" are trying to move through corral "+corralGate);
            if (G.thru(s) > 0) {
                snailsOnPasture -= s;
            }
            attempts++;
        } while (snailsOnPasture>0);

        mOut.println("It took "+attempts+" attempts to corral all of the snails.");
        return attempts;

    }


}