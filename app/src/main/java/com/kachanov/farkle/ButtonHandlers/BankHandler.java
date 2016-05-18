package com.kachanov.farkle.ButtonHandlers;

import android.util.Log;

/**
 * Created by Mac on 4/27/16.
 */
public class BankHandler {
    private static String tag = "BankHandler";
    private Integer tempBank;

    public BankHandler(Integer tempBank) {
        this.tempBank = tempBank;
    }

    public boolean isLegalToBank() {
        Log.d(tag, "Hello, you have " + tempBank + " moneys");
        if (tempBank >= 300) {
            return true;
        } else {
            return false;
        }
    }
}
