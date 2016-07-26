package mooc.vandy.java4android.gate.logic;

import java.util.Random;

import mooc.vandy.java4android.gate.ui.OutputInterface;

/**
 * This class uses your Gate class to manage a herd of snails.  We
 * have supplied you will the code necessary to execute as an app.
 * You must fill in the missing logic below.
 */
public class HerdManager {
    /**
     * Reference to the output.
     */
    private OutputInterface mOut;

    /**
     * The input Gate object.
     */
    private Gate mEastGate;

    /**
     * The output Gate object.
     */
    private Gate mWestGate;

    /**
     * Maximum number of iterations to run the simulation.
     */
    private static final int MAX_ITERATIONS = 10;

    /**
     * Constructor initializes the fields.
     */
    public HerdManager(OutputInterface out,
                       Gate westGate,
                       Gate eastGate) {
        mOut = out;

        mWestGate = westGate;
        mWestGate.open(Gate.IN);

        mEastGate = eastGate;
        mEastGate.open(Gate.OUT);
    }

    /**
     * Number of stock in the herd.
     */
    public static final int HERD = 24;

    /**
     * Simulated movement of a herd through two gates.
     *
     * Random rand: Random object for gate and number of snails
     */
    public void simulateHerd(Random rand)
    {
        int pen = HERD, pasture = 0;    // Where the herd are

        mOut.println("There are currently " + pen +
                " snails in the pen and " + pasture +
                " snails in the pasture");
        // Simulate for ten cycles
        for (int i = 0; i < 10; i++) {
            int snailsOnTheMove;

            if (pasture == 0 || rand.nextInt(2) == 1)
                snailsOnTheMove = mEastGate.thru(rand.nextInt(pen)+1);
            else
                snailsOnTheMove = mWestGate.thru(rand.nextInt(pasture)+1);

            pen += snailsOnTheMove;
            pasture -= snailsOnTheMove;
            mOut.println("There are currently " + pen +
                    " snails in the pen and " + pasture +
                    " snails in the pasture");
        }
    }
}