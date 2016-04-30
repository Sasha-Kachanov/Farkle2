package com.kachanov.farkle.Helpers;

import android.util.Log;

import java.util.ArrayList;

/**
 * Created by sasha.kachanov on 4/30/2016.
 */
public class CombinationFinder {
    private int activeDice;

    private ArrayList<Combination> availableCombinations;

    public void findAvailableCombinations(Footprint footprint) {
        activeDice = footprint.getNumOfDice();
        clearAvailableCombinationsArray();
    }

    private void testForAllDiceCombs() {
        if (activeDice == 6) {
            if (handFootprintIsEqualTo(AllCombinations.run.footprint)) {
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
        //i think you still have to add the else if test for 4 dice etc down to single

    }


    public void testFor6DiceCombs() {
        //the way you could "optimize" this is by creating a method called addACombination(Combination) that calls the combs.add and populateSingleCombination
        //6 dice combinations must match footprints of their AllCombinations.combname.footprint
        if (handFootprintIsEqualTo(AllCombinations.sixOnes.footprint)) {
            addCombination(AllCombinations.sixOnes);
        } else if (handFootprintIsEqualTo(AllCombinations.sixTwos.footprint)) {
            addCombination(AllCombinations.sixTwos);
        } else if (handFootprintIsEqualTo(AllCombinations.sixThrees.footprint)) {
            addCombination(AllCombinations.sixThrees);
        } else if (handFootprintIsEqualTo(AllCombinations.sixFours.footprint)) {
            addCombination(AllCombinations.sixFours);
        } else if (handFootprintIsEqualTo(AllCombinations.sixFives.footprint)) {
            addCombination(AllCombinations.sixFives);
        } else if (handFootprintIsEqualTo(AllCombinations.sixSixes.footprint)) {
            addCombination(AllCombinations.sixSixes);
        }
        testFor5DiceCombs();

    }

    private void addCombination(Combination combination) {
        availableCombinations.add(combination);
    }

    public void testFor5DiceCombs() {
        if (handContainsFootprintOf(AllCombinations.fiveOnes.footprint)) {
            addCombination(AllCombinations.fiveOnes);
        } else if (handContainsFootprintOf(AllCombinations.fiveTwos.footprint)) {
            addCombination(AllCombinations.fiveTwos);
        } else if (handContainsFootprintOf(AllCombinations.fiveThrees.footprint)) {
            addCombination(AllCombinations.fiveThrees);
        } else if (handContainsFootprintOf(AllCombinations.fiveFours.footprint)) {
            addCombination(AllCombinations.fiveFours);
        } else if (handContainsFootprintOf(AllCombinations.fiveFives.footprint)) {
            addCombination(AllCombinations.fiveFives);
        } else if (handContainsFootprintOf(AllCombinations.fiveSixes.footprint)) {
            addCombination(AllCombinations.fiveSixes);
        }
        testFor4DiceCombs();
    }

    public void testFor4DiceCombs() {
        if (handContainsFootprintOf(AllCombinations.fourOnes.footprint)) {
            addCombination(AllCombinations.fourOnes);
        } else if (handContainsFootprintOf(AllCombinations.fourTwos.footprint)) {
            addCombination(AllCombinations.fourTwos);
        } else if (handContainsFootprintOf(AllCombinations.fourThrees.footprint)) {
            addCombination(AllCombinations.fourThrees);
        } else if (handContainsFootprintOf(AllCombinations.fourFours.footprint)) {
            addCombination(AllCombinations.fourFours);
        } else if (handContainsFootprintOf(AllCombinations.fourFives.footprint)) {
            addCombination(AllCombinations.fourFives);
        } else if (handContainsFootprintOf(AllCombinations.fourSixes.footprint)) {
            addCombination(AllCombinations.fourSixes);
        }
        testFor3DiceCombs();
    }

    public void testFor3DiceCombs() {
        //note that the logic is different because you can have more than one
        if (handContainsFootprintOf(AllCombinations.threeOnes.footprint)) {
            addCombination(AllCombinations.threeOnes);
        }
        if (handContainsFootprintOf(AllCombinations.threeTwos.footprint)) {
            addCombination(AllCombinations.threeTwos);
        }
        if (handContainsFootprintOf(AllCombinations.threeThrees.footprint)) {
            addCombination(AllCombinations.threeThrees);
        }
        if (handContainsFootprintOf(AllCombinations.threeFours.footprint)) {
            addCombination(AllCombinations.threeFours);
        }
        if (handContainsFootprintOf(AllCombinations.threeFives.footprint)) {
            addCombination(AllCombinations.threeFives);
        }
        if (handContainsFootprintOf(AllCombinations.threeSixes.footprint)) {
            addCombination(AllCombinations.threeSixes);
        }
        testFor2DiceCombs();
    }

    public void testFor2DiceCombs() {
        if (handContainsFootprintOf(AllCombinations.twoOnes.footprint)) {
            addCombination(AllCombinations.twoOnes);
        }
        if (handContainsFootprintOf(AllCombinations.twoFives.footprint)) {
            addCombination(AllCombinations.twoFives);
        }
        testFor1DiceCombs();
    }

    public void testFor1DiceCombs() {
        // i need a contains method. the footprint will add up to 6 dice. but does it contain a a single one??? i can't use equals to 0 1 0 0  0 0 0..
        if (handContainsFootprintOf(AllCombinations.singleOne.footprint)) {
            addCombination(AllCombinations.singleOne);
        }
        if (handContainsFootprintOf(AllCombinations.singleFive.footprint)) {
            addCombination(AllCombinations.singleFive);
        }
    }

    public boolean handFootprintIsEqualTo(ArrayList<Integer> footprint) {
        if (handFootprint.equals(footprint)) {
            Log.d(tag, "The hand contains/is: " + footprint);
            return true;
        }
        return false;
    }

    public boolean handContainsFootprintOf(ArrayList<Integer> footprint) {
        Log.d(tag, "Comparing the footprint of " + handFootprint.toString() + " to " + footprint
                .toString());
        //look at 1 thru 6 indexes
        for (int i = 0; i < Game.MAX_NUM_OF_DICE; i++) {
            if (footprint.get(i) != 0 && handFootprint.get(i) >= footprint.get(i)) {
                Log.d(tag, "The hand contains the footprint of: " + footprint);
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
}
