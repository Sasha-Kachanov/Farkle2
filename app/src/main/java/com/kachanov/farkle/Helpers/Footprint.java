package com.kachanov.farkle.Helpers;

import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by sasha.kachanov on 4/30/2016.
 */
public class Footprint {
    private static String tag = "Footprint: ";

    private ArrayList<Integer> footprintArrayList = new ArrayList<>(Arrays.asList(0, 0, 0, 0, 0, 0));

    public Footprint() {
    }

    public Footprint(ArrayList<Integer> footprintArrayList) {
        this.footprintArrayList = footprintArrayList;
    }

    public void generateFootprint(ArrayList<Die> hand) {
        clearFootprint();

        //if the dice is used (not unused) add it to the footprintArrayList
        for (int i = 0; i < Game.MAX_NUM_OF_DICE; i++) {
            if (hand.get(i).isUsed()) {
                int valueOfDie = hand.get(i).getValue();
                //retrieve how many of those dice (ones, two,..) we already have
                int newVal = footprintArrayList.get(valueOfDie - 1);
                //update that value
                newVal++;
                //store the new amount of ones, twos, ... back in the array
                footprintArrayList.set(valueOfDie - 1, newVal);
            }
        }
        printFootprintInLogcat();
    }

    public void add(Footprint footprint) {
        for (int i = 0; i < Game.MAX_NUM_OF_DICE; i++) {
            Integer temp;
            temp = this.footprintArrayList.get(i) + footprint.getFootprintArrayList().get(i);
            this.footprintArrayList.set(i, temp);
        }
    }

    public void subtract(Footprint footprint){
        for (int i = 0; i < Game.MAX_NUM_OF_DICE; i++) {
            Integer temp;
            temp = this.footprintArrayList.get(i) - footprint.getFootprintArrayList().get(i);
            this.footprintArrayList.set(i, temp);
        }
    }

    public boolean canAdd(Footprint footprintOfCombination, Footprint maxCapacity) {
        Log.d(tag, "The user is currently holding: " + this.getFootprintArrayList().toString());
        Log.d(tag, "The user is trying to add: " + footprintOfCombination
                .getFootprintArrayList().toString());
        Log.d(tag, "The max capacity aka the hand is: " + maxCapacity.getFootprintArrayList()
                .toString());
        for (int i = 0; i < Game.MAX_NUM_OF_DICE; i++) {
            if (footprintOfCombination.getFootprintArrayList().get(i) + this.getFootprintArrayList()
                    .get(i) > maxCapacity.getFootprintArrayList().get(i)) {
                Log.d(tag, "Returning false - we have an overflow");
                return false;
            }
        }
        Log.d(tag, "Returning true - the user may select that combination");
        return true;
    }

    //it sets all the elements of the sortedDice array to zero, must be called between rolls
    //note that it does not work as clear() defined for ArrayLists; we are not destorying the
    // objects here
    private void clearFootprint() {
        for (int i = 0; i < Game.MAX_NUM_OF_DICE; i++) {
            footprintArrayList.set(i, 0);
        }
    }

    public int getNumOfDice() {
        int numOfDiceInTheFootprint = 0;
        for (int i = 0; i < Game.MAX_NUM_OF_DICE; i++) {
            numOfDiceInTheFootprint += footprintArrayList.get(i);
        }
        Log.d(tag, "The number of dice in this footprintArrayList is " + numOfDiceInTheFootprint);
        return numOfDiceInTheFootprint;
    }


    //    write 6 lines outputting the footprintArrayList of the hand
    private void printFootprintInLogcat() {
        //this simply shows the developer what is stored in the sorted dies array.
        printLine();
        Log.d(tag, "BELOW IS THE FOOTPRINT OF THE HAND THAT WAS ROLLED");
        for (int i = 0; i < Game.MAX_NUM_OF_DICE; i++) {
            Log.d(tag, "The number of " + (i + 1) + "s in the hand is " + footprintArrayList.get(i));
        }
        printLine();
    }

    private void printLine() {
        Log.d(tag, "--------------------------------------------------------");
    }

    //setters
    public void setFootprintOfTheCombination(ArrayList<Integer> footprint) {
        this.footprintArrayList = footprint;
    }

    public ArrayList<Integer> getFootprintArrayList() {
        return footprintArrayList;
    }
}
