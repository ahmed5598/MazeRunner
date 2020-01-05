package com.company.Game.model;

public class HasArmor implements PlayerState {

    Player player;

    public HasArmor(Player newPlayer)
    {
        player = newPlayer;
    }

    @Override
    public boolean move() {
        player.setPlayerState(player.getNoArmor());
        return true;
    }
}
