package com.kachanov.farkle.Helpers;

import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by sasha.kachanov on 4/30/2016.
 */
public class Footprint {
    private static String tag = "Footprint: ";
    private ArrayList<Integer> footprint = new ArrayList<>(Arrays.asList(new Integer(0), new Integer
            (0), new Integer(0), new Integer(0), new Integer(0), new Integer(0)));

    public void generateFootprint(ArrayList<Die> hand) {
//        clearFootprint();

        //if the dice is used (not unused) add it to the footprint
        for (int i = 0; i < Game.MAX_NUM_OF_DICE; i++) {
            if (hand.get(i).isUsed()) {
                int valueOfDie = hand.get(i).getValue();
                //retrieve how many of those dice (ones, two,..) we already have
                int newVal = footprint.get(valueOfDie - 1);
                //update that value
                newVal++;
                //store the new amount of ones, twos, ... back in the array
                footprint.set(valueOfDie - 1, newVal);
            }
        }
        printFootprintInLogcat();
    }

    //it sets all the elements of the sortedDice array to zero, must be called between rolls
    //note that it does not work as clear() defined for ArrayLists; we are not destorying the
    // objects here
    private void clearFootprint() {
        for (int i = 0; i < Game.MAX_NUM_OF_DICE; i++) {
            footprint.set(i, 0);
        }
    }

    public int getNumOfDice() {
        int numOfDiceInTheFootprint = 0;
        for (int i = 0; i < Game.MAX_NUM_OF_DICE; i++) {
            numOfDiceInTheFootprint += footprint.get(i);
        }
        Log.d(tag, "The number of dice in this footprint is " + numOfDiceInTheFootprint);
        return numOfDiceInTheFootprint;
    }


    //    write 6 lines outputting the footprint of the hand
    private void printFootprintInLogcat() {
        //this simply shows the developer what is stored in the sorted dies array.
        printLine();
        for (int i = 0; i < Game.MAX_NUM_OF_DICE; i++) {
            Log.d(tag, "The number of " + (i + 1) + "s in the hand is " + footprint.get(i));
        }
        printLine();
    }

    private void printLine() {
        Log.d(tag, "--------------------------------------------------------");
    }
}
