package fr.miage.m2.job;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Class which represents a Game
 * It is in charge of manage turn system and link with the player
 */
public class Game implements Serializable {

    Points point;
    List<Dice> dices = new ArrayList<Dice>();
    private Player player = new Player();
    private int NUMBER_OF_TURN = 10;
    private int currentTurn = 0;

    private Game() {
    }

    public static Game getInstance() {
        return GameWrapper.instanceGame;
    }

    public Points getPoint() {
        return point;
    }

    public void setPoint(Points point) {
        this.point = point;
    }

    public List<Dice> getDices() {
        return dices;
    }

    public void setDices(List<Dice> dices) {
        this.dices = dices;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public int getNUMBER_OF_TURN() {
        return NUMBER_OF_TURN;
    }

    public int getCurrentTurn() {
        return currentTurn;
    }

    public Player getCurrentPlayer() {
        return this.player;
    }

    /**
     * Method which computes a turn
     */
    public void doTurn() {
        Player player = this.getCurrentPlayer();
        System.out.println(player.getFirstname() + "_" + player.getLastname() + " joue..");
        this.currentTurn++;
    }

    /**
     * Singleton implementation for Game
     */
    private static class GameWrapper {
        private static Game instanceGame = new Game();
    }
}