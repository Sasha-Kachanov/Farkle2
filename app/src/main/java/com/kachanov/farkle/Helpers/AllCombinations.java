package com.kachanov.farkle.Helpers;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Mac on 3/15/16.
 */
public class AllCombinations {

    public static Combination run = new Combination("Run", 1500, new ArrayList<Integer>(Arrays.asList(new Integer[]{0, 1, 1, 1, 1, 1, 1})));
//HOW DO I DO THREE PAIRS!? a very inefficient way is of course storing ALL variations of three pairs
    //alternatively we simply test for arr[i]==2 and we count how many times thats the case and if its three then we have the thing. but that would not
    //be in this class it would be in main when inside testfor6dice function

    public static Combination singleOne = new Combination("Single one", 100, new ArrayList<Integer>(Arrays.asList(new Integer[]{0, 1, 0, 0, 0, 0, 0})));
    public static Combination singleFive = new Combination("Single five", 50, new ArrayList<Integer>(Arrays.asList(new Integer[]{0, 0, 0, 0, 0, 1, 0})));

    public static Combination twoOnes = new Combination("Two ones", 200, new ArrayList<Integer>(Arrays.asList(new Integer[]{0, 2, 0, 0, 0, 0, 0})));
    public static Combination twoFives = new Combination("Two fives", 100, new ArrayList<Integer>(Arrays.asList(new Integer[]{0, 0, 0, 0, 0, 2, 0})));

    public static Combination threeOnes = new Combination("Three ones", 1000, new ArrayList<Integer>(Arrays.asList(new Integer[]{0, 3, 0, 0, 0, 0, 0})));
    public static Combination fourOnes = new Combination("Four ones", 2000, new ArrayList<Integer>(Arrays.asList(new Integer[]{0, 4, 0, 0, 0, 0, 0})));
    public static Combination fiveOnes = new Combination("Five ones", 4000, new ArrayList<Integer>(Arrays.asList(new Integer[]{0, 5, 0, 0, 0, 0, 0})));
    public static Combination sixOnes = new Combination("Six ones", 8000, new ArrayList<Integer>(Arrays.asList(new Integer[]{0, 6, 0, 0, 0, 0, 0})));

    public static Combination threeTwos = new Combination("Three twos", 200, new ArrayList<Integer>(Arrays.asList(new Integer[]{0, 0, 3, 0, 0, 0, 0})));
    public static Combination fourTwos = new Combination("Four twos", 400, new ArrayList<Integer>(Arrays.asList(new Integer[]{0, 0, 4, 0, 0, 0, 0})));
    public static Combination fiveTwos = new Combination("Five twos", 800, new ArrayList<Integer>(Arrays.asList(new Integer[]{0, 0, 5, 0, 0, 0, 0})));
    public static Combination sixTwos = new Combination("Six twos", 1600, new ArrayList<Integer>(Arrays.asList(new Integer[]{0, 0, 6, 0, 0, 0, 0})));

    public static Combination threeThrees = new Combination("Three threes", 300, new ArrayList<Integer>(Arrays.asList(new Integer[]{0, 0, 0, 3, 0, 0, 0})));
    public static Combination fourThrees = new Combination("Four threes", 600, new ArrayList<Integer>(Arrays.asList(new Integer[]{0, 0, 0, 4, 0, 0, 0})));
    public static Combination fiveThrees = new Combination("Five threes", 1200, new ArrayList<Integer>(Arrays.asList(new Integer[]{0, 0, 0, 5, 0, 0, 0})));
    public static Combination sixThrees = new Combination("Six threes", 2400, new ArrayList<Integer>(Arrays.asList(new Integer[]{0, 0, 0, 6, 0, 0, 0})));

    public static Combination threeFours = new Combination("Three fours", 400, new ArrayList<Integer>(Arrays.asList(new Integer[]{0, 0, 0, 0, 3, 0, 0})));
    public static Combination fourFours = new Combination("Four fours", 800, new ArrayList<Integer>(Arrays.asList(new Integer[]{0, 0, 0, 0, 4, 0, 0})));
    public static Combination fiveFours = new Combination("Five fours", 1600, new ArrayList<Integer>(Arrays.asList(new Integer[]{0, 0, 0, 0, 5, 0, 0})));
    public static Combination sixFours = new Combination("Six fours", 3200, new ArrayList<Integer>(Arrays.asList(new Integer[]{0, 0, 0, 0, 6, 0, 0})));

    public static Combination threeFives = new Combination("Three fives", 500, new ArrayList<Integer>(Arrays.asList(new Integer[]{0, 0, 0, 0, 0, 3, 0})));
    public static Combination fourFives = new Combination("Four fives", 1000, new ArrayList<Integer>(Arrays.asList(new Integer[]{0, 0, 0, 0, 0, 4, 0})));
    public static Combination fiveFives = new Combination("Five fives", 2000, new ArrayList<Integer>(Arrays.asList(new Integer[]{0, 0, 0, 0, 0, 5, 0})));
    public static Combination sixFives = new Combination("Six fives", 4000, new ArrayList<Integer>(Arrays.asList(new Integer[]{0, 0, 0, 0, 0, 6, 0})));

    public static Combination threeSixes = new Combination("Three sixes", 600, new ArrayList<Integer>(Arrays.asList(new Integer[]{0, 0, 0, 0, 0, 0, 3})));
    public static Combination fourSixes = new Combination("Four sixes", 1200, new ArrayList<Integer>(Arrays.asList(new Integer[]{0, 0, 0, 0, 0, 0, 4})));
    public static Combination fiveSixes = new Combination("Five sixes", 2400, new ArrayList<Integer>(Arrays.asList(new Integer[]{0, 0, 0, 0, 0, 0, 5})));
    public static Combination sixSixes = new Combination("Six sixes", 4800, new ArrayList<Integer>(Arrays.asList(new Integer[]{0, 0, 0, 0, 0, 0, 6})));

    public static Combination nothing = new Combination("Nothing!", 500, new ArrayList<Integer>(Arrays.asList(new Integer[]{0, 0, 0, 0, 0, 0, 0})));
}
