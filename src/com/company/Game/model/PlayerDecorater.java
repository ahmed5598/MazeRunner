package com.company.Game.model;

public abstract class PlayerDecorater implements PlayerInterface {

    protected PlayerInterface player;

    public PlayerDecorater(PlayerInterface Player)
    {
        player = Player;
    }

}
