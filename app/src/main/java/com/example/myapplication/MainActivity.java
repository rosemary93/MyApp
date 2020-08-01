package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    private Button mButtonTrue;
    private Button mButtonFalse;
    private TextView mTextViewQ;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mButtonTrue = findViewById(R.id.trueBottun);
        mButtonFalse = findViewById(R.id.wrongBottun);
        mTextViewQ = findViewById(R.id.questionTextView);


        setListener();

    }

    public void setListener() {
        mButtonTrue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast toast = Toast.makeText(MainActivity.this, R.string.toast_correct, Toast.LENGTH_SHORT);
                TextView toastMessage = (TextView) toast.getView().findViewById(android.R.id.message);
                toastMessage.setTextColor(Color.GREEN);
                /*LinearLayout toastLayout = (LinearLayout) toast.getView();
                TextView toastTV = (TextView) toastLayout.getChildAt(0);*/
                toastMessage.setTextSize(20);
                toastMessage.setTextColor(Color.GREEN);
                mTextViewQ.setTextColor(Color.GREEN);
                toast.show();
            }
        });

        mButtonFalse.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Toast toast = Toast.makeText(MainActivity.this, R.string.toast_incorrect, Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.TOP, 0, 0);
                LinearLayout toastLayout = (LinearLayout) toast.getView();
                TextView toastTV = (TextView) toastLayout.getChildAt(0);
                toastTV.setTextSize(20);
                toastTV.setTextColor(Color.RED);
                mTextViewQ.setTextColor(Color.RED);
                toast.show();

            }
        });
    }

}