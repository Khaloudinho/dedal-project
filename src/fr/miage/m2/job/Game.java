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

	@OneToMany
	private List<Player> players = new ArrayList<Player>();

	@OneToOne
	Points point;

	@OneToMany
	List<Dice> dices = new ArrayList<Dice>();

	private int indexCurrentPlayer=0;

	private static class GameWrapper{
		private static Game instanceGame = new Game();
	}

	public static Game getInstance(){
		return GameWrapper.instanceGame;
	}

	public Game() {
	}

	public void start() {
		for (Player player:
			 this.players) {
			player.throwDice();
		}
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

	public List<Player> getPlayers() {
		return players;
	}

	public void setPlayers(List<Player> players) {
		this.players = players;
	}

	public void determineFutureGamerIndex(){
		if(this.indexCurrentPlayer<players.size()-1){
			this.indexCurrentPlayer++;
		}else{
			this.indexCurrentPlayer=0;
		}
	}

	public void doTurn(){
		//this.joueurCourant.reseterCapaciteJoueur();
		this.determineFutureGamerIndex();
		System.out.println(this.getCurrentPlayer().getFirstname()+" joue..");
	}

	public Player getCurrentPlayer(){
		return players.get(indexCurrentPlayer);
	}
}