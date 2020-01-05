/*package com.company.Game.Game.controller;

import com.company.Game.model.Player;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyActions extends KeyAdapter {

    Player player = Player.getInstance();
    Controller controller = Controller.getInstance();

    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
                if( controller.isValidMove( 0 , -1))
                    player.movePlayer(0, -1);
                player.setDirection("up");
                break;
            case  KeyEvent.VK_DOWN:
                if( controller.isValidMove( 0 , 1))
                    player.movePlayer(0, 1);
                player.setDirection("down");
                break;
            case KeyEvent.VK_LEFT:
                if( controller.isValidMove( -1 , 0))
                    player.movePlayer(-1, 0);
                player.setDirection("left");
                break;
            case KeyEvent.VK_RIGHT:
                if( controller.isValidMove( 1 , 0))
                    player.movePlayer(1, 0);
                player.setDirection("right");
                break;
            case KeyEvent.VK_SPACE:
                if(player.getNumOfBullets() > 0)
                    controller.fireBullet();

        }
        controller.updateInfo();
    }

    public void keyReleased(KeyEvent e){}
    public void keyTyped(KeyEvent e){}
}
*/