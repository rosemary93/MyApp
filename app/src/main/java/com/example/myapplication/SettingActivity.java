package com.example.myapplication;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;

public class SettingActivity extends AppCompatActivity {

    public static final int VALUE_SMALL = -1;
    public static final String EXTRA_FONT = "font size";
    public static final String EXTRA_MEDIUM = "medium";
    public static final int VALUE_MEDIUM = 0;
    public static final String EXTRA_LARGE = "large";
    public static final int VALUE_LARGE = 1;
    private RadioButton mRadioButtonSmall;
    private RadioButton mRadioButtonMedium;
    private RadioButton mRadioButtonLarge;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        findViews();
        setListeners();
    }

    private void setListeners() {
        mRadioButtonSmall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                settingFontResult(VALUE_SMALL);
            }
        });

        mRadioButtonMedium.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                settingFontResult(VALUE_MEDIUM);
            }
        });

        mRadioButtonLarge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                settingFontResult(VALUE_LARGE);
            }
        });
    }

    private void settingFontResult(int size) {
        Intent intent= new Intent();
        intent.putExtra(EXTRA_FONT, size);
        setResult(RESULT_OK,intent);
    }

    private void findViews() {
        mRadioButtonSmall=findViewById(R.id.radioButtonSmall);
        mRadioButtonMedium=findViewById(R.id.radioButtonMedium);
        mRadioButtonLarge=findViewById(R.id.radioButtonLarge);
    }
}