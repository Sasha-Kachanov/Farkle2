package com.kachanov.farkle;

import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.kachanov.farkle.ButtonHandlers.RollHandler;
import com.kachanov.farkle.Helpers.Combination;
import com.kachanov.farkle.Helpers.Die;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private static int activeDice=6;

    public static int getActiveDice() {
        return activeDice;
    }

    public static void setActiveDice(int activeDice) {
        MainActivity.activeDice = activeDice;
    }

    //this object will handle everything when roll is pressed
    RollHandler rollHandler = new RollHandler();

    //tag that is used for debugging
    private static String tag = "MainActivity: ";

    //holds the ImageViews of the dice
    private ArrayList<ImageView> diceImageViews = new ArrayList<>();

    //holds the available combinations buttons
    private ArrayList<Button> combinationButtons = new ArrayList<>();

    private ArrayList<Drawable> drawableDiceArrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        d("onCreate was called");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializeAllViews();
        populateDrawableDiceArrayListWithPics();

    }

    //this gets invoked when the roll button get's pressed
    //it has to be public -- android limitation
    public void roll(View view) {
        //any die object that is selected must be now hidden RIGHT HERE

        rollHandler.setContext(getApplicationContext());
        rollHandler.handle();
        displayDice(rollHandler.getDiceArrayList());

        displayCombinations(rollHandler.getAnalyzer().getCombinationFinder().getAvailableCombinations());
    }

    //all that it does is displayDice the proper image based on the value of the die
    private void displayDice(ArrayList<Die> list) {
        //if a die is still used and the user hasn't selected it we update the image
        for (int i = 0; i < 6; i++) {
            if (list.get(i).isUsed() && !list.get(i).isSelected()) {
                diceImageViews.get(i).setImageDrawable(drawableDiceArrayList.get(list.get(i).getValue() - 1));
                diceImageViews.get(i).setVisibility(View.VISIBLE);
            }
        }
    }

    private void displayCombinations(ArrayList<Combination> combinations) {
        hideAllCombinations();

    }

    private void initializeAllViews() {
        d("initializeAllViews() was called");
        populateDiceImageViews();
        populateCombinationButtons();
        setButtonsStyle();
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
            combinationButtons.add((Button) findViewById(R.id.comb0));
            combinationButtons.add((Button) findViewById(R.id.comb1));
            combinationButtons.add((Button) findViewById(R.id.comb2));
            combinationButtons.add((Button) findViewById(R.id.comb3));
            combinationButtons.add((Button) findViewById(R.id.comb4));
            combinationButtons.add((Button) findViewById(R.id.comb5));
        }
    }


    private void setButtonsStyle() {
        d("setButtonsStyle() was called");
        for (int i = 0; i < 6; i++) {
            combinationButtons.get(i).setBackgroundResource(android.R.drawable.button_onoff_indicator_off);
        }
        Button bankButton = (Button) findViewById(R.id.bankButton);
        bankButton.setBackgroundResource(android.R.drawable.button_onoff_indicator_off);
        Button rollButton = (Button) findViewById((R.id.rollButton));
        rollButton.setBackgroundResource(android.R.drawable.button_onoff_indicator_off);
    }

    //simply hides the buttons that displayDice combinations
    private void hideAllCombinations() {
        for (int i = 0; i < 6; i++) {
            combinationButtons.get(i).setVisibility(View.INVISIBLE);
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

}

//OKAY SO I DO VERY MUCH NEED THE DEFINITIONS OF WHAT EACH COMBINATIONS USES. IF RUN IS AVAILABLE GO AND FIND THAT ONE AND SELECT IT. GO AND FIND THAT TWO
//AND SELECT IT. ETC. IF AS YOU DO IT ONE OF THEM IS ALREADY SELECTED THEN YOU DISPLAY A TOAST
//you could make a hand class and have a raw hand in it (dies) and a footprint hand (sorteddices)
