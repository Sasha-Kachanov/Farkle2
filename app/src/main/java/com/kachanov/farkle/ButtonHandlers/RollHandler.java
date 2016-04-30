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
//
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

    //
//
//
//
//
//
//
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
