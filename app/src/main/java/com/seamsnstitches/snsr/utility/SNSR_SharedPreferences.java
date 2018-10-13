package com.seamsnstitches.snsr.utility;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class SNSR_SharedPreferences {


    /**
     * This class handles the editing and checking to see if user is logged in or not
     */
    public static class LoginStatusPreference {
        public final static String LOG_IN_STATUS = "login-status";
        public final static String IS_LOGGED_IN = "logged_in";
        public final static String IS_LOGGED_OUT = "logged_out";
        private static Context mContext;
        private static SharedPreferences mSharedPreferences;

        public static void initLoginStatusPreference(Context context) {
            mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
            mContext = context;
        }

        public static boolean getIsLoggedIn() {
            return mSharedPreferences.getBoolean(LOG_IN_STATUS, false);
        }

        public static void setIsLoggedIn(boolean isLoggedIn) {
            SharedPreferences.Editor editor = mSharedPreferences.edit();
            editor.putBoolean(LOG_IN_STATUS, isLoggedIn);
            editor.apply();
        }
    }
}
