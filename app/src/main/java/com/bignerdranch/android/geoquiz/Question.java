package com.bignerdranch.android.geoquiz;

/**
 * Created by User on 21.01.2018.
 */

public class Question {
    private int mTextResID;
    private boolean mAnswerTrue;
    private Boolean mAnswered = null;

    public Question(int textResId, boolean answerTrue) {
        mTextResID = textResId;
        mAnswerTrue = answerTrue;
    }

    public int getTextResID() {
        return mTextResID;
    }

    public void setTextResID(int textResID) {
        mTextResID = textResID;
    }

    public boolean isAnswerTrue() {
        return mAnswerTrue;
    }

    public void setAnswerTrue(boolean answerTrue) {
        mAnswerTrue = answerTrue;
    }

    public Boolean isAnswered() {
        return mAnswered;
    }

    public void setAnswered(Boolean answered) {
        mAnswered = answered;
    }
}
