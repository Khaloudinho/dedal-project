package fr.miage.m2.job;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.io.Serializable;
import java.util.Set;

@Entity
public class Player implements Serializable {

	private String firstname, lastname;
	private int points;

	@OneToMany
	private Set<Dice> dices;

	@OneToOne
	private Game game;

	public Player (){

	}

	/**
	 * 
	 * @param firstname
	 * @param lastname
	 */
	public Player(String firstname, String lastname, Set<Dice> dices) {
		this.firstname = firstname;
		this.lastname = lastname;
		this.dices = dices;
		this.points = 0;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}

	public void throwDice() {
		for (Dice dice:
			 this.dices) {
			dice.roll();
		}
	}

	public Set<Dice> getDices() {
		return dices;
	}

	public void setDices(Set<Dice> dices) {
		this.dices = dices;
	}

	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}

	public String toString(){
		return "Player - Firstname : " + this.firstname + " - Lastname : " + this.lastname + " - Points : " + this.points + "\n";
	}
}