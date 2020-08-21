package com.furmandevs.myapplicationn;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.RelativeLayout;


public class MainActivity extends AppCompatActivity {

    Intent moveAcIntent;
    RelativeLayout relativeSplash;
    Animation animation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        relativeSplash = findViewById(R.id.relativeSplash);


        Thread background = new Thread() {
            public void run() {
                try {
                    animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_in);
                    relativeSplash.startAnimation(animation);
                    sleep(1*1000);
                    moveAcIntent = new Intent(MainActivity.this, MenuActivity.class);
                    startActivity(moveAcIntent);
                    overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                    finish();
                }
                catch (Exception e) { }
            }
        };
        background.start();

    }
}
