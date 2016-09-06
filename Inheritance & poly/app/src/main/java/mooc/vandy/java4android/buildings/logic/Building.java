package mooc.vandy.java4android.buildings.logic;

/**
 * This is the Building class file.
 */
public class Building {
    
    // TODO - Put your code here.
    private int mLength;
    private int mWidth;
    private int mLotLength;
    private int mLotWidth;

    public Building(int length,int width,int lotLength,int lotWidt)
    {
        this.mLength=length;
        this.mWidth=width;
        this.mLotLength=lotLength;
        this.mLotWidth=lotWidt;

    }

    public int getLength() {
        return mLength;
    }

    public void setLength(int mLength) {
        this.mLength = mLength;
    }

    public int getWidth() {
        return mWidth;
    }

    public void setWidth(int mWidth) {
        this.mWidth = mWidth;
    }

    public int getLotLength() {
        return mLotLength;
    }

    public void setLotLength(int mLotLength) {
        this.mLotLength = mLotLength;
    }

    public int getLotWidth() {
        return mLotWidth;
    }

    public void setLotWidth(int mLotWidth) {
        this.mLotWidth = mLotWidth;
    }

    @Override
    public String toString() {
        return super.toString();
    }
    public int calcBuildingArea()
    {
        return getLength()*getWidth();
    }
    public int calcLotArea()
    {
        return getLotLength()*getLotWidth();
    }
}
