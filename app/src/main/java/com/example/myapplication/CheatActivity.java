package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;


public class CheatActivity extends AppCompatActivity {

   /* public static final String EXTRE_IS_CHEATED = "isCheated";
    public static final String CHEAT_IS_PRESSED = "cheatIsPressed";
    public static final String CORRECT_ANSWER = "correct answer";
    private Button mButtonShowAnswer;
    private TextView mTextViewAnswer;
    private boolean mCorrectAnswer;
    private boolean mCheatIsPressed;*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cheat);
        FragmentManager fragmentManager=getSupportFragmentManager();
        Fragment fragment=fragmentManager.findFragmentById(R.id.cheatFragmentContainer);
        if (fragment==null) {
            CheatFragment cheatFragment=new CheatFragment();
            fragmentManager.beginTransaction().add(R.id.cheatFragmentContainer,cheatFragment).commit();
        }
//        findViews();
        /*if(savedInstanceState!= null)
        {
            mCheatIsPressed=savedInstanceState.getBoolean(CHEAT_IS_PRESSED,false);
            mCorrectAnswer =savedInstanceState.getBoolean(CORRECT_ANSWER,false);
            if (mCheatIsPressed) {
                if (mCorrectAnswer) {
                    mTextViewAnswer.setText("درست");
                } else {
                    mTextViewAnswer.setText("نادرست");
                }
            }

        }
        mCorrectAnswer =getIntent().getBooleanExtra(MainActivity.EXTRA_QUESTION_ANSWER,false);

        setListener();

        Intent intent= new Intent();
        intent.putExtra(EXTRE_IS_CHEATED,true);
        setResult(RESULT_OK,intent);
    }

    private void setListener() {
        mButtonShowAnswer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCheatIsPressed=true;
                if (mCorrectAnswer)
                {
                    mTextViewAnswer.setText(R.string.true_button);
                }else {
                    mTextViewAnswer.setText(R.string.false_button);
                }
            }
        });
    }

    public void findViews()
    {
        mTextViewAnswer=findViewById(R.id.textViewAnswer);
        mButtonShowAnswer=findViewById(R.id.ButtonShowAnswer);
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean(CHEAT_IS_PRESSED,mCheatIsPressed);
        outState.putBoolean(CORRECT_ANSWER, mCorrectAnswer);
    }*/
}}