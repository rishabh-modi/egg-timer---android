package com.example.risha.eggtimer;

import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    SeekBar counterSeekBar;
    TextView counterTextView;
    Button counterControlButton;
    boolean activeCounter = false;
    CountDownTimer countDownTimer;

    public void resetTimer()
    {
        counterControlButton.setText("Go");
        counterSeekBar.setProgress(30);
        counterTextView.setText("0:30");
        countDownTimer.cancel();
        counterSeekBar.setEnabled(true);
        activeCounter = false;

    }

    void timerUpdate(int secondsUntilFinished)
    {

        int seconds;
        int minutes;

        minutes = (int) secondsUntilFinished/60;
        seconds = secondsUntilFinished - minutes*60;

        String sec = Integer.toString(seconds);

        if(seconds<=9)
            sec = "0" + sec;

        counterTextView.setText(Integer.toString(minutes) + ":" + sec);
    }


    public void buttonTimerControl(View view)
    {
        if(activeCounter == false) {

            activeCounter = true;
            counterSeekBar.setEnabled(false);
            counterControlButton.setText("Stop !");

            countDownTimer = new CountDownTimer(counterSeekBar.getProgress() * 1000 + 100, 1000) {

                @Override
                public void onTick(long millisUntilFinished) {
                    timerUpdate((int) millisUntilFinished / 1000);
                }

                @Override
                public void onFinish() {

                    resetTimer();
                    MediaPlayer mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.airhorn);
                    mediaPlayer.start();

                }
            }.start();

        }else
        {
            resetTimer();
        }
    }




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        counterSeekBar = (SeekBar)findViewById(R.id.counterSeekBar);
        counterTextView = (TextView)findViewById(R.id.counterTextView);
        counterControlButton = (Button)findViewById(R.id.counterControlButton);


        counterSeekBar.setMax(1200);
        counterSeekBar.setProgress(30);

        counterSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

               timerUpdate(progress);


            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
}
