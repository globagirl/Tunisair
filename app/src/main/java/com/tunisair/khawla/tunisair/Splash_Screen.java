package com.tunisair.khawla.tunisair;

import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class Splash_Screen extends AppCompatActivity {
public static int SPLASH_TIME_OUT=3000;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
       Animation  animFadeIn;

        ImageView logo = (ImageView) findViewById(R.id.splash);


        animFadeIn=AnimationUtils.loadAnimation(this, R.anim.translate);

        logo.setAnimation(animFadeIn);
        new Handler().postDelayed(new Runnable() {


            /*
             * Showing splash screen with a timer. This will be useful when you
             * want to show case your app logo / company
             */

            @Override
            public void run() {


                // This method will be executed once the timer is over
                // Start your app main activity
                Intent i = new Intent(Splash_Screen.this, LoginActivity.class);
                startActivity(i);

                // close this activity
                finish();
            }
        }, SPLASH_TIME_OUT);
    }
    }




