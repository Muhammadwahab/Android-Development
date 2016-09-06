package mooc.vandy.java4android.buildings.logic;

/**
 * This is the House class file that extends Building.
 */
public class House 
       extends Building {
       
    // TODO - Put your code here.
    private String mOwner;
    private boolean mPool;
    public House(int length,int width,int lotLength,int lotWidth)
    {
        super(length,width,lotLength,lotWidth);

    }
    public House(int length,int width,int lotLength,int lotWidth,String owner)
    {
        this(length,width,lotLength,lotWidth);
        this.mOwner=owner;

    }
    public House(int length,int width,int lotLength,int lotWidth,String owner,boolean pool)
    {
        this(length,width,lotLength,lotWidth,owner);
        this.mPool=pool;

    }

    public String getOwner() {
        return mOwner;
    }
    public boolean hasPool()
    {
        return mPool;
    }

    public void setOwner(String mOwner) {
        this.mOwner = mOwner;
    }

    public void setPool(boolean mPool) {
        this.mPool = mPool;
    }

    @Override
    public String toString() {
        return "Owner: "+getOwner()+";"+" has a big open space";
    }

    @Override
    public boolean equals(Object o) {
        if(o instanceof House)
        {
            House otherHouse=(House)o;
            return hasPool()==otherHouse.hasPool() || calcBuildingArea()==otherHouse.calcBuildingArea();
        }
        return false;
    }
}
