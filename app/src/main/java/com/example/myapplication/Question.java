package com.example.myapplication;



public class Question  {
    //private String mText;
    private int mQuestionTextResId;
    private boolean mCorrectAnswer;
    private boolean mIsCheater;
    private boolean mIsAnswered;

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

    public boolean isCorrectAnswer() {
        return mCorrectAnswer;
    }

    public void setCorrectAnswer(boolean correctAnswer) {
        mCorrectAnswer = correctAnswer;
    }

    public boolean getIsAnswered() {
        return mIsAnswered;
    }

    public void setIsAnswered(boolean answered) {
        mIsAnswered = answered;
    }

    public Question(int questionTextResId, boolean correctAnswer) {
        mQuestionTextResId = questionTextResId;
        mCorrectAnswer = correctAnswer;
    }
}
