package com.kachanov.farkle.ButtonHandlers;

import android.util.Log;

import com.kachanov.farkle.Helpers.AllCombinations;
import com.kachanov.farkle.Helpers.Combination;
import com.kachanov.farkle.Helpers.Die;
import com.kachanov.farkle.Helpers.Footprint;
import com.kachanov.farkle.Helpers.Game;

import java.util.ArrayList;

/**
 * Created by sasha.kachanov on 5/14/2016.
 */
public class CombinationHandler {
    private static String tag = "CombinationHandler:";
    private boolean theButtonIsAlreadySelected;
    private Combination combinationThatWasClicked;


    private Integer tempBank;
    private ArrayList<Die> diceArrayList;

    public CombinationHandler(Integer tempBank) {
        this.tempBank = tempBank;
    }

    //the bit representation/histogram of the current hand
    private Footprint currentlyHolding = new Footprint();

    public boolean isLegalSelection(Combination combinationThatWasClicked, Footprint maxCapacity,
                                    boolean theButtonIsAlreadySelected) {
        if (theButtonIsAlreadySelected || currentlyHolding.canAdd(combinationThatWasClicked
                .getFootprintOfTheCombination(), maxCapacity)) {
            //we set this for the handle() method to make use of later
            this.theButtonIsAlreadySelected = theButtonIsAlreadySelected;
            this.combinationThatWasClicked = combinationThatWasClicked;
            return true;
        } else {
            return false;
        }
    }

    public void resetCurrentlyHoldingArray() {
        for (int i = 0; i < 6; i++) {
            currentlyHolding.getFootprintArrayList().set(i, 0);
        }
    }

    public void setDiceArrayList(ArrayList<Die> diceArrayList) {
        this.diceArrayList = diceArrayList;
    }

    public void handle() {
        //this only gets called if it was legal to press that combination button
        if (theButtonIsAlreadySelected) {
            currentlyHolding.subtract(combinationThatWasClicked.getFootprintOfTheCombination());
            tempBank -= combinationThatWasClicked.getPoints();
            markSelectedDice(combinationThatWasClicked, false);
        } else {
            currentlyHolding.add(combinationThatWasClicked.getFootprintOfTheCombination());
            tempBank += combinationThatWasClicked.getPoints();
            markSelectedDice(combinationThatWasClicked, true);
        }
        printCurrentlyHolding();
        Log.d(tag, "Temporary bank is " + tempBank);
    }

    private void printCurrentlyHolding() {
        Log.d("Footprint:", "The user is now currently holding " + currentlyHolding
                .getFootprintArrayList().toString());
        for (int i = 0; i < Game.MAX_NUM_OF_DICE; i++) {
            Log.d("Footprint:", "[" + i + "]" + "(" + diceArrayList.get(i).getValue() + ") " +
                    "selected: " +
                    diceArrayList.get(i).isSelected() + " | used: " + diceArrayList.get(i).isUsed());
        }
    }

    private void markSelectedDice(Combination combination, boolean weAreSelecting) {
        if (combinationThatWasClicked.getName() == AllCombinations.nothing.getName()) {
            for (int i = 0; i < Game.MAX_NUM_OF_DICE; i++) {
                if (weAreSelecting) {
                    diceArrayList.get(i).setSelected(true);
                } else {
                    diceArrayList.get(i).setSelected(false);
                }
            }
        } else {
            for (int i = 0; i < Game.MAX_NUM_OF_DICE; i++) {
                if (combination.getFootprintArrayListOfTheCombination().get(i) != 0) {
                    findAndMarkThatValueOfDie(combination.getFootprintArrayListOfTheCombination().get(i), i,
                            weAreSelecting);
                }
            }
        }
    }

    private void findAndMarkThatValueOfDie(Integer howManyDiceOfSomeValue, int indexOfTheDieThatNeedsToBeMarked, boolean
            weAreSelecting) {
        int valueOfTheDieThatNeedsToBeMarked = indexOfTheDieThatNeedsToBeMarked + 1;
        int howManyDiceOfThisValueWeAlreadyFound = 0;
        if (weAreSelecting) {
            for (int i = 0; i < Game.MAX_NUM_OF_DICE; i++) {
                if (diceArrayList.get(i).getValue() == valueOfTheDieThatNeedsToBeMarked &&
                        !diceArrayList.get(i).isSelected() && diceArrayList.get(i).isUsed()) {
                    diceArrayList.get(i).setSelected(true);
                    howManyDiceOfThisValueWeAlreadyFound++;
                    if (howManyDiceOfThisValueWeAlreadyFound == howManyDiceOfSomeValue) {
                        break;
                    }
                }
            }
        } else {
            for (int i = 0; i < Game.MAX_NUM_OF_DICE; i++) {
                if (diceArrayList.get(i).getValue() == valueOfTheDieThatNeedsToBeMarked &&
                        diceArrayList.get(i).isSelected() && diceArrayList.get(i).isUsed()) {
                    diceArrayList.get(i).setSelected(false);
                    howManyDiceOfThisValueWeAlreadyFound++;
                    if (howManyDiceOfThisValueWeAlreadyFound == howManyDiceOfSomeValue) {
                        break;
                    }
                }
            }
        }
    }


    public int getTempBank() {
        return tempBank;
    }

    public void setTempBank(Integer tempBank) {
        this.tempBank = tempBank;
    }
}


