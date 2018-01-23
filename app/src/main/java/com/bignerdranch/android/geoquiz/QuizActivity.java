package com.bignerdranch.android.geoquiz;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class QuizActivity extends AppCompatActivity {

    private static final String TAG = "QuizActivity";
    private static final String KEY_INDEX = "index";
    private Button mTrueButton;
    private Button mFalseButton;
    private Button mPrevButton;
    private Button mNextButton;
    private TextView mQuestionTextView;

    private Question[] mQuestionBank = new Question[] {
            new Question(R.string.question_australia, true),
            new Question(R.string.question_oceans, true),
            new Question(R.string.question_mideast, false),
            new Question(R.string.question_africa, false),
            new Question(R.string.question_americas, true),
            new Question(R.string.question_asia, false),
    };

    private int mCurrentIndex = 0;
    private int mAnsweredQuestion = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate(Bundle) called");
        setContentView(R.layout.activity_quiz);

        if (savedInstanceState != null) {
            mCurrentIndex = savedInstanceState.getInt(KEY_INDEX, 0);
        }

        mQuestionTextView = (TextView) findViewById(R.id.question_text_view);
        mQuestionTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCurrentIndex = (mCurrentIndex + 1) % mQuestionBank.length;
                updateQuestion();
            }
        });

        mTrueButton = (Button) findViewById(R.id.true_button);
        mTrueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(true);
            }
        });

        mFalseButton = (Button) findViewById(R.id.false_button);
        mFalseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(false);
            }
        });

        mPrevButton = (Button) findViewById(R.id.prev_button);
        mPrevButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCurrentIndex = (mCurrentIndex - 1 + mQuestionBank.length) % mQuestionBank.length;
                updateQuestion();
                if (mQuestionBank[mCurrentIndex].isAnswered() == null) {
                    mTrueButton.setEnabled(true);
                    mFalseButton.setEnabled(true);
                } else {
                    mTrueButton.setEnabled(false);
                    mFalseButton.setEnabled(false);
                }
            }
        });

        updateQuestion();

        mNextButton = (Button) findViewById(R.id.next_button);
        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCurrentIndex = (mCurrentIndex + 1) % mQuestionBank.length;
                updateQuestion();
                if (mQuestionBank[mCurrentIndex].isAnswered() == null) {
                    mTrueButton.setEnabled(true);
                    mFalseButton.setEnabled(true);
                } else {
                    mTrueButton.setEnabled(false);
                    mFalseButton.setEnabled(false);
                }
            }
        });

        updateQuestion();
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d(TAG, "onStart() called");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, "onResume() called");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d(TAG, "onPause() called");
    }

    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        Log.i(TAG, "onSaveInstanceState");
        savedInstanceState.putInt(KEY_INDEX, mCurrentIndex);
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d(TAG, "onStop() called");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy() called");
    }

    private void updateQuestion() {
        int question = mQuestionBank[mCurrentIndex].getTextResID();
        mQuestionTextView.setText(question);
    }

    private void checkAnswer(boolean userPressedTrue){
        boolean answerIsTrue = mQuestionBank[mCurrentIndex].isAnswerTrue();

        int messageResID = 0;
        if (userPressedTrue == answerIsTrue) {
            messageResID = R.string.correct_toast;
            mQuestionBank[mCurrentIndex].setAnswered(true);
        } else {
            messageResID = R.string.incorrect_toast;
            mQuestionBank[mCurrentIndex].setAnswered(false);
        }

        Toast toast = Toast.makeText(this,
                messageResID,
                Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.TOP,0,300);
        toast.show();
        mAnsweredQuestion++;

        mTrueButton.setEnabled(false);
        mFalseButton.setEnabled(false);
        if (mAnsweredQuestion == mQuestionBank.length) {
            int correctAnswer = 0;
            for (Question question : mQuestionBank) {
                if (question.isAnswered())
                    correctAnswer++;
            }
            Toast finalToast = Toast.makeText(this,
                    String.valueOf(correctAnswer * 100 / mQuestionBank.length) +
                            "% of correct answers",
                    Toast.LENGTH_LONG);
            finalToast.show();
        }
    }
}