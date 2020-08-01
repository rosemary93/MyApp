package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    private TextView mTextViewQ;
    private TextView mTextViewScore;
    private ImageButton mButtonTrue;
    private ImageButton mButtonFalse;
    private ImageButton mButtonNext;
    private ImageButton mButtonPrev;
    private ImageButton mButtonFirst;
    private ImageButton mButtonLast;
    private Button mButtonReset;
    private LinearLayout mplayingLayout;
    private Question[] mQuestionBank = {
            new Question(R.string.question_australia, false),
            new Question(R.string.question_oceans, true),
            new Question(R.string.question_mideast, false),
            new Question(R.string.question_africa, true),
            new Question(R.string.question_americas, false),
            new Question(R.string.question_asia, false)
    };
    private boolean[] answered = new boolean[mQuestionBank.length];
    private int mCurrentIndex = 0;
    private int score = 0;
    private int counter = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViews();
        setListener();
        mTextViewQ.setText(mQuestionBank[mCurrentIndex].getQuestionTextResId());
    }

    private void findViews() {
        mTextViewQ = findViewById(R.id.questionTextView);
        mTextViewScore = findViewById(R.id.score);
        mButtonTrue = findViewById(R.id.imButtonCorrect);
        mButtonFalse = findViewById(R.id.imButtonWrong);
        mButtonNext = findViewById(R.id.imButtonNext);
        mButtonPrev = findViewById(R.id.imButtonPrev);
        mButtonFirst = findViewById(R.id.imButtonFirst);
        mButtonLast = findViewById(R.id.imButtonLast);
        mButtonReset = findViewById(R.id.resetButton);
        mplayingLayout = findViewById(R.id.playingLayout);
    }

    public void setListener() {
        mButtonTrue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (answered[mCurrentIndex]) {
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
                if (answered[mCurrentIndex]) {
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

    }

    private void updateQuestion() {
        int questionTextResId = mQuestionBank[mCurrentIndex].getQuestionTextResId();
        mTextViewQ.setText(questionTextResId);
        mButtonTrue.setClickable(true);
        mButtonFalse.setClickable(true);
    }

    private void checkAnswer(boolean userPressed) {
        counter++;
        if (mQuestionBank[mCurrentIndex].isAnswerTrue() == userPressed) {
            answered[mCurrentIndex] = true;
            score++;
            mTextViewScore.setText(String.valueOf(score));
            Toast toastC = Toast.makeText(MainActivity.this, R.string.toast_correct, Toast.LENGTH_SHORT);
            TextView toastMessage = (TextView) toastC.getView().findViewById(android.R.id.message);
            toastMessage.setTextSize(20);
            toastMessage.setTextColor(Color.GREEN);
            toastC.show();
        } else {
            answered[mCurrentIndex] = true;
            score--;
            mTextViewScore.setText(String.valueOf(score));
            Toast toastW = Toast.makeText(MainActivity.this, R.string.toast_incorrect, Toast.LENGTH_SHORT);
            toastW.setGravity(Gravity.TOP, 0, 0);
            TextView toastMessage = (TextView) toastW.getView().findViewById(android.R.id.message);
            toastMessage.setTextSize(20);
            toastMessage.setTextColor(Color.RED);
            toastW.show();
        }
        if (counter == mQuestionBank.length) {
            mplayingLayout.setVisibility(LinearLayout.GONE);
            mButtonReset.setVisibility(Button.VISIBLE);

        }

    }

}