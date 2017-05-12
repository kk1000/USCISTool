package uscis.teknepal.com.uscistool;

import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

//APP DUE MAY 16th at midnight..

public class Home extends AppCompatActivity {

    public int highScore = 0;
    private Question mQuestion = new Question();
    private TextView mScoreView;
    private TextView mQuestionView;
    private TextView score_text;
    private Button mButtonChoice1;
    private Button mButtonChoice2;
    private Button mButtonChoice3;
    private Button mButtonChoice4;
    private String mAnswer;
    private int mScore = 0;
    private int mQuestionNumber = 0;

    private Context context = this;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:

                    Intent intentHome = new Intent(Home.this, Home.class);
                    // Launch the Activity using the intent

                    ActivityOptions options = ActivityOptions.makeCustomAnimation(context, R.anim.abc_slide_in_bottom, R.anim.abc_slide_in_bottom);
                    //Home.this.overridePendingTransition(R.anim.abc_slide_in_bottom,R.anim.abc_slide_out_bottom);
                    context.startActivity(intentHome, options.toBundle());
                    return true;
                case R.id.navigation_dashboard:
                    Intent intent = new Intent(Home.this, CaseStatus.class);
                    ActivityOptions option = ActivityOptions.makeCustomAnimation(context, R.anim.abc_slide_in_bottom, R.anim.abc_slide_in_bottom);
                    // Launch the Activity using the intent

                    context.startActivity(intent, option.toBundle());
                    // Home.this.overridePendingTransition(R.anim.abc_slide_in_bottom,R.anim.abc_slide_out_bottom);
                    return true;
                case R.id.navigation_notifications:
                    Intent notification = new Intent(Home.this, Notification.class);
                    ActivityOptions opt = ActivityOptions.makeCustomAnimation(context, R.anim.abc_slide_in_top, R.anim.abc_slide_in_top);

                    // Launch the Activity using the intent
                    context.startActivity(notification, opt.toBundle());
                    // Home.this.overridePendingTransition(R.anim.abc_slide_in_top ,R.anim.abc_slide_out_top);
                    return true;
            }
            return false;
        }

    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);

        mScoreView = (TextView) findViewById(R.id.score);
        mQuestionView = (TextView) findViewById(R.id.question);
        mButtonChoice1 = (Button) findViewById(R.id.choice1);
        mButtonChoice2 = (Button) findViewById(R.id.choice2);
        mButtonChoice3 = (Button) findViewById(R.id.choice3);
        mButtonChoice4 = (Button) findViewById(R.id.quit);
        score_text = (TextView) findViewById(R.id.score_text);
        //mButtonquit.setVisibility(View.INVISIBLE);

        updateQuestion();

        //  mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        //Start of Button Listener for Button1
        mButtonChoice1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//My logic for Button goes in here
                if (mButtonChoice1.getText() == mAnswer) {
                    mScore = mScore + 1;
                    updateScore(mScore);
                    setHighScore(mScore);
                    updateQuestion();

                } else {
                    // Toast.makeText(Home.this, "wrong", Toast.LENGTH_SHORT).show();
                    updateQuestion();
                }
            }
        });

        //Start of Button Listener for Button2
        mButtonChoice2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mButtonChoice2.getText() == mAnswer) {
                    mScore = mScore + 1;
                    updateScore(mScore);
                    setHighScore(mScore);
                    updateQuestion();


                } else {
                    //Toast.makeText(Home.this, "wrong", Toast.LENGTH_SHORT).show();
                    updateQuestion();
                }
            }
        });


        //Start of Button Listener for Button3
        mButtonChoice3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //My logic for Button goes in here

                if (mButtonChoice3.getText() == mAnswer) {
                    mScore = mScore + 1;
                    updateScore(mScore);
                    setHighScore(mScore);
                    updateQuestion();
                } else {
                    // Toast.makeText(Home.this, "wrong", Toast.LENGTH_SHORT).show();
                    updateQuestion();
                }
            }
        });//End of Button Listener for Button3

        mButtonChoice4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //My logic for Button goes in here

                if (mButtonChoice4.getText() == mAnswer) {
                    mScore = mScore + 1;
                    updateScore(mScore);
                    setHighScore(mScore);
                    updateQuestion();

                } else {
                    // Toast.makeText(Home.this, "wrong", Toast.LENGTH_SHORT).show();
                    updateQuestion();
                }
            }
        });
        //End of Button Listener for Button4

    }

    private void updateQuestion() {
        if (mQuestionNumber < 21) {
            Log.d("", "" + mQuestionNumber);
            Random rand = new Random();
            // int  n = rand.nextInt(20) + 1;
            int n = this.randomNum();
            mQuestionView.setText(mQuestion.getQuestion(n));
            mButtonChoice1.setText(mQuestion.getChoice1(n));
            mButtonChoice2.setText(mQuestion.getChoice2(n));
            mButtonChoice3.setText(mQuestion.getChoice3(n));
            mButtonChoice4.setText(mQuestion.getChoice4(n));
            //mButtonChoice1.setBackgroundColor(Color.RED);

            mAnswer = mQuestion.getCorrectAnswer(n);
            mQuestionNumber++;
        } else {
            quizEnd();
        }

    }

    private void updateScore(int score) {
        mScoreView.setText(String.format("%d", score));
    }

    private void setHighScore(int score) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        int high = preferences.getInt("HighScore", 0);
        if (score >= high) {
            SharedPreferences.Editor editor = preferences.edit();
            editor.putInt("HighScore", score);
            editor.apply();
        }
        else{
        }
    }


    private void quizEnd() {
        Toast.makeText(Home.this, "End", Toast.LENGTH_SHORT).show();
        //updateScore();
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        int highScore = preferences.getInt("HighScore", 0);

        mButtonChoice1.setVisibility(View.INVISIBLE);
        mButtonChoice2.setVisibility(View.INVISIBLE);
        mButtonChoice3.setVisibility(View.INVISIBLE);
        mButtonChoice4.setVisibility(View.INVISIBLE);
        mScoreView.setVisibility(View.INVISIBLE);

        score_text.setText("Result:");
        score_text.setAllCaps(true);
        score_text.setTextColor(Color.RED);
        score_text.setTextSize(50);
        score_text.setGravity(Gravity.CENTER_HORIZONTAL);
        String finalText = "Your Final Score is " + mScore + "\nYour All time high is " + highScore;
        mQuestionView.setText(finalText);
        mQuestionView.setAllCaps(true);
        mQuestionView.setTextColor(Color.RED);

    }

    private int randomNum() {
        ArrayList<Integer> list = new ArrayList<Integer>();
        int j = 0;
        for (int i = 1; i < 21; i++) {
            list.add(i);
        }
        Collections.shuffle(list);
        for (int i = 0; i < 1; i++) {
            j = list.get(i);
        }
        return j;
    }
}