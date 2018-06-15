package com.valle00018316.gamenews;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    private static int SPLASH_TIME_OUT=4000;
    String access;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SharedPreferences sharedPreferences=this.getSharedPreferences("logbait",MODE_PRIVATE);

        if(sharedPreferences.contains("logbait")){
            Intent homeIntent= new Intent(MainActivity.this, HomeActivity.class);
            startActivity(homeIntent);
            finish();
        }

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                Intent homeIntent= new Intent(MainActivity.this, LoginActivity.class);
                startActivity(homeIntent);
                finish();
            }
        }, SPLASH_TIME_OUT);

    }


}
