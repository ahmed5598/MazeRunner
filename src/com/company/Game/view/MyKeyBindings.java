package com.company.Game.view;

import com.company.Game.Game.controller.Controller;
import com.company.Game.model.Player;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * Created by FAST on 11-May-18.
 */
public class MyKeyBindings extends AbstractAction{

    Player player = Player.getInstance();
    Controller controller = Controller.getInstance();

    private String cmd;

    public MyKeyBindings(String cmd) {
        this.cmd = cmd;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        controller.getView().getGamePanel().requestFocusInWindow();
        if( !controller.getView().getGamePanel().isDisabled())
        {
            switch (cmd)
            {
                case "LeftArrow":
                    if( controller.isValidMove( -1 , 0))
                        player.movePlayer(-1, 0);
                    player.setDirection("left");
                    break;
                case "RightArrow":
                    if( controller.isValidMove( 1 , 0))
                        player.movePlayer(1, 0);
                    player.setDirection("right");
                    break;
                case "UpArrow":
                    if( controller.isValidMove( 0 , -1))
                        player.movePlayer(0, -1);
                    player.setDirection("up");
                    break;
                case "DownArrow":
                    if( controller.isValidMove( 0 , 1))
                        player.movePlayer(0, 1);
                    player.setDirection("down");
                    break;
                case "Space":
                    System.out.println("User pressed space");
                    System.out.println(player.getNumOfBullets());
                    if(player.getNumOfBullets() > 0)
                        controller.fireBullet();
                    break;
            }
            controller.updateInfo();
        }

    }
}
