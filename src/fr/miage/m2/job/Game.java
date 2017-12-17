package fr.miage.m2.job;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Game implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int Id;

	@OneToOne
	private Player player = new Player();

	@OneToOne
	Points point;

	@OneToMany
	List<Dice> dices = new ArrayList<Dice>();

	private int indexCurrentPlayer=0;

	private int NUMBER_OF_TURN=10;
	private int currentTurn=0;

	private static class GameWrapper{
		private static Game instanceGame = new Game();
	}

	public static Game getInstance(){
		return GameWrapper.instanceGame;
	}

	public Game() {
	}

	public void start() {
		player.throwDice();
	}

	public Points getPoints() {
		return point;
	}

	public void setPoints(Points point) {
		this.point = point;
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

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public int getIndexCurrentPlayer() {
		return indexCurrentPlayer;
	}

	public void setIndexCurrentPlayer(int indexCurrentPlayer) {
		this.indexCurrentPlayer = indexCurrentPlayer;
	}

	public int getNUMBER_OF_TURN() {
		return NUMBER_OF_TURN;
	}

	public void setNUMBER_OF_TURN(int NUMBER_OF_TURN) {
		this.NUMBER_OF_TURN = NUMBER_OF_TURN;
	}

	public int getCurrentTurn() {
		return currentTurn;
	}

	public void setCurrentTurn(int currentTurn) {
		this.currentTurn = currentTurn;
	}

	public void doTurn(){
		Player player = this.getCurrentPlayer();
		System.out.println(player.getFirstname()+"_"+player.getLastname()+" joue..");
		this.currentTurn++;
	}

	public Player getCurrentPlayer(){
		return this.player;
	}
}