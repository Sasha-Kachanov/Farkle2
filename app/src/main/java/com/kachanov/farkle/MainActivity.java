package com.kachanov.farkle;

import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.kachanov.farkle.ButtonHandlers.RollHandler;
import com.kachanov.farkle.Helpers.Combination;
import com.kachanov.farkle.Helpers.CombinationButton;
import com.kachanov.farkle.Helpers.Die;
import com.kachanov.farkle.Helpers.Footprint;
import com.kachanov.farkle.Helpers.Game;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    //this object will handle everything when roll is pressed
    RollHandler rollHandler = new RollHandler();

    //holds the ImageViews of the dice
    private ArrayList<ImageView> diceImageViews = new ArrayList<>();

    //holds the available combinations buttons
    private ArrayList<CombinationButton> combinationButtons = new ArrayList<>();

    private ArrayList<Drawable> drawableDiceArrayList = new ArrayList<>();

    private ArrayList<Integer> iDsForCombinationsButtons = new ArrayList<>();

    private Footprint currentlyHolding = new Footprint();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializeAllViews();

        populateDrawableDiceArrayListWithPics();
    }

    //this gets invoked when the roll button get's pressed
    //it has to be public -- android limitation
    public void roll(View view) {
        //any die object that is selected must be now hidden RIGHT HERE

        //this takes care of rolling dice and displaying the 6 dice on the bottom
        rollHandler.setContext(getApplicationContext());
        rollHandler.handle();
        displayDice(rollHandler.getDiceArrayList());

        displayCombinations(rollHandler.getAnalyzer().getCombFinder().getAvailableCombinations());
    }

    //all that it does is displayDice the proper image based on the value of the die
    private void displayDice(ArrayList<Die> diceList) {
        //if a die is still used and the user hasn't selected it we update the image
        for (int i = 0; i < 6; i++) {
            if (diceList.get(i).isUsed() && !diceList.get(i).isSelected()) {
                diceImageViews.get(i).setImageDrawable(drawableDiceArrayList.get(diceList.get(i).getValue() - 1));
                diceImageViews.get(i).setVisibility(View.VISIBLE);
            }
        }
    }

    private void displayCombinations(ArrayList<Combination> combinations) {
        hideAllCombinations();
        for (int i = 0; i < combinations.size(); i++) {
            displayOneCombination(combinations.get(i));
        }
    }

    private void displayOneCombination(Combination combination) {
        for (int i = 0; i < Game.MAX_NUM_OF_COMBINATIONS_AT_A_TIME; i++) {
            if (combinationButtons.get(i).getButton().getVisibility() == View.INVISIBLE) {
                combinationButtons.get(i).getButton().setText(combination.toString());
                combinationButtons.get(i).getButton().setVisibility(View.VISIBLE);
                break;
            }
        }
    }

    //handlng of the pressing of combination buttons starts here
//    //note about combinationButtonWasClicked - a better implementation would be to have all buttons call the same method when they are clicked
//    //and then the method identifies which one was clicked. but i don't know how to implement that
//    //so instead i will have 5 methods that could be called.
    public void combinationButtonWasClicked(View view) {
        Combination combination = new Combination();
        ArrayList<Combination> availableCombinations = rollHandler.getAnalyzer().getCombFinder()
                .getAvailableCombinations();
        int indexOfButtonPressed = -1;
        for (int i = 0; i < Game.MAX_NUM_OF_COMBINATIONS_AT_A_TIME; i++) {
            if (view.getId() == iDsForCombinationsButtons.get(i)) {
                indexOfButtonPressed = i;
                combination = availableCombinations.get(i);
                break;
            }
        }

        if (combinationButtons.get(indexOfButtonPressed).isSelected() ||
                isLegalCombinationToPress(combination)) {
            //was it legal to click it? if not then show toast if yes then process with this function

            //this just changes the color of a button that was clicked
            //it seems a little wasteful doing the tests both here and in flipTheSwitchOnASingleButton
            //maybe if they were in an array you could just pass the int which will be the index.
            //or change the color within this function

            //update temp bank!!!

            flipTheSwitchOnASingleButton(combinationButtons.get(indexOfButtonPressed), combination);

        } else {
            Toast toast = Toast.makeText(getApplicationContext(), "You have to unselect an " +
                    "interfering combination first!", Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    private boolean isLegalCombinationToPress(Combination combination) {
        Footprint maxCapacity = rollHandler.getAnalyzer().getFootprintOfTheCurrentHand();
        if (currentlyHolding.canAdd(combination.getFootprintOfTheCombination(), maxCapacity)) {
            return true;
        }

        return false;
    }

    private void flipTheSwitchOnASingleButton(CombinationButton combinationButton, Combination combination) {
        if (!combinationButton.isSelected()) {
            combinationButton.getButton().setBackgroundResource(android.R.drawable.button_onoff_indicator_on);
            combinationButton.setSelected(true);
            currentlyHolding.add(combination.getFootprintOfTheCombination());
        } else {
            combinationButton.getButton().setBackgroundResource(android.R.drawable
                    .button_onoff_indicator_off);
            combinationButton.setSelected(false);
            currentlyHolding.subtract(combination.getFootprintOfTheCombination());
        }

    }


    private void initializeAllViews() {
        d("initializeAllViews() was called");
        populateDiceImageViews();
        populateCombinationButtons();
        setButtonsStyle();
        populateIDsForCombinationsButtons();
    }

    //this function creates ImageView objects and ties them to the layout Views

    private void populateDiceImageViews() {
        d("populateDiceImageViews was called");

        d("diceImageViews.size() == " + diceImageViews.size() + ". It should be 0 or 6");
        //the reason I am not using a for loop here is because I basically can't
        if (diceImageViews.size() == 0) {
            diceImageViews.add((ImageView) findViewById(R.id.dice0));
            diceImageViews.add((ImageView) findViewById(R.id.dice1));
            diceImageViews.add((ImageView) findViewById(R.id.dice2));
            diceImageViews.add((ImageView) findViewById(R.id.dice3));
            diceImageViews.add((ImageView) findViewById(R.id.dice4));
            diceImageViews.add((ImageView) findViewById(R.id.dice5));
            d("ImageViews for all 6 dies were successfully added to the diceImageViews array");
        }
    }

    //combinationButtons is an array and it needs to be populated with Button objects one single time
    private void populateCombinationButtons() {
        d("populateCombinationButtons() was called");

        d("combinationButtons.size() == " + combinationButtons.size() + ". It should be 0 or 6");
        if (combinationButtons.size() == 0) {
            combinationButtons.add(new CombinationButton((Button) findViewById(R.id.comb0)));
            combinationButtons.add(new CombinationButton((Button) findViewById(R.id.comb1)));
            combinationButtons.add(new CombinationButton((Button) findViewById(R.id.comb2)));
            combinationButtons.add(new CombinationButton((Button) findViewById(R.id.comb3)));
            combinationButtons.add(new CombinationButton((Button) findViewById(R.id.comb4)));
            combinationButtons.add(new CombinationButton((Button) findViewById(R.id.comb5)));
        }
    }

    private void setButtonsStyle() {
        d("setButtonsStyle() was called");
        for (int i = 0; i < 6; i++) {
            combinationButtons.get(i).getButton().setBackgroundResource(android.R.drawable.button_onoff_indicator_off);
        }
        Button bankButton = (Button) findViewById(R.id.bankButton);
        bankButton.setBackgroundResource(android.R.drawable.button_onoff_indicator_off);
        Button rollButton = (Button) findViewById((R.id.rollButton));
        rollButton.setBackgroundResource(android.R.drawable.button_onoff_indicator_off);
    }

    private void populateIDsForCombinationsButtons() {
        iDsForCombinationsButtons.add(R.id.comb0);
        iDsForCombinationsButtons.add(R.id.comb1);
        iDsForCombinationsButtons.add(R.id.comb2);
        iDsForCombinationsButtons.add(R.id.comb3);
        iDsForCombinationsButtons.add(R.id.comb4);
        iDsForCombinationsButtons.add(R.id.comb5);
    }


    //simply hides the buttons that displayDice combinations
    private void hideAllCombinations() {
        for (int i = 0; i < 6; i++) {
            combinationButtons.get(i).getButton().setVisibility(View.INVISIBLE);
        }
    }


    //shortcut to output to standard console
    private void d(String message) {
        Log.d(tag, message);
    }

    //shoving all images in an array
    private void populateDrawableDiceArrayListWithPics() {
        drawableDiceArrayList.add(getResources().getDrawable(R.drawable.dice1));
        drawableDiceArrayList.add(getResources().getDrawable(R.drawable.dice2));
        drawableDiceArrayList.add(getResources().getDrawable(R.drawable.dice3));
        drawableDiceArrayList.add(getResources().getDrawable(R.drawable.dice4));
        drawableDiceArrayList.add(getResources().getDrawable(R.drawable.dice5));
        drawableDiceArrayList.add(getResources().getDrawable(R.drawable.dice6));
        d("Size of drawableDiceArrayList is " + drawableDiceArrayList.size());
    }


    //tag that is used for debugging
    private static String tag = "MainActivity: ";
}

//OKAY SO I DO VERY MUCH NEED THE DEFINITIONS OF WHAT EACH COMBINATIONS USES. IF RUN IS AVAILABLE GO AND FIND THAT ONE AND SELECT IT. GO AND FIND THAT TWO
//AND SELECT IT. ETC. IF AS YOU DO IT ONE OF THEM IS ALREADY SELECTED THEN YOU DISPLAY A TOAST
//you could make a hand class and have a raw hand in it (dies) and a footprint hand (sorteddices)
