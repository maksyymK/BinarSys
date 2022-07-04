package com.example.binarsys;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class BinActivity extends AppCompatActivity {

    ImageView getText, getNumber, getCopy, numFloder, mainFloder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bin);
        getSupportActionBar().setTitle("Binary Converter");

        numFloder = findViewById(R.id.numF);
        mainFloder = findViewById(R.id.textFl);

        mainFloder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BinActivity.this, MainActivity.class);

                Vibrator v1 = (Vibrator) BinActivity.this.getSystemService(Context.VIBRATOR_SERVICE);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                    v1.vibrate(VibrationEffect.createOneShot(100, 1));
                }

                v.startAnimation(AnimationUtils.loadAnimation(BinActivity.this, R.anim.anim));

                startActivity(intent);
                overridePendingTransition(0, 0);
            }
        });

        numFloder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BinActivity.this, NumActivity.class);

                Vibrator v1 = (Vibrator) BinActivity.this.getSystemService(Context.VIBRATOR_SERVICE);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                    v1.vibrate(VibrationEffect.createOneShot(100, 1));
                }

                v.startAnimation(AnimationUtils.loadAnimation(BinActivity.this, R.anim.anim));

                startActivity(intent);
                overridePendingTransition(0, 0);
            }
        });

    }
}