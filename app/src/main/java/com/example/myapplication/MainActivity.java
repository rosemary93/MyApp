package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    private static final String BUNDLE_KEY_CURRENT_INDEX = "currentIndex";
    private static final String BUNDLE_KEY_SCORE = "score";
    private static final String BUNDLE_KEY_COUNTER = "counter";
    private static final String BUNDLE_KEY_IS_ENABLED = "isEnable";
    public static final String EXTRA_QUESTION_ANSWER = "questionAnswer";
    public static final int REQUEST_CODE_CHEAT = 0;


    private TextView mTextViewQ;
    private TextView mTextViewScore;
    private ImageButton mButtonTrue;
    private ImageButton mButtonFalse;
    private ImageButton mButtonNext;
    private ImageButton mButtonPrev;
    private ImageButton mButtonFirst;
    private ImageButton mButtonLast;
    private Button mButtonReset;
    private Button mButtonCheat;
    private LinearLayout mplayingLayout;
    private LinearLayout rightArrows;
    private LinearLayout leftArrows;
    private Question[] mQuestionBank = {
            new Question(R.string.question_australia, false),
            new Question(R.string.question_oceans, true),
            new Question(R.string.question_mideast, false),
            new Question(R.string.question_africa, true),
            new Question(R.string.question_americas, false),
            new Question(R.string.question_asia, false)
    };
    private boolean[] mAnswered = new boolean[mQuestionBank.length];
    private int mCurrentIndex = 0;
    private int mScore = 0;
    private int mCounter = 0;
    private boolean mIsCheater=false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViews();
        if (savedInstanceState != null) {
            mCurrentIndex=savedInstanceState.getInt(BUNDLE_KEY_CURRENT_INDEX,0);
            mScore=savedInstanceState.getInt(BUNDLE_KEY_SCORE,0);
            mAnswered=savedInstanceState.getBooleanArray(BUNDLE_KEY_IS_ENABLED);
            mCounter=savedInstanceState.getInt(BUNDLE_KEY_COUNTER,0);
            updateUI();
        }

        setListener();
        updateQuestion();
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(BUNDLE_KEY_SCORE,mScore);
        outState.putInt(BUNDLE_KEY_COUNTER,mCounter);
        outState.putInt(BUNDLE_KEY_CURRENT_INDEX,mCurrentIndex);
        outState.putBooleanArray(BUNDLE_KEY_IS_ENABLED,mAnswered);


    }

    private void findViews() {
        mTextViewQ = findViewById(R.id.questionTextView);
        mTextViewScore = findViewById(R.id.score);
        mButtonTrue = findViewById(R.id.imButtonCorrect);
        mButtonFalse = findViewById(R.id.imButtonWrong);
        mButtonNext = findViewById(R.id.imButtonNext);
        mButtonPrev = findViewById(R.id.imButtonPrev);
        mButtonFirst = findViewById(R.id.imButtonLast);
        mButtonLast = findViewById(R.id.imButtonFirst);
        mButtonReset = findViewById(R.id.resetButton);
        mButtonCheat = findViewById(R.id.ButtonCheat);
        mplayingLayout = findViewById(R.id.playingLayout);
        rightArrows= findViewById(R.id.rightArrows);
        leftArrows=findViewById(R.id.leftArrows);
    }

    public void setListener() {
        mButtonTrue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mAnswered[mCurrentIndex]) {
                    mButtonTrue.setClickable(false);
                    mButtonFalse.setClickable(false);
                } else {
                    checkAnswer(true);
                }
            }
        });

        mButtonFalse.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                if (mAnswered[mCurrentIndex]) {
                    mButtonTrue.setClickable(false);
                    mButtonFalse.setClickable(false);
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
                Intent intent = new Intent(MainActivity.this,CheatActivity.class);
                intent.putExtra(EXTRA_QUESTION_ANSWER,mQuestionBank[mCurrentIndex].isAnswerTrue());
                startActivityForResult(intent, REQUEST_CODE_CHEAT);
                //startActivity(intent);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode!= Activity.RESULT_OK || data==null)
            return;
        if (requestCode == REQUEST_CODE_CHEAT){
            mQuestionBank[mCurrentIndex].setIsCheater(data.getBooleanExtra(CheatActivity.EXTRE_IS_CHEATED,false));
        }


    }

    private void updateQuestion() {
        int questionTextResId = mQuestionBank[mCurrentIndex].getQuestionTextResId();
        mTextViewQ.setText(questionTextResId);
        mButtonTrue.setClickable(true);
        mButtonFalse.setClickable(true);
    }

    private void checkAnswer(boolean userPressed) {
        mCounter++;
        mAnswered[mCurrentIndex] = true;
        if (mQuestionBank[mCurrentIndex].getIsCheater())
        {
            Toast.makeText(this, "تقلب کار خوبی نیست", Toast.LENGTH_SHORT).show();
        }
        else {
            if (mQuestionBank[mCurrentIndex].isAnswerTrue() == userPressed) {

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
    public void updateUI()
    {
        mTextViewScore.setText(String.valueOf(mScore));
        checkGameIsOver();
    }

    private void checkGameIsOver() {
        if (mCounter == mQuestionBank.length) {
            if(mplayingLayout!= null)
            mplayingLayout.setVisibility(LinearLayout.GONE);
            mButtonCheat.setVisibility(Button.GONE);
            mButtonReset.setVisibility(Button.VISIBLE);
            if (rightArrows!=null && leftArrows!= null)
            {
                rightArrows.setVisibility(LinearLayout.GONE);
                leftArrows.setVisibility(LinearLayout.GONE);
            }

        }
    }

}