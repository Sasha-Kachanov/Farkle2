package com.kachanov.farkle.Helpers;

import android.widget.Button;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Mac on 4/27/16.
 */
public class Game {
    public static final int MAX_NUM_OF_DICE = 6;
    public static final int MAX_NUM_OF_COMBINATIONS_AT_A_TIME = 6;

    //YOU NEED AN ARRAY OF WHAT YOU ARE HOLDING CURRENTLY. IE FOOTPRINT ON HAND. THAT ARRAY MAY NEVER OVERFLOW THE ACTUAL HANDFOOTPRINT ARRAY

    Button comb0Button;     //you can shove these in an array.
    Button comb1Button;     //you can actually make a class that will have 2 memebrs - button and boolean and that will take care of this bs
    Button comb2Button;
    Button comb3Button;
    Button comb4Button;
    Button comb5Button;

    boolean comb0ButtonSelected = false;
    boolean comb1ButtonSelected = false;
    boolean comb2ButtonSelected = false;
    boolean comb3ButtonSelected = false;
    boolean comb4ButtonSelected = false;
    boolean comb5ButtonSelected = false;
}
