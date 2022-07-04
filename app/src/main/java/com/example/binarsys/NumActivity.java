package com.example.binarsys;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class NumActivity extends AppCompatActivity {

    EditText typeText;
    TextView reslText;
    ImageView getNumber, getCopy, binFloder, mainFloder;
    String bin_copy_text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_num);

        getSupportActionBar().setTitle("Binary Converter");

        binFloder = findViewById(R.id.binF);
        mainFloder = findViewById(R.id.textFl);

        getNumber = findViewById(R.id.getBinTex);
        getCopy = findViewById(R.id.getCopyBc);

        typeText = (EditText)findViewById(R.id.typeText);
        reslText = findViewById(R.id.reslText);
        reslText.setMovementMethod(new ScrollingMovementMethod());

        getNumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Vibrator v1 = (Vibrator) NumActivity.this.getSystemService(Context.VIBRATOR_SERVICE);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                    v1.vibrate(VibrationEffect.createOneShot(100, 1));
                }

                v.startAnimation(AnimationUtils.loadAnimation(NumActivity.this, R.anim.anim));

                String test = "Get your binary code";
                String recivedNum = typeText.getText().toString();

                if(recivedNum.length() == 0){
                    reslText.setText("You need to type a number!");
                    reslText.setTextColor(Color.parseColor("#457550"));
                    reslText.setTextSize(20);
                }else {
                    converting(recivedNum);
                }
                // Toast.makeText(MainActivity.this, test, Toast.LENGTH_SHORT).show();
            }
        });

        getCopy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Vibrator v1 = (Vibrator) NumActivity.this.getSystemService(Context.VIBRATOR_SERVICE);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                    v1.vibrate(VibrationEffect.createOneShot(100, 1));
                }

                v.startAnimation(AnimationUtils.loadAnimation(NumActivity.this, R.anim.anim));

                if(typeText.getText().length() == 0){
                    reslText.setText("You need to type a number!");
                    reslText.setTextColor(Color.parseColor("#457550"));
                    reslText.setTextSize(20);
                    return;
                }
                if (typeText.getText().length() > 0 ){
                    if (reslText.getText().toString().equals("You need to type a number!") || reslText.getText().toString().equals("Here will be binary code!")){
                        reslText.setText("You need to tap button: \"Get Binary code\"");
                        reslText.setTextColor(Color.parseColor("#457550"));
                        reslText.setTextSize(20);
                        return;
                    }else{
                        ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                        ClipData clip = ClipData.newPlainText("Copied", bin_copy_text);
                        clipboard.setPrimaryClip(clip);
                        Toast.makeText(NumActivity.this, "Copied", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        mainFloder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NumActivity.this, MainActivity.class);

                Vibrator v1 = (Vibrator) NumActivity.this.getSystemService(Context.VIBRATOR_SERVICE);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                    v1.vibrate(VibrationEffect.createOneShot(100, 1));
                }

                v.startAnimation(AnimationUtils.loadAnimation(NumActivity.this, R.anim.anim));

                startActivity(intent);
                overridePendingTransition(0, 0);
            }
        });

        binFloder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NumActivity.this, BinActivity.class);

                Vibrator v1 = (Vibrator) NumActivity.this.getSystemService(Context.VIBRATOR_SERVICE);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                    v1.vibrate(VibrationEffect.createOneShot(100, 1));
                }

                v.startAnimation(AnimationUtils.loadAnimation(NumActivity.this, R.anim.anim));

                startActivity(intent);
                overridePendingTransition(0, 0);
            }
        });

    }

    @SuppressLint("ResourceAsColor")
    private void converting(String recivedNum) {
        int[] ostatki;
        String bin_sms = "";
        int resived = Integer.parseInt(recivedNum);
        int count = 0;
        ostatki = new int[100];
        bin_sms += " ";

        for (int i = 0; i < ostatki.length; i++) {
            ostatki[i] = resived % 2;
            resived /= 2;


            if (resived <= 0) break;
                count++;
            }
            if (count < 7) {
                count=7;
            }

            for (int i = count; i >= 0; i--) {
                bin_sms += ostatki[i];
            }

        String finalBin_sms = bin_sms;
        bin_copy_text = bin_sms;
        reslText.setText(finalBin_sms);
        reslText.setTextColor(R.color.black);
        reslText.setTextSize(15);
    }

}