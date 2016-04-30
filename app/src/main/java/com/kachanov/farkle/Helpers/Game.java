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



    public static ArrayList<Integer> sortedDices = new ArrayList<>(Arrays.asList(new Integer(0), new Integer(0), new Integer(0), new Integer(0), new Integer(0), new Integer(0), new Integer(0)));     //note there are 7 objects                     // this is your hand but it is sorted i.e., the footprint of your hand
    public static ArrayList<Combination> combs = new ArrayList<>(Arrays.asList(new Combination(), new Combination(), new Combination(), new Combination(), new Combination()));                            //i was going to populate this with the combinations that are present. based on this member the comb button would get populated
    public static int activeDice = 6;                      //must be really carefull with this - I don't know how often will it decide to reinitialize this to 6
    public static boolean testingMode;

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
