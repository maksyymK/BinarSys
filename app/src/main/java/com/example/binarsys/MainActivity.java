package com.example.binarsys;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ActivityOptions;
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
import android.util.Pair;
import android.view.Gravity;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText typeText;
    TextView reslText;
    ImageView getBinaryCodeText, getCopyText, numFloder, binFloder;
    String bin_copy_text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //getActionBar().setDisplayShowTitleEnabled(false);
        //getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setTitle("Binary Converter");

        typeText = (EditText)findViewById(R.id.typeText);
        reslText = findViewById(R.id.reslText);
        reslText.setMovementMethod(new ScrollingMovementMethod());

        getBinaryCodeText = findViewById(R.id.getBinTex);
        getCopyText = findViewById(R.id.getCopyBc);

        numFloder = findViewById(R.id.numF);
        binFloder = findViewById(R.id.binF);

        getBinaryCodeText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Vibrator v1 = (Vibrator) MainActivity.this.getSystemService(Context.VIBRATOR_SERVICE);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                    v1.vibrate(VibrationEffect.createOneShot(100, 1));
                }

                v.startAnimation(AnimationUtils.loadAnimation(MainActivity.this, R.anim.anim));

                String test = "Get your binary code";
                String recivedText = typeText.getText().toString();

                if(recivedText.length() == 0){
                    reslText.setText("You need to type the text!");
                    reslText.setTextColor(Color.parseColor("#457550"));
                    reslText.setTextSize(20);
                }else {
                    converting(recivedText);
                }
               // Toast.makeText(MainActivity.this, test, Toast.LENGTH_SHORT).show();
            }
        });

        getCopyText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Vibrator v1 = (Vibrator) MainActivity.this.getSystemService(Context.VIBRATOR_SERVICE);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                    v1.vibrate(VibrationEffect.createOneShot(100, 1));
                }

                v.startAnimation(AnimationUtils.loadAnimation(MainActivity.this, R.anim.anim));

                if(typeText.getText().length() == 0){
                    reslText.setText("You need to type the text!");
                    reslText.setTextColor(Color.parseColor("#457550"));
                    reslText.setTextSize(20);
                    return;
                }
                if (typeText.getText().length() > 0 ){
                    if (reslText.getText().toString().equals("You need to type the text!") || reslText.getText().toString().equals("Here will be binary code!")){
                        reslText.setText("You need to tap button: \"Get Binary code\"");
                        reslText.setTextColor(Color.parseColor("#457550"));
                        reslText.setTextSize(20);
                        return;
                    }else{
                        ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                        ClipData clip = ClipData.newPlainText("Copied", bin_copy_text);
                        clipboard.setPrimaryClip(clip);
                        Toast.makeText(MainActivity.this, "Copied", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        binFloder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, BinActivity.class);

                Vibrator v1 = (Vibrator) MainActivity.this.getSystemService(Context.VIBRATOR_SERVICE);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                    v1.vibrate(VibrationEffect.createOneShot(100, 1));
                }

                v.startAnimation(AnimationUtils.loadAnimation(MainActivity.this, R.anim.anim));

                startActivity(intent);
                overridePendingTransition(0, 0);
            }
        });

        numFloder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, NumActivity.class);

                Vibrator v1 = (Vibrator) MainActivity.this.getSystemService(Context.VIBRATOR_SERVICE);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                    v1.vibrate(VibrationEffect.createOneShot(100, 1));
                }

                v.startAnimation(AnimationUtils.loadAnimation(MainActivity.this, R.anim.anim));

                startActivity(intent);
                overridePendingTransition(0, 0);
            }
        });

    }

    @SuppressLint("ResourceAsColor")
    private void converting(String recivedText) {
        int[] symbols = new int[recivedText.length()];
        int[] ostatki;
        String bin_sms = "";
        String bin_copy = "";

        for (int i = 0; i < recivedText.length(); i++) {
            if (recivedText.indexOf(" ") == i){
                symbols[i] = 32;
            }else{
                symbols[i] = recivedText.charAt(i);
            }
            //System.out.println(symbols[i]);
        }

        int symbol;
        int count;
        for (int j = 0; j < symbols.length; j++) {
            symbol = symbols[j];
            ostatki = new int[100];
            count = 0;
            bin_sms += " ";
            for (int i = 0; i < ostatki.length; i++) {
                ostatki[i] = symbol % 2;
                symbol /= 2;

                if (symbol <= 0) break;
                count++;
            }
            if (count < 7) {
                count=7;
            }

            for (int i = count; i >= 0; i--) {
                bin_sms += ostatki[i];
                bin_copy += ostatki[i];
            }

        }

        String finalBin_sms = bin_sms;
        bin_copy_text = bin_copy;
        reslText.setText(finalBin_sms);
        reslText.setTextColor(R.color.black);
        reslText.setTextSize(15);
    }

}