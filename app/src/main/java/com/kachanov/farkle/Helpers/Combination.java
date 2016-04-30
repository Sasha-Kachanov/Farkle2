package com.kachanov.farkle.Helpers;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Mac on 3/9/16.
 */
public class Combination {
    public String name;
    public int points;
    public ArrayList<Integer> footprint = new ArrayList<>((Arrays.asList(new Integer(0), new Integer(0), new Integer(0), new Integer(0), new Integer(0), new Integer(0))));
    public Integer nullInteger = new Integer(-10);

    public Combination() {
        clearAllFields();
    }

    public Combination(String name, int points, ArrayList<Integer> footprint) {
        this.name = name;
        this.points = points;
        this.footprint = footprint;
    }

    //i need this method to reinitialize the combs array that holds availble combinations
    public void clearAllFields() {

        for (int i = 0; i < 6; i++) {
            this.footprint.set(i, nullInteger);
        }
        this.name = "empty";
        this.points = -1;
    }
}
