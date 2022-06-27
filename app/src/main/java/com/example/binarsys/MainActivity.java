package com.example.binarsys;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    EditText typeText;
    TextView reslText;
    ImageView getBinaryCodeText, getCopyText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //getActionBar().setDisplayShowTitleEnabled(false);
        //getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setTitle("Binary Converter");

        typeText = findViewById(R.id.typeText);
        reslText = findViewById(R.id.reslText);
        getBinaryCodeText = findViewById(R.id.getBinTex);
      //  getCopyText = findViewById(R.id.getCopyText);

        String recivedText = String.valueOf(typeText.getText());
       // reslText.setText(recivedText);

        int[] symbols = new int[recivedText.length()];
        int[] ostatki;
        String bin_sms = "";

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
            }
            bin_sms+=" ";
        }

//aaaaa

        String finalBin_sms = bin_sms;
        System.out.println(recivedText + " " + finalBin_sms);
        getBinaryCodeText.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("MissingPermission")
            @Override
            public void onClick(View v) {

                reslText.setText(finalBin_sms);
            }
        });

    }
}