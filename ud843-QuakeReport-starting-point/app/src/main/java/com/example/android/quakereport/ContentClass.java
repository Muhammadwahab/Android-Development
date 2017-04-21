package com.example.android.quakereport;

/**
 * Created by Engr.Uzma on 23/08/2016.
 */
public class ContentClass {

    private double Magnitude;
    private String Placed;
    private long timeInMilliSecond;
    private String url;

    public ContentClass(double Magnitude,String Placed,long timeInMilliSecond,String url)
    {
        this.Magnitude=Magnitude;
        this.Placed=Placed;
        this.timeInMilliSecond=timeInMilliSecond;
        this.url=url;
    }

    public String getUrl() {
        return url;
    }

    public double getMagnitude() {
        return Magnitude;
    }

    public String getPlaced() {
        return Placed;
    }

    public long gettimeInMilliSecond() {
        return timeInMilliSecond;
    }
}
