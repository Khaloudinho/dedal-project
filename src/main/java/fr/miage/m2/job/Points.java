package fr.miage.m2.job;

import java.io.Serializable;

/**
 * Class wich represents the Points system
 * no real need but it was planned in initial conception
 */
public class Points implements Serializable {

    private int points;

    public Points() {

    }

    /**
     * Constructs points
     *
     * @param points used for scoring system
     */
    public Points(int points) {
        this.points = points;
    }

    public int getPoints() {
        return points;
    }
}