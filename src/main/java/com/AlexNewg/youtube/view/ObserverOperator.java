package com.AlexNewg.youtube.view;

import java.util.ArrayList;
import java.util.List;

public class ObserverOperator implements Subject {

    private List<Observer> observers;

    public ObserverOperator() {
        observers = new ArrayList<>();
    }

    @Override
    public void register(Observer newObserver) {
        observers.add(newObserver);
    }

    @Override
    public void notifyObserver() {
        for(Observer observer : observers){
             observer.update();
        }
    }

}
