package com.kachanov.farkle.Helpers;

import android.util.Log;

import java.util.ArrayList;

/**
 * Created by sasha.kachanov on 4/30/2016.
 */
public class CombinationFinder {
    private int activeDice;
    private Footprint footprintOfTheHand;

    private ArrayList<Combination> availableCombinations = new ArrayList<>();

    public void findAvailableCombinations(Footprint footprint) {
        activeDice = footprint.getNumOfDice();
        footprintOfTheHand = footprint;
        testForAllDiceCombs();
    }

    public void printAvailableCombinations() {
        Log.d(tag, "Printing available combinations just in case:");
        for (int i = 0; i < availableCombinations.size(); i++) {
            Log.d(tag, "Printing: " + availableCombinations.get(i).toString());
        }
    }

    private void testForAllDiceCombs() {
        clearAvailableCombinationsArray();
        if (activeDice == 6) {
            if (handFootprintIsEqualTo(AllCombinations.run.getFootprintArrayListOfTheCombination())) {
                addCombination(AllCombinations.run);
            }
            //test for three pairs should happen here i believe through an else statement
            testFor6DiceCombs();
        } else if (activeDice == 5) {
            testFor5DiceCombs();
        } else if (activeDice == 4) {
            testFor4DiceCombs();
        } else if (activeDice == 3) {
            testFor3DiceCombs();
        } else if (activeDice == 2) {
            testFor2DiceCombs();
        } else if (activeDice == 1) {
            testFor1DiceCombs();
        }

        if (activeDice == 6 && availableCombinations.size() == 0){
            availableCombinations.add(AllCombinations.nothing);
        }

            Log.d(tag, "Contents of availableCombinations:");
        for (int i = 0; i < availableCombinations.size(); i++) {
            Log.d(tag, availableCombinations.get(i).toString());
        }
    }

    public void testFor6DiceCombs() {
        //6 dice combinations must match footprints of their AllCombinations.combname.getFootprintArrayListOfTheCombination()
        if (handFootprintIsEqualTo(AllCombinations.sixOnes.getFootprintArrayListOfTheCombination())) {
            addCombination(AllCombinations.sixOnes);
        } else if (handFootprintIsEqualTo(AllCombinations.sixTwos.getFootprintArrayListOfTheCombination())) {
            addCombination(AllCombinations.sixTwos);
        } else if (handFootprintIsEqualTo(AllCombinations.sixThrees.getFootprintArrayListOfTheCombination())) {
            addCombination(AllCombinations.sixThrees);
        } else if (handFootprintIsEqualTo(AllCombinations.sixFours.getFootprintArrayListOfTheCombination())) {
            addCombination(AllCombinations.sixFours);
        } else if (handFootprintIsEqualTo(AllCombinations.sixFives.getFootprintArrayListOfTheCombination())) {
            addCombination(AllCombinations.sixFives);
        } else if (handFootprintIsEqualTo(AllCombinations.sixSixes.getFootprintArrayListOfTheCombination())) {
            addCombination(AllCombinations.sixSixes);
        }
        testFor5DiceCombs();
    }

    private void addCombination(Combination combination) {
        availableCombinations.add(combination);
    }

    public void testFor5DiceCombs() {
        if (handContainsFootprintOf(AllCombinations.fiveOnes.getFootprintArrayListOfTheCombination())) {
            addCombination(AllCombinations.fiveOnes);
        } else if (handContainsFootprintOf(AllCombinations.fiveTwos.getFootprintArrayListOfTheCombination())) {
            addCombination(AllCombinations.fiveTwos);
        } else if (handContainsFootprintOf(AllCombinations.fiveThrees.getFootprintArrayListOfTheCombination())) {
            addCombination(AllCombinations.fiveThrees);
        } else if (handContainsFootprintOf(AllCombinations.fiveFours.getFootprintArrayListOfTheCombination())) {
            addCombination(AllCombinations.fiveFours);
        } else if (handContainsFootprintOf(AllCombinations.fiveFives.getFootprintArrayListOfTheCombination())) {
            addCombination(AllCombinations.fiveFives);
        } else if (handContainsFootprintOf(AllCombinations.fiveSixes.getFootprintArrayListOfTheCombination())) {
            addCombination(AllCombinations.fiveSixes);
        }
        testFor4DiceCombs();
    }

    public void testFor4DiceCombs() {
        if (handContainsFootprintOf(AllCombinations.fourOnes.getFootprintArrayListOfTheCombination())) {
            addCombination(AllCombinations.fourOnes);
        } else if (handContainsFootprintOf(AllCombinations.fourTwos.getFootprintArrayListOfTheCombination())) {
            addCombination(AllCombinations.fourTwos);
        } else if (handContainsFootprintOf(AllCombinations.fourThrees.getFootprintArrayListOfTheCombination())) {
            addCombination(AllCombinations.fourThrees);
        } else if (handContainsFootprintOf(AllCombinations.fourFours.getFootprintArrayListOfTheCombination())) {
            addCombination(AllCombinations.fourFours);
        } else if (handContainsFootprintOf(AllCombinations.fourFives.getFootprintArrayListOfTheCombination())) {
            addCombination(AllCombinations.fourFives);
        } else if (handContainsFootprintOf(AllCombinations.fourSixes.getFootprintArrayListOfTheCombination())) {
            addCombination(AllCombinations.fourSixes);
        }
        testFor3DiceCombs();
    }

    public void testFor3DiceCombs() {
        //note that the logic is different because you can have more than one
        if (handContainsFootprintOf(AllCombinations.threeOnes.getFootprintArrayListOfTheCombination())) {
            addCombination(AllCombinations.threeOnes);
        }
        if (handContainsFootprintOf(AllCombinations.threeTwos.getFootprintArrayListOfTheCombination())) {
            addCombination(AllCombinations.threeTwos);
        }
        if (handContainsFootprintOf(AllCombinations.threeThrees.getFootprintArrayListOfTheCombination())) {
            addCombination(AllCombinations.threeThrees);
        }
        if (handContainsFootprintOf(AllCombinations.threeFours.getFootprintArrayListOfTheCombination())) {
            addCombination(AllCombinations.threeFours);
        }
        if (handContainsFootprintOf(AllCombinations.threeFives.getFootprintArrayListOfTheCombination())) {
            addCombination(AllCombinations.threeFives);
        }
        if (handContainsFootprintOf(AllCombinations.threeSixes.getFootprintArrayListOfTheCombination())) {
            addCombination(AllCombinations.threeSixes);
        }
        testFor2DiceCombs();
    }

    public void testFor2DiceCombs() {
        if (handContainsFootprintOf(AllCombinations.twoOnes.getFootprintArrayListOfTheCombination())) {
            addCombination(AllCombinations.twoOnes);
        }
        if (handContainsFootprintOf(AllCombinations.twoFives.getFootprintArrayListOfTheCombination())) {
            addCombination(AllCombinations.twoFives);
        }
        testFor1DiceCombs();
    }

    public void testFor1DiceCombs() {
        if (handContainsFootprintOf(AllCombinations.singleOne.getFootprintArrayListOfTheCombination())) {
            addCombination(AllCombinations.singleOne);
        }
        if (handContainsFootprintOf(AllCombinations.singleFive.getFootprintArrayListOfTheCombination())) {
            addCombination(AllCombinations.singleFive);
        }
    }

    public boolean handFootprintIsEqualTo(ArrayList<Integer> footprintOfACombination) {
        Log.d(tag, "Comparing the footprintOfTheHand of " + this.footprintOfTheHand.getFootprintArrayList().toString() + " " +
                "to " + footprintOfACombination.toString());
        if (this.footprintOfTheHand.equals(footprintOfACombination)) {
            Log.d(tag, "The hand contains/is: " + footprintOfACombination);
            return true;
        }
        return false;
    }

    public boolean handContainsFootprintOf(ArrayList<Integer> footprintOfACombination) {
        Log.d(tag, "Comparing the footprintOfTheHand of " + this.footprintOfTheHand.getFootprintArrayList().toString() + " " +
                "to " + footprintOfACombination.toString());
        //look at 1 thru 6 indexes
        for (int i = 0; i < Game.MAX_NUM_OF_DICE; i++) {
            if (footprintOfACombination.get(i) != 0 && this.footprintOfTheHand.getFootprintArrayList().get(i) >= footprintOfACombination.get(i)) {
                Log.d(tag, "The hand contains the footprintOfTheHand of: " + footprintOfACombination);
                return true;
            }
        }
        return false;
    }


    private void clearAvailableCombinationsArray() {
        availableCombinations.clear();
//        for (int i = 0; i < Game.MAX_NUM_OF_COMBINATIONS_AT_A_TIME; i++) {
//            availableCombinations.get(i).clearAllFields();
//        }
    }

    //getters and setters
    public ArrayList<Combination> getAvailableCombinations() {
        return availableCombinations;
    }

    private static String tag = "CombinationFinder: ";

    //setters
    private void setFootprintOfTheHand(Footprint footprintOfTheHand) {
        this.footprintOfTheHand = footprintOfTheHand;
    }
}
