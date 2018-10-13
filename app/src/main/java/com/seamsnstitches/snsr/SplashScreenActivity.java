package com.seamsnstitches.snsr;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import com.seamsnstitches.snsr.utility.SNSR_SharedPreferences.LoginStatusPreference;

import butterknife.ButterKnife;

/**
 * Created by Oto-obong on 17/01/2018.
 */

public class SplashScreenActivity extends AppCompatActivity {

    /**
     * Duration of wait
     **/
    private final int SPLASH_DISPLAY_LENGTH = 4000;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        ButterKnife.bind(this);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                /* Create an Intent that will start the Menu-Activity. */

                LoginStatusPreference.initLoginStatusPreference(getApplicationContext());

                //SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences(AppConstants.SHARED_PREFERENCE_NAME, 0); // 0 - for private mode

                //if (sharedPreferences.getBoolean(AppConstants.IS_LOGGED_IN, false)) {
                if (LoginStatusPreference.getIsLoggedIn()) {

                    Intent homeIntent = new Intent(SplashScreenActivity.this, HomeActivity.class);

                    SplashScreenActivity.this.startActivity(homeIntent);

                    SplashScreenActivity.this.finish();

                } else {

                    Intent loginIntent = new Intent(SplashScreenActivity.this, LoginActivity.class);

                    SplashScreenActivity.this.startActivity(loginIntent);

                    SplashScreenActivity.this.finish();

                }


            }
        }, SPLASH_DISPLAY_LENGTH);

    }
}
