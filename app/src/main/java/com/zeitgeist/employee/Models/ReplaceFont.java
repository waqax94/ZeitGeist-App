package com.zeitgeist.employee.Models;

import android.content.Context;
import android.graphics.Typeface;
import android.icu.text.DateFormat;

import java.lang.reflect.Field;

/**
 * Created by User on 8/23/2017.
 */

public class ReplaceFont {

    public static void replaceFont(Context context,String oldFont,String newFont){
        Typeface customFont = Typeface.createFromAsset(context.getAssets(),newFont);
        replaceFont(oldFont,customFont);
    }

    private static void replaceFont(String oldFont, Typeface customFont) {
        try {
            Field myField = Typeface.class.getDeclaredField(oldFont);
            myField.setAccessible(true);
            myField.set(null,customFont);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
