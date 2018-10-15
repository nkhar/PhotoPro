package com.droiddwarf.photomodifix.utils;

import android.content.Context;
import android.graphics.Color;
import android.preference.PreferenceManager;
import android.support.annotation.ColorInt;

public class Prefs {

    private Prefs() {
    }

    public static boolean stackHorizontally(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context)
                .getBoolean("stack_horizontally", true);
    }

    public static void stackHorizontally(Context context, boolean newValue) {
        PreferenceManager.getDefaultSharedPreferences(context).edit()
                .putBoolean("stack_horizontally", newValue).commit();
    }

    @ColorInt
    public static int bgFillColor(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context)
                .getInt("bg_fill_color", Color.parseColor("#212121"));
    }

    public static void bgFillColor(Context context, @ColorInt int newValue) {
        PreferenceManager.getDefaultSharedPreferences(context).edit()
                .putInt("bg_fill_color", newValue).commit();
    }

}
