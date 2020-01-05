package com.company.Game.Game.controller;

import com.company.Game.view.EndDialog;
import com.company.Game.view.View;

import javax.swing.*;

public class StopWatch {
    private Timer timer;
    private static int timeLeft;

    private View view;

    public boolean isPaused() {
        return paused;
    }

    public void setPaused(boolean paused) {
        this.paused = paused;
    }

    boolean paused; //false by default

    public StopWatch(View view) {
        this.view = view;

        timeLeft = 120; //120 seconds == 2 minutes

        timer = new Timer(1000, e->updateTimer()); //kol 1000 second beynady updateTimer()

    }

    public void updateTimer()
    {
        if( !paused)
        {
            --timeLeft;

            if( timeLeft > 0)
            {
                view.getTimeLbl().setText( String.format("%d:%02d", timeLeft / 60 , timeLeft % 60)); //min: seconds
            }
            else if( timeLeft == 0)
            {
                view.getTimeLbl().setText("0:00");
                view.getGamePanel().setDisabled(true);
                new EndDialog(this , "Time's Up" , view).execute();
                view.getGamePanel().setDisabled(false);
            }
        }
    }

    public static int getTimeLeft() {
        return timeLeft;
    }

    public static void setTimeLeft(int time) {
        timeLeft = time;
    }

    public void reset()
    {
        timer.start();
        paused = false;
        timeLeft = 120;
        updateTimer();
    }
}