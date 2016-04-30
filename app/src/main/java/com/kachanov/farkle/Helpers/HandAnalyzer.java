package com.kachanov.farkle.Helpers;

import android.util.Log;

import com.kachanov.farkle.MainActivity;

import java.util.ArrayList;

/**
 * Created by sasha.kachanov on 4/29/2016.
 */
public class HandAnalyzer {
    private Footprint footprint = new Footprint();
    private CombinationFinder combFinder = new CombinationFinder();

    public void analyze(ArrayList<Die> hand) {
        footprint.generateFootprint(hand);
        combFinder.findAvailableCombinations(footprint);
    }

    private static String tag = "HandAnalyzer: ";

    //getters
    public CombinationFinder getCombFinder() {
        return combFinder;
    }
}