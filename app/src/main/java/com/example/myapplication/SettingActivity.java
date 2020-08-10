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
    public static final int VALUE_MEDIUM = 0;
    public static final int VALUE_LARGE = 1;
    public static final int VALUE_DEFAULT = -2;
    private RadioButton mRadioButtonDefault;
    private RadioButton mRadioButtonSmall;
    private RadioButton mRadioButtonMedium;
    private RadioButton mRadioButtonLarge;
    private int mFontSizeS;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        findViews();

        mFontSizeS=getIntent().getIntExtra(MainActivity.EXTRA_FONT_SIZE,-2);
        setCurrentFontButtonEnable();


        setListeners();
    }

    private void setCurrentFontButtonEnable() {
        switch (mFontSizeS){
            case -2:
                mRadioButtonDefault.setChecked(true);
                break;
            case -1:
                mRadioButtonSmall.setChecked(true);
                break;
            case 0:
                mRadioButtonMedium.setChecked(true);
                break;
            case 1:
                mRadioButtonLarge.setChecked(true);

        }
    }

    private void setListeners() {
        mRadioButtonSmall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mRadioButtonDefault.setChecked(false);
                mRadioButtonMedium.setChecked(false);
                mRadioButtonLarge.setChecked(false);

                settingFontResult(VALUE_SMALL);
            }
        });

        mRadioButtonMedium.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mRadioButtonDefault.setChecked(false);
                mRadioButtonSmall.setChecked(false);
                mRadioButtonLarge.setChecked(false);
                settingFontResult(VALUE_MEDIUM);
            }
        });

        mRadioButtonLarge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mRadioButtonDefault.setChecked(false);
                mRadioButtonMedium.setChecked(false);
                mRadioButtonSmall.setChecked(false);
                settingFontResult(VALUE_LARGE);
            }
        });
        mRadioButtonDefault.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mRadioButtonSmall.setChecked(false);
                mRadioButtonMedium.setChecked(false);
                mRadioButtonLarge.setChecked(false);
                settingFontResult(VALUE_DEFAULT);
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
        mRadioButtonDefault=findViewById(R.id.radioButtonDefault);
    }
}