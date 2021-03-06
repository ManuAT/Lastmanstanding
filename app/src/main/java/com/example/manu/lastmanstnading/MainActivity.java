package com.example.manu.lastmanstnading;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.manu.lastmanstnading.R;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private static final long START_TIME_IN_MILLIS = 7200000;
    private static final long add_time = 300000;
    private TextView mTextViewCountDown;
    private TextView unlock_key;
    private Button mButtonStartPause;
    private Button mButtonReset;
    private  Button uk1;
    private  Button uk2;
    private  Button finish;
    private  Button qrbtn;
    private  Button homebtn;
    private Button  tplus;
    private Button  tminus;
    RelativeLayout main;
    RelativeLayout home;
    RelativeLayout hint;
    RelativeLayout map;
    RelativeLayout video;
    RelativeLayout unlock1;
    RelativeLayout unlock2;
    RelativeLayout finshed;
    RelativeLayout qrcode;

    private CountDownTimer mCountDownTimer;

    private boolean mTimerRunning;

    public long mTimeLeftInMillis = START_TIME_IN_MILLIS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        //setContentView(R.layout.activity_page2);

        mTextViewCountDown = findViewById(R.id.txt2);

        mButtonStartPause = findViewById(R.id.button_start_pause);
        mButtonReset = findViewById(R.id.button_reset);
        uk1 = findViewById(R.id.sbtn);
        uk2 = findViewById(R.id.unkey);
        finish= findViewById(R.id.finish);
        unlock_key = findViewById(R.id.untxt);
        qrbtn = findViewById(R.id.qbtn);
        homebtn = findViewById(R.id.homebtn);
        tplus = findViewById(R.id.tplus);
        tminus = findViewById(R.id.tminus);
        home =(RelativeLayout)findViewById(R.id.home);
        hint =(RelativeLayout)findViewById(R.id.hint);
        map =(RelativeLayout)findViewById(R.id.map);
        video = (RelativeLayout)findViewById(R.id.video);
        unlock1= (RelativeLayout)findViewById(R.id.unlock1);
        unlock2 = (RelativeLayout)findViewById(R.id.unlock2);
        main = (RelativeLayout)findViewById(R.id.main);
        finshed = (RelativeLayout)findViewById(R.id.finished);
        qrcode = (RelativeLayout)findViewById(R.id.qrcode);


        mButtonStartPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mTimerRunning) {
                    pauseTimer();
                } else {
                    startTimer();
                }
            }
        });

        mButtonReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetTimer();
            }
        });
        /*uk1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,unlock.class);
                startActivity(intent);
            }
        });*/
        uk1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                home.setVisibility(View.GONE);
                unlock1.setVisibility(View.VISIBLE);
            }
        });
        homebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                home.setVisibility(View.VISIBLE);
                unlock1.setVisibility(View.GONE);
                unlock2.setVisibility(View.GONE);
                hint.setVisibility(View.GONE);
                map.setVisibility(View.GONE);
                video.setVisibility(View.GONE);
                qrcode.setVisibility(View.GONE);
                unlock_key.setText("");
            }
        });
        qrbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                home.setVisibility(View.GONE);
                qrcode.setVisibility(View.VISIBLE);
            }
        });
        uk2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(unlock_key.getText().toString().equals("777"))
                {
                unlock1.setVisibility(View.GONE);
                unlock_key.setText("");
                unlock2.setVisibility(View.VISIBLE);}
            }
        });
        finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                main.setVisibility(View.GONE);
                home.setVisibility(View.GONE);
                finshed.setVisibility(View.VISIBLE);
            }
        });
        tplus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                plus_time();
            }
        });
        tminus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                minus_time();
            }
        });

        startTimer();
        updateCountDownText();


    }

    private void startTimer() {
        mCountDownTimer = new CountDownTimer(mTimeLeftInMillis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                mTimeLeftInMillis = millisUntilFinished;
                updateCountDownText();
            }

            @Override
            public void onFinish() {
                mTimerRunning = false;
                mButtonStartPause.setText("Start");
                mButtonStartPause.setVisibility(View.INVISIBLE);
                mButtonReset.setVisibility(View.VISIBLE);
            }
        }.start();

        mTimerRunning = true;
        mButtonStartPause.setText("pause");
        mButtonReset.setVisibility(View.INVISIBLE);
    }

    private void pauseTimer() {
        mCountDownTimer.cancel();
        mTimerRunning = false;
        mButtonStartPause.setText("Start");
        mButtonReset.setVisibility(View.VISIBLE);
    }
    private void plus_time(){
        pauseTimer();
        mTimeLeftInMillis = mTimeLeftInMillis + add_time;
        startTimer();

    }
    private  void minus_time(){
        pauseTimer();
        mTimeLeftInMillis = mTimeLeftInMillis - add_time;
        startTimer();

    }

    private void resetTimer() {
        mTimeLeftInMillis = START_TIME_IN_MILLIS;
        updateCountDownText();
        mButtonReset.setVisibility(View.INVISIBLE);
        mButtonStartPause.setVisibility(View.VISIBLE);
    }

    private void updateCountDownText() {
        int hours = (int) (mTimeLeftInMillis / 1000)/ (60*60);
        int minutes = (int) ((mTimeLeftInMillis / 1000)%(60*60) )/ 60;
        int seconds = (int) (mTimeLeftInMillis / 1000) % 60;

        String timeLeftFormatted = String.format(Locale.getDefault(), "%02d:%02d:%02d",hours ,minutes, seconds);

        mTextViewCountDown.setText(timeLeftFormatted);
    }
}
