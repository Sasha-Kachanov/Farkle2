//package com.kachanov.farkle.ButtonHandlers;
//
//import android.view.View;
//import android.widget.Button;
//
//import com.kachanov.farkle.Helpers.Combination;
//import com.kachanov.farkle.R;
//
///**
// * Created by Mac on 4/27/16.
// */
//public class CombHandler {
//
//    //handlng of the pressing of combination buttons starts here
//    //note about combXWasClicked - a better implementation would be to have all buttons call the same method when they are clicked
//    //and then the method identifies which one was clicked. but i don't know how to implement that
//    //so instead i will have 5 methods that could be called.
//    public void combXWasClicked(View view) {
//        Combination combination;
//        if (view.getId() == R.id.comb0) {
//            combination = combs.get(0);
//        } else if (view.getId() == R.id.comb1) {
//            combination = combs.get(1);
//        } else if (view.getId() == R.id.comb2) {
//            combination = combs.get(2);
//        } else if (view.getId() == R.id.comb3) {
//            combination = combs.get(3);
//        } else /*if (view.getId() == R.id.comb4)*/ {            //i guess you don't really need the last if here
//            combination = combs.get(4);
//        }
//
//
//
//        //was it legal to click it? if not then show toast if yes then process with this function
//
//        //was it checked or unchecked?  -- doesn't really matter i guess
//
//        //this just changes the color of a button that was clicked
//        //it seems a little wasteful doing the tests both here and in changeColorOfSingleButton
//        //maybe if they were in an array you could just pass the int which will be the index.
//        //or change the color within this function
//        if (view.getId() == R.id.comb0) {
//            //change the color
//            changeColorOfSingleButton(comb0Button);
//        } else if (view.getId() == R.id.comb1) {
//            //change the color
//            changeColorOfSingleButton(comb1Button);
//        } else if (view.getId() == R.id.comb2) {
//            //change the color
//            changeColorOfSingleButton(comb2Button);
//        } else if (view.getId() == R.id.comb3) {
//            //change the color
//            changeColorOfSingleButton(comb3Button);
//        } else /*if (view.getId() == R.id.comb4)*/ {            //i guess you don't really need the last if here
//            //change the color
//            changeColorOfSingleButton(comb4Button);
//        }
//    }
//
//
//    private void changeColorOfSingleButton(Button button) {
//        if (button.getId() == R.id.comb0) {
//            if (!comb0ButtonSelected) {
//                button.setBackgroundResource(android.R.drawable.button_onoff_indicator_on);
//                comb0ButtonSelected = true;
//            } else {
//                comb0Button.setBackgroundResource(android.R.drawable.button_onoff_indicator_off);
//                comb0ButtonSelected = false;
//            }
//        } else if (button.getId() == R.id.comb1) {
//            if (!comb1ButtonSelected) {
//                button.setBackgroundResource(android.R.drawable.button_onoff_indicator_on);
//                comb1ButtonSelected = true;
//            } else {
//                comb1Button.setBackgroundResource(android.R.drawable.button_onoff_indicator_off);
//                comb1ButtonSelected = false;
//            }
//        } else if (button.getId() == R.id.comb2) {
//            if (!comb2ButtonSelected) {
//                button.setBackgroundResource(android.R.drawable.button_onoff_indicator_on);
//                comb2ButtonSelected = true;
//            } else {
//                comb2Button.setBackgroundResource(android.R.drawable.button_onoff_indicator_off);
//                comb2ButtonSelected = false;
//            }
//        } else if (button.getId() == R.id.comb3) {
//            if (!comb3ButtonSelected) {
//                button.setBackgroundResource(android.R.drawable.button_onoff_indicator_on);
//                comb3ButtonSelected = true;
//            } else {
//                comb3Button.setBackgroundResource(android.R.drawable.button_onoff_indicator_off);
//                comb3ButtonSelected = false;
//            }
//        } else /*if (button.getId() == R.id.comb4) */ {
//            if (!comb4ButtonSelected) {
//                button.setBackgroundResource(android.R.drawable.button_onoff_indicator_on);
//                comb4ButtonSelected = true;
//            } else {
//                comb4Button.setBackgroundResource(android.R.drawable.button_onoff_indicator_off);
//                comb4ButtonSelected = false;
//            }
//        }
//
//    }
//    // handlng of the pressing of combination buttons ends here
//
//}
