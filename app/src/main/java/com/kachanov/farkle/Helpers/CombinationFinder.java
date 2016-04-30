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
        setFootprintOfTheHand(footprint);
        testForAllDiceCombs();

    }

    private void testForAllDiceCombs() {
        clearAvailableCombinationsArray();
        if (activeDice == 6) {
            if (handFootprintIsEqualTo(AllCombinations.run.getFootprintOfTheCombination())) {
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
        Log.d(tag, "Contents of availableCombinations:");
        for (int i = 0; i < availableCombinations.size(); i++) {
            Log.d(tag, availableCombinations.get(i).toString());
        }
    }

    public void testFor6DiceCombs() {
        //6 dice combinations must match footprints of their AllCombinations.combname.getFootprintOfTheCombination()
        if (handFootprintIsEqualTo(AllCombinations.sixOnes.getFootprintOfTheCombination())) {
            addCombination(AllCombinations.sixOnes);
        } else if (handFootprintIsEqualTo(AllCombinations.sixTwos.getFootprintOfTheCombination())) {
            addCombination(AllCombinations.sixTwos);
        } else if (handFootprintIsEqualTo(AllCombinations.sixThrees.getFootprintOfTheCombination())) {
            addCombination(AllCombinations.sixThrees);
        } else if (handFootprintIsEqualTo(AllCombinations.sixFours.getFootprintOfTheCombination())) {
            addCombination(AllCombinations.sixFours);
        } else if (handFootprintIsEqualTo(AllCombinations.sixFives.getFootprintOfTheCombination())) {
            addCombination(AllCombinations.sixFives);
        } else if (handFootprintIsEqualTo(AllCombinations.sixSixes.getFootprintOfTheCombination())) {
            addCombination(AllCombinations.sixSixes);
        }
        testFor5DiceCombs();
    }

    private void addCombination(Combination combination) {
        availableCombinations.add(combination);
    }

    public void testFor5DiceCombs() {
        if (handContainsFootprintOf(AllCombinations.fiveOnes.getFootprintOfTheCombination())) {
            addCombination(AllCombinations.fiveOnes);
        } else if (handContainsFootprintOf(AllCombinations.fiveTwos.getFootprintOfTheCombination())) {
            addCombination(AllCombinations.fiveTwos);
        } else if (handContainsFootprintOf(AllCombinations.fiveThrees.getFootprintOfTheCombination())) {
            addCombination(AllCombinations.fiveThrees);
        } else if (handContainsFootprintOf(AllCombinations.fiveFours.getFootprintOfTheCombination())) {
            addCombination(AllCombinations.fiveFours);
        } else if (handContainsFootprintOf(AllCombinations.fiveFives.getFootprintOfTheCombination())) {
            addCombination(AllCombinations.fiveFives);
        } else if (handContainsFootprintOf(AllCombinations.fiveSixes.getFootprintOfTheCombination())) {
            addCombination(AllCombinations.fiveSixes);
        }
        testFor4DiceCombs();
    }

    public void testFor4DiceCombs() {
        if (handContainsFootprintOf(AllCombinations.fourOnes.getFootprintOfTheCombination())) {
            addCombination(AllCombinations.fourOnes);
        } else if (handContainsFootprintOf(AllCombinations.fourTwos.getFootprintOfTheCombination())) {
            addCombination(AllCombinations.fourTwos);
        } else if (handContainsFootprintOf(AllCombinations.fourThrees.getFootprintOfTheCombination())) {
            addCombination(AllCombinations.fourThrees);
        } else if (handContainsFootprintOf(AllCombinations.fourFours.getFootprintOfTheCombination())) {
            addCombination(AllCombinations.fourFours);
        } else if (handContainsFootprintOf(AllCombinations.fourFives.getFootprintOfTheCombination())) {
            addCombination(AllCombinations.fourFives);
        } else if (handContainsFootprintOf(AllCombinations.fourSixes.getFootprintOfTheCombination())) {
            addCombination(AllCombinations.fourSixes);
        }
        testFor3DiceCombs();
    }

    public void testFor3DiceCombs() {
        //note that the logic is different because you can have more than one
        if (handContainsFootprintOf(AllCombinations.threeOnes.getFootprintOfTheCombination())) {
            addCombination(AllCombinations.threeOnes);
        }
        if (handContainsFootprintOf(AllCombinations.threeTwos.getFootprintOfTheCombination())) {
            addCombination(AllCombinations.threeTwos);
        }
        if (handContainsFootprintOf(AllCombinations.threeThrees.getFootprintOfTheCombination())) {
            addCombination(AllCombinations.threeThrees);
        }
        if (handContainsFootprintOf(AllCombinations.threeFours.getFootprintOfTheCombination())) {
            addCombination(AllCombinations.threeFours);
        }
        if (handContainsFootprintOf(AllCombinations.threeFives.getFootprintOfTheCombination())) {
            addCombination(AllCombinations.threeFives);
        }
        if (handContainsFootprintOf(AllCombinations.threeSixes.getFootprintOfTheCombination())) {
            addCombination(AllCombinations.threeSixes);
        }
        testFor2DiceCombs();
    }

    public void testFor2DiceCombs() {
        if (handContainsFootprintOf(AllCombinations.twoOnes.getFootprintOfTheCombination())) {
            addCombination(AllCombinations.twoOnes);
        }
        if (handContainsFootprintOf(AllCombinations.twoFives.getFootprintOfTheCombination())) {
            addCombination(AllCombinations.twoFives);
        }
        testFor1DiceCombs();
    }

    public void testFor1DiceCombs() {
        if (handContainsFootprintOf(AllCombinations.singleOne.getFootprintOfTheCombination())) {
            addCombination(AllCombinations.singleOne);
        }
        if (handContainsFootprintOf(AllCombinations.singleFive.getFootprintOfTheCombination())) {
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
