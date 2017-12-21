package fr.miage.m2.job;

import java.io.Serializable;
import java.util.List;
import java.util.Observer;

/**
 * Class wich represents a player (model)
 */
public class Player implements Serializable, Observer {

    private String firstname, lastname;
    private int points;

    private List<Dice> dices;

    public Player() {
    }

    /**
     * Construct a Player
     *
     * @param firstname
     * @param lastname
     * @param dices     of the player
     */
    public Player(String firstname, String lastname, List<Dice> dices) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.dices = dices;
        this.points = 0;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    /**
     * Throw dices of the player
     *
     * @return
     */
    public int[] throwDice() {
        int[] results = new int[2];
        for (int i = 0; i < dices.size(); i++) {
            results[i] = dices.get(i).roll();
        }
        return results;
    }

    public void setDices(List<Dice> dices) {
        this.dices = dices;
    }

    @Override
    public String toString() {
        return "Player - Firstname : " + this.firstname + " - Lastname : " + this.lastname + " - Points : " + this.points + "\n";
    }

    // We follow observer pattern
    @Override
    public void update(java.util.Observable o, Object arg) {
        System.out.println(this.lastname + "_" + this.firstname + " voit la valeur " +
                ((Integer) arg).intValue() + " d'un de");
    }
}