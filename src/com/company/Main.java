package com.company;

import com.company.Game.Game.controller.Controller;
import com.company.Game.Game.controller.StopWatch;
import com.company.Game.view.PausePanel;
import com.company.Game.view.View;

public class Main {

    public static void main(String[] args) {
        View view = new View();
        Controller controller = Controller.getInstance();
        controller.setView(view);
        StopWatch stopWatch = new StopWatch(view);
        controller.stopWatch = stopWatch;
        view.setStopWatch(stopWatch);
        PausePanel pausePanel = PausePanel.getInstance();
        //view.setPausePanel(pausePanel);
        pausePanel.setView(view);



    }
}