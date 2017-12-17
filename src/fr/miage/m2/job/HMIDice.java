package fr.miage.m2.job;

import java.util.Observable;
import java.util.Observer;

/**
 * Class wich is an implementation of Observable
 * Never use because of JAVA FX system
 */
public class HMIDice extends Observable {

    private int id;
    private int value;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value){
        this.value = value;
        setChanged();
        notifyObservers(this.value);
    }

    public String toString(){
        return "Dice - Id : " + this.id + " - Value : " + this.value + "\n";
    }

    @Override
    public synchronized void addObserver(Observer o) {
        super.addObserver(o);
    }
}