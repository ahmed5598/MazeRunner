package com.company.Game.model;
import com.company.Game.view.View;

public class UpdatesObserver implements Observer {
    private int health;
    private int score;
    private View view;
    final HealthBar healthBar = HealthBar.getInstance();

    @Override
    public void update(int health,int score) {
        this.health = health;
        this.score = score;
        healthBar.changeValue(health);


    }
}
