package mooc.vandy.java4android.buildings.logic;

/**
 * This is the office class file, it is a subclass of Building.
 */
public class Office 
       extends Building {
       
    // TODO - Put your code here.
    private String mBusinessName;
    private  int mParkingSpaces;
    private static int sTotalOffices=0;

    public Office(int length,int width,int lotLength, int lotWidth)
    {
        super(length,width,lotLength,lotWidth);
        sTotalOffices++;

    }
    public Office(int length,int width,int lotLength, int lotWidth,String businessName)
    {
        this(length,width,lotLength,lotWidth);
        this.mBusinessName=businessName;

    }
    public Office(int length,int width,int lotLength,int lotWidth,String businessName,int parkingSpaces)
    {
        this(length,width,lotLength,lotWidth,businessName);
        this.mParkingSpaces=parkingSpaces;
    }

    public int getParkingSpaces() {
        return mParkingSpaces;
    }

    public void setParkingSpaces(int mParkingSpaces) {
        this.mParkingSpaces = mParkingSpaces;
    }

    public String getBusinessName() {
        return mBusinessName;
    }

    public void setBusinessName(String mBusinessName) {
        this.mBusinessName = mBusinessName;
    }

    @Override
    public String toString() {
        return "Business: "+"unoccupied"+" (total offices: "+sTotalOffices+")";
    }

    @Override
    public boolean equals(Object o) {
        if(o instanceof Office)
        {
            Office otherHouse=(Office)o;
            return calcBuildingArea()==otherHouse.calcBuildingArea() && getParkingSpaces()==otherHouse.getParkingSpaces();
        }
        return false;
    }
}
