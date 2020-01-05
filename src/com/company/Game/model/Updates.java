package com.company.Game.model;

public class Updates implements Subject {

    private int health;
    private int score;
    private UpdatesObserver observer;



    public void notifyObserver()
    {
        observer.update(health,score);
    }
    public void register(UpdatesObserver o) {
        this.observer = o;


    }

    @Override
    public void register(Observer o) {

    }

    @Override
    public void unregister(Observer o) {

    }
    public void setUpdates(int health,int score)
    {
        this.health = health;
        this.score = score;
        notifyObserver();

    }

}
