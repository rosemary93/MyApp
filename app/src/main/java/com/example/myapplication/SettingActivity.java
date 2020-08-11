package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;

public class SettingActivity extends AppCompatActivity {
    public static final String EXTRA_FONT = "font size";
    public static final String EXTRA_BACKGROUND_COLOR = "background color";
    public static final String BACKGROUND_WHITE = "white";
    public static final String BACKGROUND_LIGHT_BLUE = "blue";
    public static final String BACKGROUND_LIGHT_RED = "red";
    public static final String BACKGROUND_LIGHT_GREEN = "green";
    public static final String BUNDLE_KEY_FONT_SIZE = "fontSize";
    public static final String BUNDLE_KEY_BACKGROUND_COLOR = "backgroundColor";
    public static final int FONT_SIZE_SMALL = -1;
    public static final int FONT_SIZE_MEDIUM = 0;
    public static final int FONT_SIZE_LARGE = 1;
    public static final int FONT_SIZE_DEFAULT = -2;
    private RadioButton mRadioButtonDefaultFontSize;
    private RadioButton mRadioButtonSmallFont;
    private RadioButton mRadioButtonMediumFont;
    private RadioButton mRadioButtonLargeFont;
    private RadioButton mRadioButtonWhiteBkg;
    private RadioButton mRadioButtonRedBkg;
    private RadioButton mRadioButtonBlueBkg;
    private RadioButton mRadioButtonGreenBkg;
    private int mFontSizeS=-2;
    private String mBackgroundColor =BACKGROUND_WHITE;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        findViews();
        if (savedInstanceState!=null)
        {
            mFontSizeS=savedInstanceState.getInt(BUNDLE_KEY_FONT_SIZE,-2);
            mBackgroundColor= (String) savedInstanceState.get(BUNDLE_KEY_BACKGROUND_COLOR);
            settingFontAndBackgroundColor();
        }

        mBackgroundColor=getIntent().getStringExtra(MainActivity.EXTRA_BACKGOUND_COLOR);
        mFontSizeS = getIntent().getIntExtra(MainActivity.EXTRA_FONT_SIZE, -2);
        setCurrentButtonsEnable();

        setListeners();
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(BUNDLE_KEY_FONT_SIZE,mFontSizeS);
        outState.putCharSequence(BUNDLE_KEY_BACKGROUND_COLOR,mBackgroundColor);
    }

    private void setCurrentButtonsEnable() {
        switch (mFontSizeS) {
            case -2:
                mRadioButtonDefaultFontSize.setChecked(true);
                break;
            case -1:
                mRadioButtonSmallFont.setChecked(true);
                break;
            case 0:
                mRadioButtonMediumFont.setChecked(true);
                break;
            case 1:
                mRadioButtonLargeFont.setChecked(true);

        }
        switch (mBackgroundColor)
        {
            case "white":
                mRadioButtonWhiteBkg.setChecked(true);
                break;
            case "blue":
                mRadioButtonBlueBkg.setChecked(true);
                break;
            case "red":
                mRadioButtonRedBkg.setChecked(true);
                break;
            case "green":
                mRadioButtonGreenBkg.setChecked(true);
        }
    }

    private void setListeners() {
        mRadioButtonSmallFont.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mFontSizeS= FONT_SIZE_SMALL;
                mRadioButtonDefaultFontSize.setChecked(false);
                mRadioButtonMediumFont.setChecked(false);
                mRadioButtonLargeFont.setChecked(false);
                settingFontAndBackgroundColor();
            }
        });

        mRadioButtonMediumFont.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mFontSizeS= FONT_SIZE_MEDIUM;
                mRadioButtonDefaultFontSize.setChecked(false);
                mRadioButtonSmallFont.setChecked(false);
                mRadioButtonLargeFont.setChecked(false);
                settingFontAndBackgroundColor();
            }
        });

        mRadioButtonLargeFont.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mFontSizeS= FONT_SIZE_LARGE;
                mRadioButtonDefaultFontSize.setChecked(false);
                mRadioButtonMediumFont.setChecked(false);
                mRadioButtonSmallFont.setChecked(false);
                settingFontAndBackgroundColor();
            }
        });

        mRadioButtonDefaultFontSize.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mFontSizeS= FONT_SIZE_DEFAULT;
                mRadioButtonSmallFont.setChecked(false);
                mRadioButtonMediumFont.setChecked(false);
                mRadioButtonLargeFont.setChecked(false);
                settingFontAndBackgroundColor();
            }
        });

        mRadioButtonWhiteBkg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mBackgroundColor = BACKGROUND_WHITE;
                settingFontAndBackgroundColor();
            }
        });

        mRadioButtonBlueBkg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mBackgroundColor = BACKGROUND_LIGHT_BLUE;
                settingFontAndBackgroundColor();
            }
        });

        mRadioButtonRedBkg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mBackgroundColor = BACKGROUND_LIGHT_RED;
                settingFontAndBackgroundColor();
            }
        });

        mRadioButtonGreenBkg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mBackgroundColor = BACKGROUND_LIGHT_GREEN;
                settingFontAndBackgroundColor();
            }
        });


    }


    private void settingFontAndBackgroundColor() {
        Intent intent = new Intent();
        intent.putExtra(EXTRA_FONT, mFontSizeS);
        intent.putExtra(EXTRA_BACKGROUND_COLOR, mBackgroundColor);
        setResult(RESULT_OK, intent);
    }

    private void findViews() {
        mRadioButtonSmallFont = findViewById(R.id.radioButtonSmallFont);
        mRadioButtonMediumFont = findViewById(R.id.radioButtonMediumFont);
        mRadioButtonLargeFont = findViewById(R.id.radioButtonLargeFont);
        mRadioButtonDefaultFontSize = findViewById(R.id.radioButtonDefaultFont);
        mRadioButtonBlueBkg = findViewById(R.id.radioButtonBlueColor);
        mRadioButtonGreenBkg = findViewById(R.id.radioButtonGreenColor);
        mRadioButtonWhiteBkg = findViewById(R.id.radioButtonWhiteColor);
        mRadioButtonRedBkg = findViewById(R.id.radioButtonRedColor);

    }
}