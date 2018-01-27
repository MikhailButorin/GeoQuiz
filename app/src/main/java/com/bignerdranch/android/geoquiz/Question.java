package com.bignerdranch.android.geoquiz;

/**
 * Created by User on 21.01.2018.
 */

public class Question {
    private int mTextResID;
    private boolean mAnswerTrue;
    private Boolean mRightAnswered = null;

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

    public Boolean isRightAnswered() {
        return mRightAnswered;
    }

    public void setRightAnswered(Boolean rightAnswered) {
        mRightAnswered = rightAnswered;
    }
}
