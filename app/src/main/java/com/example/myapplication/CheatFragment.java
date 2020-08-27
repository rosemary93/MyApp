package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CheatFragment} factory method to
 * create an instance of this fragment.
 */
public class CheatFragment extends Fragment {

    public static final String EXTRE_IS_CHEATED = "isCheated";
    public static final String CHEAT_IS_PRESSED = "cheatIsPressed";
    public static final String CORRECT_ANSWER = "correct answer";
    private Button mButtonShowAnswer;
    private TextView mTextViewAnswer;
    private boolean mCorrectAnswer;
    private boolean mCheatIsPressed;
    View mView;

    public CheatFragment() {
        // Required empty public constructor
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean(CHEAT_IS_PRESSED,mCheatIsPressed);
        outState.putBoolean(CORRECT_ANSWER, mCorrectAnswer);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView= inflater.inflate(R.layout.fragment_cheat, container, false);
        findViews();

        if(savedInstanceState!= null)
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

        mCorrectAnswer =getActivity().getIntent().getBooleanExtra(MainActivity.EXTRA_QUESTION_ANSWER,false);

        setListener();

        Intent intent= new Intent();
        intent.putExtra(EXTRE_IS_CHEATED,true);
        getActivity().setResult(getActivity().RESULT_OK,intent);
        return mView;
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
        mTextViewAnswer=mView.findViewById(R.id.textViewAnswer);
        mButtonShowAnswer=mView.findViewById(R.id.ButtonShowAnswer);
    }
}