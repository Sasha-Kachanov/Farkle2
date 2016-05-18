package com.kachanov.farkle.ButtonHandlers;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.kachanov.farkle.Helpers.AllCombinations;
import com.kachanov.farkle.Helpers.Combination;
import com.kachanov.farkle.Helpers.Die;
import com.kachanov.farkle.Helpers.Game;
import com.kachanov.farkle.Helpers.HandAnalyzer;
import com.kachanov.farkle.MainActivity;
import com.kachanov.farkle.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

/**
 * Created by Mac on 4/27/16.
 */
public class RollHandler {
    private static String tag = "RollHandler: ";

    private Context mContext;

    public void setContext(Context context) {
        this.mContext = context;
    }

    private HandAnalyzer analyzer = new HandAnalyzer();

    private boolean firstRoll = true;

    public HandAnalyzer getAnalyzer() {
        return analyzer;
    }

    //these are the 6 dice that will be displayed on the bottom of the screen.
    private ArrayList<Die> diceArrayList = new ArrayList<>(Arrays.asList(new Die(), new Die(), new
            Die(), new Die(), new Die(), new Die()));

    //getter
    public ArrayList<Die> getDiceArrayList() {
        return diceArrayList;
    }

    //starting from here is everything that handles the roll button
    public void handle() {
        //need to test if rolling is legal, if not display toast and end function with a return I suppose
        if (legalToRoll()) {
            numberOfSelectedCombinations = 0;
            //reset the backend combinations and all the dice stuff
            analyzer.getCombFinder().printAvailableCombinations();

            //back end rolling - rolls every dice object in the diceArrayList which populates the value
            // field of each dice with a random number from 1 to 6
            populateDiceArrayListWithRands();

            //find available combinations and display them as buttons
            //populateCombinationsButtons();
            analyzer.analyze(diceArrayList);
        }
    }

    private Integer numberOfSelectedCombinations;

    public void setNumberOfSelectedCombinations(Integer numberOfSelectedCombinations) {
        this.numberOfSelectedCombinations = numberOfSelectedCombinations;
        Log.d(tag, "The number of selected combinations is: " + this.numberOfSelectedCombinations);
    }

    private boolean legalToRoll() {
        String tag = "zhopa";
        Log.d(tag, "IT IS A FREE ROLL: " + isFreeRoll());
        Log.d(tag, "IT IS A FIRST ROLL: " + isFirstRoll());
        if (numberOfSelectedCombinations != null) {
            Log.d(tag, "NUMBEROFSELECTEDCOMINATIONS IS > 0 " + (numberOfSelectedCombinations > 0));
            Log.d(tag, "NUMBEROFSELECTEDCOMINATIONS IS: " + (numberOfSelectedCombinations));
        }
        if (isFreeRoll()) {
            numberOfSelectedCombinations = 0;
            return true;
        } else if (isFirstRoll()) {
            numberOfSelectedCombinations = 0;
            firstRoll = false;
            return true;
        } else if (numberOfSelectedCombinations > 0) {
            numberOfSelectedCombinations = 0;
            return true;
        } else {
            CharSequence text = "You must select at least one combination to be able to roll!";
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(mContext, text, duration);
            toast.show();
            return false;
        }
    }

    public boolean isFreeRoll() {
        int count = 0;
        for (int i = 0; i < Game.MAX_NUM_OF_DICE; i++) {
            if (diceArrayList.get(i).isSelected()) {
                count++;
            }
        }
        if (count == 6) {

            Log.d(tag, "FREE ROLL");
            return true;
        } else {
            return false;
        }
    }

    public void resetThingsForFreeRoll() {
        for (int i = 0; i < Game.MAX_NUM_OF_DICE; i++) {
            diceArrayList.get(i).setSelected(false);
            diceArrayList.get(i).setUsed(true);
        }
    }


    //populates the diceArrayList with random numbers
    //more specifically it checks if the die is still used, and if it is not isSelected, then it
    // rolls it.
    private void populateDiceArrayListWithRands() {
        for (int i = 0; i < 6; i++) {
            if (diceArrayList.get(i).isUsed() && !diceArrayList.get(i).isSelected()) {
                diceArrayList.get(i).roll();
            }
        }
        //Display the hand in logcat -- for testing and debugging
        printDiceArrayListInLogcat();
    }

    //writes 6 lines representing the back end array
    private void printDiceArrayListInLogcat() {
        printLine();
        Log.d(tag, "BELOW IS diceArrayList, i.e., RAW HAND THAT WAS ROLLED");
        for (int i = 0; i < 6; i++) {
            Log.d(tag, "dice[" + i + "] == " + diceArrayList.get(i).getValue() + "," +
                    " isUsed == " + diceArrayList.get(i).isUsed() + ", isSelected == " +
                    diceArrayList.get
                            (i).isSelected());
        }
        printLine();
    }

    private void printLine() {
        Log.d(tag, "--------------------------------------------------------");
    }

    public boolean isFirstRoll() {
        return firstRoll;
    }

    public void setFirstRoll(boolean firstRoll) {
        this.firstRoll = firstRoll;
    }
}
