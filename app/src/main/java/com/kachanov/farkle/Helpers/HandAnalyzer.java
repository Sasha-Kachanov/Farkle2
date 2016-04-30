package com.kachanov.farkle.Helpers;

import java.util.ArrayList;

/**
 * Created by sasha.kachanov on 4/29/2016.
 */
public class HandAnalyzer {
    private Footprint footprintOfTheCurrentHand = new Footprint();
    private CombinationFinder combFinder = new CombinationFinder();

    public void analyze(ArrayList<Die> hand) {
        footprintOfTheCurrentHand.generateFootprint(hand);
        combFinder.findAvailableCombinations(footprintOfTheCurrentHand);
    }

    private static String tag = "HandAnalyzer: ";

    //getters
    public CombinationFinder getCombFinder() {
        return combFinder;
    }

    public Footprint getFootprintOfTheCurrentHand() {
        return footprintOfTheCurrentHand;
    }
}