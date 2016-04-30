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

            //back end rolling - rolls every dice object in the diceArrayList which populates the value
            // field of each dice with a random number from 1 to 6
            populateDiceArrayListWithRands();

            //find available combinations and display them as buttons
            //populateCombinationsButtons();
            analyzer.analyze(diceArrayList);
        }
    }

    private boolean legalToRoll() {
        if (true) {
            return true;

        } else {
            CharSequence text = "You must have at least 300 points and at least one combination " +
                    "selected to be able to roll!";
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(mContext, text, duration);
            toast.show();
            return false;
        }
    }







//
//    public void findCombinations() {
//        //start the chain with 6 dice combs, it will call the rest
//        testForAllDiceCombs();
//    }
//
//    public void testForAllDiceCombs() {
//        if (activeDice == 6) {
//            if (compareHandFootprintTo(AllCombinations.run.footprint)) {
//                addCombination(AllCombinations.run);
//            }
//            //test for three pairs should happen here i believe through an else statement
//            testFor6DiceCombs();
//        } else if (activeDice == 5) {
//            testFor5DiceCombs();
//        } else if (activeDice == 4) {
//            testFor4DiceCombs();
//        } else if (activeDice == 3) {
//            testFor3DiceCombs();
//        } else if (activeDice == 2) {
//            testFor2DiceCombs();
//        } else if (activeDice == 1) {
//            testFor1DiceCombs();
//        }
//        //i think you still have to add the else if test for 4 dice etc down to single
//
//    }
//
//    public void testFor6DiceCombs() {
//        //the way you could "optimize" this is by creating a method called addACombination(Combination) that calls the combs.add and populateSingleCombination
//        //6 dice combinations must match footprints of their AllCombinations.combname.footprint
//        if (compareHandFootprintTo(AllCombinations.sixOnes.footprint)) {
//            addCombination(AllCombinations.sixOnes);
//        } else if (compareHandFootprintTo(AllCombinations.sixTwos.footprint)) {
//            addCombination(AllCombinations.sixTwos);
//        } else if (compareHandFootprintTo(AllCombinations.sixThrees.footprint)) {
//            addCombination(AllCombinations.sixThrees);
//        } else if (compareHandFootprintTo(AllCombinations.sixFours.footprint)) {
//            addCombination(AllCombinations.sixFours);
//        } else if (compareHandFootprintTo(AllCombinations.sixFives.footprint)) {
//            addCombination(AllCombinations.sixFives);
//        } else if (compareHandFootprintTo(AllCombinations.sixSixes.footprint)) {
//            addCombination(AllCombinations.sixSixes);
//        }
//        testFor5DiceCombs();
//
//    }
//
//    private void addCombination(Combination combination) {
//        combs.add(combination);
//        populateSingleCombinationButton(combination);
//    }
//
//    public void testFor5DiceCombs() {
//        if (handContainsFootprintOf(AllCombinations.fiveOnes.footprint)) {
//            addCombination(AllCombinations.fiveOnes);
//        } else if (handContainsFootprintOf(AllCombinations.fiveTwos.footprint)) {
//            addCombination(AllCombinations.fiveTwos);
//        } else if (handContainsFootprintOf(AllCombinations.fiveThrees.footprint)) {
//            addCombination(AllCombinations.fiveThrees);
//        } else if (handContainsFootprintOf(AllCombinations.fiveFours.footprint)) {
//            addCombination(AllCombinations.fiveFours);
//        } else if (handContainsFootprintOf(AllCombinations.fiveFives.footprint)) {
//            addCombination(AllCombinations.fiveFives);
//        } else if (handContainsFootprintOf(AllCombinations.fiveSixes.footprint)) {
//            addCombination(AllCombinations.fiveSixes);
//        }
//        testFor4DiceCombs();
//    }
//
//    public void testFor4DiceCombs() {
//        if (handContainsFootprintOf(AllCombinations.fourOnes.footprint)) {
//            addCombination(AllCombinations.fourOnes);
//        } else if (handContainsFootprintOf(AllCombinations.fourTwos.footprint)) {
//            addCombination(AllCombinations.fourTwos);
//        } else if (handContainsFootprintOf(AllCombinations.fourThrees.footprint)) {
//            addCombination(AllCombinations.fourThrees);
//        } else if (handContainsFootprintOf(AllCombinations.fourFours.footprint)) {
//            addCombination(AllCombinations.fourFours);
//        } else if (handContainsFootprintOf(AllCombinations.fourFives.footprint)) {
//            addCombination(AllCombinations.fourFives);
//        } else if (handContainsFootprintOf(AllCombinations.fourSixes.footprint)) {
//            addCombination(AllCombinations.fourSixes);
//        }
//        testFor3DiceCombs();
//    }
//
//    public void testFor3DiceCombs() {
//        //note that the logic is different because you can have more than one
//        if (handContainsFootprintOf(AllCombinations.threeOnes.footprint)) {
//            addCombination(AllCombinations.threeOnes);
//        }
//        if (handContainsFootprintOf(AllCombinations.threeTwos.footprint)) {
//            addCombination(AllCombinations.threeTwos);
//        }
//        if (handContainsFootprintOf(AllCombinations.threeThrees.footprint)) {
//            addCombination(AllCombinations.threeThrees);
//        }
//        if (handContainsFootprintOf(AllCombinations.threeFours.footprint)) {
//            addCombination(AllCombinations.threeFours);
//        }
//        if (handContainsFootprintOf(AllCombinations.threeFives.footprint)) {
//            addCombination(AllCombinations.threeFives);
//        }
//        if (handContainsFootprintOf(AllCombinations.threeSixes.footprint)) {
//            addCombination(AllCombinations.threeSixes);
//        }
//        testFor2DiceCombs();
//    }
//
//    public void testFor2DiceCombs() {
//        if (handContainsFootprintOf(AllCombinations.twoOnes.footprint)) {
//            addCombination(AllCombinations.twoOnes);
//        }
//        if (handContainsFootprintOf(AllCombinations.twoFives.footprint)) {
//            addCombination(AllCombinations.twoFives);
//        }
//        testFor1DiceCombs();
//    }
//
//    public void testFor1DiceCombs() {
//        // i need a contains method. the footprint will add up to 6 dice. but does it contain a a single one??? i can't use equals to 0 1 0 0  0 0 0..
//        if (handContainsFootprintOf(AllCombinations.singleOne.footprint)) {
//            addCombination(AllCombinations.singleOne);
//        }
//        if (handContainsFootprintOf(AllCombinations.singleFive.footprint)) {
//            addCombination(AllCombinations.singleFive);
//        }
//    }
//
//    //populates the diceArrayList with random numbers
//    //more specifically it checks if the die is still used, and if it is not isSelected, then it
//    // rolls it.
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

    //
//
//
//
//    public boolean compareHandFootprintTo(ArrayList<Integer> footprint) {
//        if (sortedDices.equals(footprint)) {
//            Log.d(tag, "The hand contains/is: " + footprint);
//            return true;
//        }
//        return false;
//    }
//
//    public boolean handContainsFootprintOf(ArrayList<Integer> footprint) {
//        //look at 1 thru 6 indeces
//        for (int i = 1; i <= 6; i++) {
//            if (footprint.get(i) != 0 && sortedDices.get(i) >= footprint.get(i)) {
//                Log.d(tag, "The hand contains the footprint of: " + footprint);
//                return true;
//            }
//        }
//
//
//        return false;
//    }
//
//    //// FIXME: 3/16/16 not even close to being done
//    private void populateDicesWithRun() {
//        for (int i = 0; i < 6; i++) {
//            dices.get(i).value = i + 1;
//        }
//    }
//
//    private void populateSingleCombinationButton(Combination comb) {
//        Button button = (Button) findViewById(getNextInvisibleButton());
//        String text;
//        text = comb.name + " - " + comb.points;
//        button.setText(text);
//        button.setVisibility(View.VISIBLE);
//    }
//
//    private int getNextInvisibleButton() {
//        if (comb0Button.getVisibility() == View.INVISIBLE) {
//            return R.id.comb0;
//        } else if (comb1Button.getVisibility() == View.INVISIBLE) {
//            return R.id.comb1;
//        } else if (comb2Button.getVisibility() == View.INVISIBLE) {
//            return R.id.comb2;
//        } else if (comb3Button.getVisibility() == View.INVISIBLE) {
//            return R.id.comb3;
//        } else if (comb4Button.getVisibility() == View.INVISIBLE) {
//            return R.id.comb4;
//        }
//        Log.d(tag, "If this message gets displayed then you are in big trouble, terrible logic error");
//        return 0;
//    }
//
//    //okay so it takes combs array and for each combination (there are 5 of them) it resets their values to defaults

//    //handling of the roll button ends here
//

}
