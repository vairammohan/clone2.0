package com.kalvi.elaptopsale;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by kalvi on 2/16/2018.
 */

public class Message {
    public static void message(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }
}
