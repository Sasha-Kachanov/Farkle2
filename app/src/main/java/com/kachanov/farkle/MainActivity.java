package com.kachanov.farkle;

import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.kachanov.farkle.ButtonHandlers.BankHandler;
import com.kachanov.farkle.ButtonHandlers.CombinationHandler;
import com.kachanov.farkle.ButtonHandlers.RollHandler;
import com.kachanov.farkle.Helpers.Combination;
import com.kachanov.farkle.Helpers.CombinationButton;
import com.kachanov.farkle.Helpers.Die;
import com.kachanov.farkle.Helpers.Footprint;
import com.kachanov.farkle.Helpers.Game;

import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class MainActivity extends AppCompatActivity {
    private Integer tempBank = 0;
    private int permBank = 0;
    //this object will handle everything when roll is pressed
    private RollHandler rollHandler = new RollHandler();

    //holds the ImageViews of the dice
    private ArrayList<ImageView> diceImageViews = new ArrayList<>();

    //holds the available combinations buttons
    private ArrayList<CombinationButton> combinationButtons = new ArrayList<>();

    //holds the references to the actual images of dice
    private ArrayList<Drawable> drawableDiceArrayList = new ArrayList<>();

    // each combination button has a unique id that looks like R.id.x . Storing them in an array
    // will allow us to use loops, etc.
    private ArrayList<Integer> iDsForCombinationsButtons = new ArrayList<>();

    private CombinationHandler combinationHandler = new CombinationHandler(tempBank);

    private BankHandler bankHandler = new BankHandler(tempBank);

    private TextView tempBankTextView;

    private TextView permBankTextView;

    private int numberOfSelectedCombinations = 0;

    private int numberOfTurns = 1;

    private boolean theVeryFirstRun = true;

    private int goal = 5000;

    private int numberOfFarkles = 0;

    //this method gets called as soon as the app boots
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toast toast = Toast.makeText(getApplicationContext(), "Hello! Please press roll to start " +
                "your first turn", Toast.LENGTH_SHORT);
        toast.show();

        initializeAllViews();
        populateDrawableDiceArrayListWithPics();
        //we must pass down the context if we want to use toasts
        rollHandler.setContext(getApplicationContext());
    }

    //this gets invoked when the roll button get's pressed
    //it has to be public -- android limitation
    public void roll(View view) {
        //any die object that is selected must be now hidden RIGHT HERE
        hideSelectedDice();

        if (theVeryFirstRun) {
            Toast toast = Toast.makeText(getApplicationContext(), "Try selecting or unselecting a" +
                    " combination!", Toast.LENGTH_SHORT);
            toast.show();
            theVeryFirstRun = false;
        }

        if (rollHandler.isFreeRoll()) {
            rollHandler.resetThingsForFreeRoll();
        }
        unSelectAllCombinationButtons();
        combinationHandler.resetCurrentlyHoldingArray();
        //this takes care of rolling dice and displaying the 6 dice on the bottom
        rollHandler.handle();
        displayDice(rollHandler.getDiceArrayList());

        //as handle gets called the rollHandler generates available combinations which the
        // following method displays
        displayCombinations(rollHandler.getAnalyzer().getCombFinder()
                .getAvailableCombinations());
    }

    private static final ScheduledExecutorService worker = Executors.newSingleThreadScheduledExecutor();

    private void farkle() {
        numberOfFarkles++;
        if (numberOfFarkles == 3) {
            numberOfFarkles = 0;
            Toast toast;
            toast = Toast.makeText(getApplicationContext(),
                    "3 farkles in a row! -500 points", Toast
                            .LENGTH_SHORT);
            toast.show();
            permBank -= 500;
            tempBank = 0;
            permBankTextView.setText(permBank + "");
        }
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                resetEverything();
                updateAndDisplayNumberOfTurns();
            }
        }, 1500);
    }

    private void updateAndDisplayNumberOfTurns() {
        numberOfTurns++;
        String string = "Turn #";
        TextView textView = (TextView) findViewById(R.id.turnNumberTextView);
        textView.setText(string + numberOfTurns);
    }

    private void resetEverything() {
        Log.d(tag, "We are ressetting everything...");
        hideAllDice();
        hideAllCombs();
        for (int i = 0; i < Game.MAX_NUM_OF_DICE; i++) {
            rollHandler.getDiceArrayList().get(i).setSelected(false);
            rollHandler.getDiceArrayList().get(i).setUsed(true);
        }
        resetTempBank();
        rollHandler.setFirstRoll(true);
    }

    private void hideAllCombs() {
        for (int i = 0; i < combinationButtons.size(); i++) {
            combinationButtons.get(i).getButton().setVisibility(View.INVISIBLE);
            combinationButtons.get(i).setSelected(false);
            combinationButtons.get(i).getButton().setBackgroundResource(android.R.drawable
                    .button_onoff_indicator_off);
        }
    }

    private void resetTempBank() {
        combinationHandler.setTempBank(0);
        tempBankTextView.setText(0 + "");
    }

    private void hideAllDice() {
        for (int i = 0; i < Game.MAX_NUM_OF_DICE; i++) {
            diceImageViews.get(i).setVisibility(View.INVISIBLE);
        }
    }

    private void unSelectAllCombinationButtons() {
        numberOfSelectedCombinations = 0;
        for (int i = 0; i < Game.MAX_NUM_OF_COMBINATIONS_AT_A_TIME; i++) {
            combinationButtons.get(i).setSelected(false);
        }
    }

    private void hideSelectedDice() {
        for (int i = 0; i < Game.MAX_NUM_OF_DICE; i++) {
            if (rollHandler.getDiceArrayList().get(i).isSelected()) {
                diceImageViews.get(i).setVisibility(View.INVISIBLE);

                rollHandler.getDiceArrayList().get(i).setUsed(false);
            }
            combinationButtons.get(i).getButton().setBackgroundResource(android.R.drawable
                    .button_onoff_indicator_off);
        }

    }

    //all that it does is displayDice the proper image based on the value of the die
    private void displayDice(ArrayList<Die> diceList) {
        //if a die is still used and the user hasn't selected it we update the image
        for (int i = 0; i < 6; i++) {
            if (!diceList.get(i).isSelected() && diceList.get(i).isUsed()) {
                diceImageViews.get(i).setImageDrawable(drawableDiceArrayList.get(diceList.get(i).getValue() - 1));
                diceImageViews.get(i).setVisibility(View.VISIBLE);
            } else {
                diceImageViews.get(i).setVisibility(View.INVISIBLE);
            }
        }
    }

    //displays all combinations
    private void displayCombinations(ArrayList<Combination> combinations) {

        hideAllCombinations();

        if (combinations.size() != 0) {

            for (int i = 0; i < combinations.size(); i++) {
                displayOneCombination(combinations.get(i));
            }
        } else {
            final Toast toast = Toast.makeText(getApplicationContext(), "FARKLE! You lose points " +
                    "in your temporary bank.", Toast.LENGTH_SHORT);
            toast.show();
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    toast.cancel();
                }
            }, 1500);
            farkle();
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

    //this must be public so that the buttons would be clickable - Android limitation
    public void combinationButtonWasClicked(View buttonThatWasClicked) {

        int index = getIndexOfCombinationButton(buttonThatWasClicked);
        Combination combinationThatWasClicked = rollHandler.getAnalyzer().getCombFinder()
                .getAvailableCombinations().get(index);

        boolean theButtonIsAlreadySelected = combinationButtons.get(index).isSelected();

        Footprint maxCapacity = rollHandler.getAnalyzer().getFootprintOfTheCurrentHand();
        //we need to see if it was legal to select that combination. if not we display a message
        // saying that. It is  always legal to click on a selected button
        // (it is always legal to cancel your selection/unselect a combination, so we test
        // against that first). If it is not the case that the button has been selected, we have
        // a method that evaluates whether or not selecting this particular combination is legal

        if (combinationHandler.isLegalSelection
                (combinationThatWasClicked, maxCapacity, theButtonIsAlreadySelected)) {
            combinationHandler.setDiceArrayList(rollHandler.getDiceArrayList());
            updateCombinationButton(combinationButtons.get(index));
            combinationHandler.handle();
            displayTemporaryBank(combinationHandler.getTempBank());
            final Toast toast;
            if (theButtonIsAlreadySelected) {
                numberOfSelectedCombinations--;
                rollHandler.setNumberOfSelectedCombinations(numberOfSelectedCombinations);
                combinationButtons.get(index).setSelected(false);
                toast = Toast.makeText(getApplicationContext(),
                        "-" + combinationThatWasClicked.getPoints() + " points", Toast
                                .LENGTH_SHORT);
                toast.show();
            } else {
                numberOfSelectedCombinations++;
                rollHandler.setNumberOfSelectedCombinations(numberOfSelectedCombinations);
                combinationButtons.get(index).setSelected(true);
                toast = Toast.makeText(getApplicationContext(),
                        "+" + combinationThatWasClicked.getPoints() + " points", Toast
                                .LENGTH_SHORT);
                toast.show();
            }
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    toast.cancel();
                }
            }, 500);

            Log.d(tag, "The number of selected combinations is: " + numberOfSelectedCombinations);
            rollHandler.setNumberOfSelectedCombinations(numberOfSelectedCombinations);
            if (rollHandler.isFreeRoll()) {
                Toast toast2 = Toast.makeText(getApplicationContext(), "Free roll! You may press " +
                        "ROLL now", Toast.LENGTH_SHORT);
                toast2.show();
            }
        } else {
            Toast toast = Toast.makeText(getApplicationContext(), "You have to unselect an " +
                    "interfering combination first!", Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    private void updateCombinationButton(CombinationButton combinationButton) {
        //this is only called if it was legal to select something so we visually select or
        // deselct it
        if (!combinationButton.isSelected()) {
            //we visibly show the button as green
            combinationButton.getButton().setBackgroundResource(android.R.drawable.button_onoff_indicator_on);
            //we edit the corresponding back end boolean to be true
            combinationButton.setSelected(true);
        } else {
            //we visibly change the color back to gray
            combinationButton.getButton().setBackgroundResource(android.R.drawable
                    .button_onoff_indicator_off);
            //we edit the corresponding back end boolean to be true
            combinationButton.setSelected(false);
        }
    }

    private int getIndexOfCombinationButton(View buttonThatWasClicked) {
        //once the user clicks on an availble combination - we need to figure out which one he
        // pressed on
        //we compare the id of the button that he clicked with all buttons and that was know the
        // index of the button that was clicked.
        int indexOfButtonPressed = -1;
        for (int i = 0; i < Game.MAX_NUM_OF_COMBINATIONS_AT_A_TIME; i++) {
            if (buttonThatWasClicked.getId() == iDsForCombinationsButtons.get(i)) {
                indexOfButtonPressed = i;
                break;
            }
        }
        return indexOfButtonPressed;
    }

    private void displayTemporaryBank(int moneys) {
        tempBankTextView.setText(moneys + "");
    }

    //bank button handling begins here
    public void bank(View view) {
        if (combinationHandler.getTempBank() >= 300) {
            Toast toast2 = Toast.makeText(getApplicationContext(), "You earned " +
                    combinationHandler.getTempBank() + " points!", Toast.LENGTH_SHORT);
            toast2.show();
            updatePermBank();
            resetEverything();
            if (!youWon) {
                updateAndDisplayNumberOfTurns();
            } else {
                Toast toast = Toast.makeText(getApplicationContext(), "Congratulations! You won " +
                        "in " + numberOfTurns + " turns!", Toast.LENGTH_SHORT);
                toast.show();

                Button roll = (Button) findViewById(R.id.rollButton);
                Button bank = (Button) findViewById(R.id.bankButton);
                roll.setEnabled(false);
                bank.setEnabled(false);
            }
        } else {
            Toast toast = Toast.makeText(getApplicationContext(), "You must accumulate 300 " +
                    "points before you can bank!", Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    private boolean youWon = false;

    private void updatePermBank() {
        permBank += combinationHandler.getTempBank();
        permBankTextView.setText(permBank + "");
        if (permBank >= goal) {
            youWon = true;
        }
    }

    //there are certain things that need to be initialized as the app starts, this method takes
    // care of all of them
    private void initializeAllViews() {
        populateDiceImageViews();
        populateCombinationButtons();
        setButtonsStyle();
        populateIDsForCombinationsButtons();
        tempBankTextView = (TextView) findViewById(R.id.tempBankTextView);
        permBankTextView = (TextView) findViewById(R.id.permBankTextView);
        TextView goalTextView = (TextView) findViewById(R.id.goalTextView);
        goalTextView.setText("Goal: " + goal + " points");
    }

    //this function creates ImageView objects and ties them to the layout Views
    //we have declared the diceImageViews array up above, and this method simply populates it
    // with the references to the views that will later display various dice
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

    // default buttons don't have the on-off functionality/inteface, so i am using this library and
    // all my buttons will look different from the default ones
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

    //simply storing the references to the comb button views in an array
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