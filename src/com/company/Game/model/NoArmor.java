package com.company.Game.model;

public class NoArmor implements PlayerState {
    Player player;

    public NoArmor(Player newPlayer)
    {
        player = newPlayer;
    }

    @Override
    public boolean move() {

        player.setPlayerState(player.getNoArmor());
        return false;


    }
}
