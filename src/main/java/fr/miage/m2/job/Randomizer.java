package fr.miage.m2.job;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Class use in order to generate numbers for dices.
 */
public class Randomizer {

    private int value;

    public Randomizer() {
        this.value = (int) Math.random() * ((6 - 1) + 1);
    }

    public int getValue() {
        this.value = ThreadLocalRandom.current().nextInt(1, 6 + 1);

        return value;
    }

}