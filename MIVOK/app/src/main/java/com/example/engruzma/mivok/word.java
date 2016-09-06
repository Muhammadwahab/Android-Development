package com.example.engruzma.mivok;

/**
 * Created by Engr.Uzma on 08/08/2016.
 */
public class word {

    private String defualtTranslation;
    private String MivokTranslation;
    private int imageID=NO_IMAGE_ID;
    private static final int NO_IMAGE_ID=-1;

    public  word(String defualtTranslation, String MivokTranslation)
    {
        this.defualtTranslation=defualtTranslation;
        this.MivokTranslation=MivokTranslation;

    }
    public  word(String defualtTranslation, String MivokTranslation, int imageID)
    {
        this.defualtTranslation=defualtTranslation;
        this.MivokTranslation=MivokTranslation;
        this.imageID=imageID;

    }

    public String getDefualtTranslation() {
        return defualtTranslation;
    }

    public String getMivokTranslation() {
        return MivokTranslation;
    }

    public int getImageID() {
        return imageID;
    }

    public boolean hasImage()
    {
        return getImageID()!=NO_IMAGE_ID;
    }
}
