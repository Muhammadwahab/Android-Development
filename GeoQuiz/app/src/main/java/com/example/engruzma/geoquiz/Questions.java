package com.example.engruzma.geoquiz;

/**
 * Created by Engr.Uzma on 16/09/2016.
 */
public class Questions {

    int TextID;
    boolean ans;
    public Questions(int TextID,boolean ans)
    {
        this.TextID=TextID;
        this.ans=ans;
    }

    public boolean isAns() {
        return ans;
    }

    public int getTextID() {
        return TextID;
    }

}