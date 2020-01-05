package com.company.Game.model;


public class Armor extends PlayerDecorater {


    public Armor(PlayerInterface Player) {
        super(Player);

        player.setPlayerState(((Player) player).getHasArmor());
    }


    @Override
    public void setPlayerState(PlayerState newPlayerState) {

    }

    @Override
    public boolean move() {
        return player.move();
    }
}
