package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CheatActivity extends AppCompatActivity {

    public static final String EXTRE_IS_CHEATED = "isCheated";
    private Button mButtonShowAnswer;
    private TextView mTextViewAnswer;
    private boolean mIsAnswerTrue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cheat);

        mIsAnswerTrue=getIntent().getBooleanExtra(MainActivity.EXTRA_QUESTION_ANSWER,false);
        findViews();
        setListener();

        Intent intent= new Intent();
        intent.putExtra(EXTRE_IS_CHEATED,true);
        setResult(RESULT_OK,intent);
    }

    private void setListener() {
        mButtonShowAnswer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mIsAnswerTrue)
                {
                    mTextViewAnswer.setText("درست");
                }else {
                    mTextViewAnswer.setText("نادرست");
                }
            }
        });
    }

    public void findViews()
    {
        mTextViewAnswer=findViewById(R.id.textViewAnswer);
        mButtonShowAnswer=findViewById(R.id.ButtonShowAnswer);
    }
}