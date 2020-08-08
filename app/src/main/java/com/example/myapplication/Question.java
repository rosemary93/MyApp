package com.example.myapplication;



public class Question  {
    //private String mText;
    private int mQuestionTextResId;
    private boolean mIsAnswerTrue;
    private boolean mIsCheater;

    public boolean getIsCheater() {
        return mIsCheater;
    }

    public void setIsCheater(boolean cheater) {
        mIsCheater = cheater;
    }

    public int getQuestionTextResId() {
        return mQuestionTextResId;
    }

    public void setQuestionTextResId(int questionTextResId) {
        mQuestionTextResId = questionTextResId;
    }

    public boolean isAnswerTrue() {
        return mIsAnswerTrue;
    }

    public void setAnswerTrue(boolean answerTrue) {
        mIsAnswerTrue = answerTrue;
    }

    public Question(int questionTextResId, boolean isAnswerTrue) {
        mQuestionTextResId = questionTextResId;
        mIsAnswerTrue = isAnswerTrue;
    }
}
