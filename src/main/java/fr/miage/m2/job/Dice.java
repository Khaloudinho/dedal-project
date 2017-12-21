package fr.miage.m2.job;

import java.io.Serializable;
import java.util.Observable;

/**
 * Class wich represent a Dice
 */
public class Dice extends Observable implements Serializable {

    private static Randomizer r = new Randomizer();
    private int value;

    public Dice() {
        this.value = r.getValue();
    }

    public int getValue() {
        setChanged();
        notifyObservers(value);
        return value;
    }

    public int roll() {
        this.value = r.getValue();
        return this.value;
    }

}