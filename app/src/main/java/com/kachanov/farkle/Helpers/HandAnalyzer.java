package com.kachanov.farkle.Helpers;

import android.util.Log;

import java.util.ArrayList;

/**
 * Created by sasha.kachanov on 4/29/2016.
 */
public class HandAnalyzer {
    private static String tag = "HandAnalyzer: ";
    private ArrayList<Die> hand;
    private ArrayList<Integer> handFootprint = new ArrayList<>(6);
    private ArrayList<Combination> availableCombinations = new ArrayList<>();

    public void analyze(ArrayList<Die> hand) {
        this.hand = hand;
        populateAvailableCombinationsArray();
    }

    private void populateAvailableCombinationsArray() {
        //before we populate the combs array we must clear it of the values it had
        clearAvailableCombinationsArray();

        //find out how many ones you have, how many twos, etc
        populateHandFootprint();

//        findCombinations();

        //if you have 6 dice or less do the following tests
        //if you have 5 dice or less do the following tests

//        testFor1DiceCombs();

        printFootprintInLogcat();
    }

    //all that this does is simply populate sortedDices, nothing more. sorted dies contains the footprint of the hand - the number of ones, two, etc
    //an alternative name for this function would be create the footprint for the hand
    public void populateHandFootprint() {
        //the array that tells us how many of each die we have must be cleared out between rolls
        clearHandFootprint();

        //if the dice is used (not unused) add it to the sorted array
        for (int i = 0; i < Game.MAX_NUM_OF_DICE; i++) {
            if (hand.get(i).isUsed()) {
                int valueOfDie = hand.get(i).getValue();
                //retrieve how many of those dice (ones, two,..) we already have
                int newVal = handFootprint.get(valueOfDie - 1);
                //update that value
                newVal++;
                //store the new amount of ones, twos, ... back in the array
                handFootprint.set(valueOfDie, newVal);
            }
        }
    }


    private void clearAvailableCombinationsArray() {
        availableCombinations.clear();
//        for (int i = 0; i < Game.MAX_NUM_OF_COMBINATIONS_AT_A_TIME; i++) {
//            availableCombinations.get(i).clearAllFields();
//        }
    }

    //it sets all the elements of the sortedDice array to zero, must be called between rolls
    private void clearHandFootprint() {
        handFootprint.clear();
//        for (int i = 0; i < Game.MAX_NUM_OF_DICE; i++) {
//            handFootprint.set(i, 0);
//        }
    }

    //    write 6 lines outputting the footprint of the hand
    private void printFootprintInLogcat() {
        //this simply shows the developer what is stored in the sorted dies array.
        for (int i = 0; i <= Game.MAX_NUM_OF_DICE; i++) {
            Log.d(tag, "The number of " + i + 1 + "s in the hand is " + handFootprint.get(i));
        }
    }

    //getters and setters
    public ArrayList<Combination> getAvailableCombinations() {
        return availableCombinations;
    }
}