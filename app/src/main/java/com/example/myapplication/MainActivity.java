package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    private static final String BUNDLE_KEY_CURRENT_INDEX = "currentIndex";
    private static final String BUNDLE_KEY_SCORE = "score";
    private static final String BUNDLE_KEY_COUNTER = "counter";
    public static final String EXTRA_QUESTION_ANSWER = "questionAnswer";
    public static final int REQUEST_CODE_CHEAT = 0;
    public static final int REQUEST_CODE_SETTING = 1;
    public static final String BUNDLE_KEY_IS_CHEATER = "is cheater";
    public static final String BUNDLE_KEY_IS_ANSWERED = "is answered";
    public static final String EXTRA_FONT_SIZE = "font size";
    public static final String EXTRA_BACKGOUND_COLOR = "backgound color";
    public static final String BUNDLE_KEY_FONT_SIZE = "fontSize";
    public static final String BUNDLE_KEY_BACKGROUND_COLOR = "background color";


    private TextView mTextViewQ;
    private TextView mTextViewScore;
    private TextView mTextViewScoreString;
    private ImageButton mButtonTrue;
    private ImageButton mButtonFalse;
    private ImageButton mButtonNext;
    private ImageButton mButtonPrev;
    private ImageButton mButtonFirst;
    private ImageButton mButtonLast;
    private Button mButtonReset;
    private Button mButtonCheat;
    private Button mButtonSetting;
    private LinearLayout mplayingLayout;
    private LinearLayout rightArrows;
    private LinearLayout leftArrows;
    private FrameLayout mMainLayout;
    private Question[] mQuestionBank = {
            new Question(R.string.question_australia, false),
            new Question(R.string.question_oceans, true),
            new Question(R.string.question_mideast, false),
            new Question(R.string.question_africa, true),
            new Question(R.string.question_americas, false),
            new Question(R.string.question_asia, false)
    };
    private int mCurrentIndex = 0;
    private int mScore = 0;
    private int mCounter = 0;
    private int mFontSize = -2;
    private String mBackgroundColor="white";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViews();
        if (savedInstanceState != null) {
            mCurrentIndex = savedInstanceState.getInt(BUNDLE_KEY_CURRENT_INDEX, 0);
            mScore = savedInstanceState.getInt(BUNDLE_KEY_SCORE, 0);
            mCounter = savedInstanceState.getInt(BUNDLE_KEY_COUNTER, 0);
            mQuestionBank[mCurrentIndex].setIsAnswered(savedInstanceState.getBoolean(BUNDLE_KEY_IS_ANSWERED));
            mQuestionBank[mCurrentIndex].setIsCheater(savedInstanceState.getBoolean(BUNDLE_KEY_IS_CHEATER, false));
            mFontSize=savedInstanceState.getInt(BUNDLE_KEY_FONT_SIZE,-2);
            mBackgroundColor=savedInstanceState.getString(BUNDLE_KEY_BACKGROUND_COLOR,"white");
            updateUI();
        }

        setListener();
        updateQuestion();
    }

    private void setTextSize(float size) {
        mTextViewQ.setTextSize(TypedValue.COMPLEX_UNIT_SP, size);
        mTextViewScoreString.setTextSize(TypedValue.COMPLEX_UNIT_SP, size);
        mTextViewScore.setTextSize(TypedValue.COMPLEX_UNIT_SP, size);
        mButtonCheat.setTextSize(TypedValue.COMPLEX_UNIT_SP, size);
        mButtonSetting.setTextSize(TypedValue.COMPLEX_UNIT_SP, size);

    }

    private void setDefaultTextSize() {
        mTextViewScoreString.setTextSize(TypedValue.COMPLEX_UNIT_SP, 50);
        mTextViewScore.setTextSize(TypedValue.COMPLEX_UNIT_SP, 36);
        mTextViewQ.setTextSize(TypedValue.COMPLEX_UNIT_SP, 24);
        mButtonCheat.setTextSize(TypedValue.COMPLEX_UNIT_SP, 24);
        mButtonSetting.setTextSize(TypedValue.COMPLEX_UNIT_SP, 24);
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(BUNDLE_KEY_SCORE, mScore);
        outState.putInt(BUNDLE_KEY_COUNTER, mCounter);
        outState.putInt(BUNDLE_KEY_CURRENT_INDEX, mCurrentIndex);
        outState.putBoolean(BUNDLE_KEY_IS_CHEATER, mQuestionBank[mCurrentIndex].getIsCheater());
        outState.putBoolean(BUNDLE_KEY_IS_ANSWERED, mQuestionBank[mCurrentIndex].getIsAnswered());
        outState.putInt(BUNDLE_KEY_FONT_SIZE,mFontSize);
        outState.putCharSequence(BUNDLE_KEY_BACKGROUND_COLOR,mBackgroundColor);

    }

    private void findViews() {
        mTextViewQ = findViewById(R.id.questionTextView);
        mTextViewScore = findViewById(R.id.score);
        mTextViewScoreString = findViewById(R.id.scoreString);
        mButtonTrue = findViewById(R.id.imButtonCorrect);
        mButtonFalse = findViewById(R.id.imButtonWrong);
        mButtonNext = findViewById(R.id.imButtonNext);
        mButtonPrev = findViewById(R.id.imButtonPrev);
        mButtonFirst = findViewById(R.id.imButtonLast);
        mButtonLast = findViewById(R.id.imButtonFirst);
        mButtonReset = findViewById(R.id.resetButton);
        mButtonCheat = findViewById(R.id.ButtonCheat);
        mButtonSetting = findViewById(R.id.ButtonSetting);
        mplayingLayout = findViewById(R.id.playingLayout);
        rightArrows = findViewById(R.id.rightArrows);
        leftArrows = findViewById(R.id.leftArrows);
        mMainLayout =findViewById(R.id.mainLayout);
    }

    public void setListener() {
        mButtonTrue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mQuestionBank[mCurrentIndex].getIsAnswered()) {
                    mButtonTrue.setEnabled(false);
                    mButtonFalse.setEnabled(false);
                } else {
                    checkAnswer(true);
                }
            }
        });

        mButtonFalse.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                if (mQuestionBank[mCurrentIndex].getIsAnswered()) {
                    mButtonTrue.setEnabled(false);
                    mButtonFalse.setEnabled(false);
                } else {
                    checkAnswer(false);
                }

            }
        });

        mButtonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCurrentIndex = (mCurrentIndex + 1) % mQuestionBank.length;
                updateQuestion();
            }
        });

        mButtonPrev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCurrentIndex = (mCurrentIndex - 1 + mQuestionBank.length) % mQuestionBank.length;
                updateQuestion();
            }

        });

        mButtonFirst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCurrentIndex = 0;
                updateQuestion();
            }
        });

        mButtonLast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCurrentIndex = mQuestionBank.length - 1;
                updateQuestion();
            }
        });

        mButtonReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = getPackageManager().getLaunchIntentForPackage(getPackageName());
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);

            }
        });

        mButtonCheat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, CheatActivity.class);
                intent.putExtra(EXTRA_QUESTION_ANSWER, mQuestionBank[mCurrentIndex].isCorrectAnswer());
                startActivityForResult(intent, REQUEST_CODE_CHEAT);
                //startActivity(intent);
            }
        });

        mButtonSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SettingActivity.class);
                intent.putExtra(EXTRA_FONT_SIZE, mFontSize);
                intent.putExtra(EXTRA_BACKGOUND_COLOR,mBackgroundColor);
                startActivityForResult(intent, REQUEST_CODE_SETTING);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != Activity.RESULT_OK || data == null)
            return;
        if (requestCode == REQUEST_CODE_CHEAT) {
            mQuestionBank[mCurrentIndex].setIsCheater(data.getBooleanExtra(CheatFragment.EXTRE_IS_CHEATED, false));
        }
        if (requestCode == REQUEST_CODE_SETTING) {
            mFontSize = data.getIntExtra(SettingActivity.EXTRA_FONT, -2);
            updateTextsSize();
            mBackgroundColor=data.getStringExtra(SettingActivity.EXTRA_BACKGROUND_COLOR);
            updateBackgroundColor();
        }


    }


    private void updateQuestion() {
        int questionTextResId = mQuestionBank[mCurrentIndex].getQuestionTextResId();
        mTextViewQ.setText(questionTextResId);
        mButtonTrue.setEnabled(true);
        mButtonFalse.setEnabled(true);
    }

    private void checkAnswer(boolean userPressed) {
        mCounter++;
        mQuestionBank[mCurrentIndex].setIsAnswered(true);
        if (mQuestionBank[mCurrentIndex].getIsCheater()) {
            Toast.makeText(this, R.string.you_cheat_toast, Toast.LENGTH_SHORT).show();
        } else {
            if (mQuestionBank[mCurrentIndex].isCorrectAnswer() == userPressed) {

                mScore++;
                mTextViewScore.setText(String.valueOf(mScore));
                Toast toastC = Toast.makeText(MainActivity.this, R.string.toast_correct, Toast.LENGTH_SHORT);
                TextView toastMessage = (TextView) toastC.getView().findViewById(android.R.id.message);
                toastMessage.setTextSize(20);
                toastMessage.setTextColor(Color.GREEN);
                toastC.show();
            } else {
                mScore--;
                mTextViewScore.setText(String.valueOf(mScore));
                Toast toastW = Toast.makeText(MainActivity.this, R.string.toast_incorrect, Toast.LENGTH_SHORT);
                TextView toastMessage = (TextView) toastW.getView().findViewById(android.R.id.message);
                toastMessage.setTextSize(20);
                toastMessage.setTextColor(Color.RED);
                toastW.show();
            }

            checkGameIsOver();
        }

    }

    public void updateUI() {
        mTextViewScore.setText(String.valueOf(mScore));
        updateTextsSize();
        updateBackgroundColor();
        checkGameIsOver();
    }

    private void updateTextsSize() {
        switch (mFontSize) {
            case -2:
                setDefaultTextSize();
                break;
            case -1:
                setTextSize(14);
                break;
            case 0:
                setTextSize(18);
                break;
            case 1:
                setTextSize(26);

        }
    }

    private void updateBackgroundColor()
    {
        switch (mBackgroundColor){
            case "white":
                mMainLayout.setBackgroundColor(Color.WHITE);
                break;
            case "blue":
                mMainLayout.setBackgroundColor(getResources().getColor(R.color.lightBlue));
                break;
            case "red":
                mMainLayout.setBackgroundColor(getResources().getColor(R.color.lightRed));
                break;
            case "green":
                mMainLayout.setBackgroundColor(getResources().getColor(R.color.lightGreen));

        }
    }

    private void checkGameIsOver() {
        if (mCounter == mQuestionBank.length) {
            if (mplayingLayout != null)
                mplayingLayout.setVisibility(LinearLayout.GONE);
            mButtonCheat.setVisibility(Button.GONE);
            mButtonReset.setVisibility(Button.VISIBLE);
            if (rightArrows != null && leftArrows != null) {
                rightArrows.setVisibility(LinearLayout.GONE);
                leftArrows.setVisibility(LinearLayout.GONE);
            }

        }
    }

}