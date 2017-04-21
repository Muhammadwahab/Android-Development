package com.example.abdull.json_asignemtn;

/**
 * Created by abdull on 4/15/17.
 */

public class populationData {

    String  Cityname, fcodename,population;

    public populationData() {
    }

    public String getCityname() {
        return Cityname;
    }

    public void setCityname(String cityname) {
        Cityname = cityname;
    }

    public String getFcodename() {
        return fcodename;
    }

    public void setFcodename(String fcodename) {
        this.fcodename = fcodename;
    }

    public String getPopulation() {
        return population;
    }

    public void setPopulation(String population) {
        this.population = population;
    }
}
