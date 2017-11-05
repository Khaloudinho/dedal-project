package fr.miage.m2.job;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.io.Serializable;
import java.util.Set;

@Entity
public class Game implements Serializable {

	private Player p, p2;

	@OneToOne
	Points point;

	@OneToMany
	Set<Dice> dices;

	public Game() {
	}

	public Game(Player p, Player p2) {
		this.p = p;
		this.p2 = p2;
	}

	public void start() {
		p.throwDice();
		p2.throwDice();
	}

	public Player getP() {
		return p;
	}

	public void setP(Player p) {
		this.p = p;
	}

	public Player getP2() {
		return p2;
	}

	public void setP2(Player p2) {
		this.p2 = p2;
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
}