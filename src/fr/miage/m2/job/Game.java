package fr.miage.m2.job;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
public class Game implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int Id;

	@OneToMany
	private Set<Player> players;

	@OneToOne
	Points point;

	@OneToMany
	Set<Dice> dices;

	public Game() {
	}

	public Game(Set<Player> players) {
		this.players = players;
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

	public Set<Dice> getDices() {
		return dices;
	}

	public void setDices(Set<Dice> dices) {
		this.dices = dices;
	}

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public Set<Player> getPlayers() {
		return players;
	}

	public void setPlayers(Set<Player> players) {
		this.players = players;
	}
}