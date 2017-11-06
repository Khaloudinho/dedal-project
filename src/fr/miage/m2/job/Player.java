package fr.miage.m2.job;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
public class Player implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int Id;

	private String firstname, lastname;
	private int points;

	@OneToMany
	private List<Dice> dices;

	@OneToOne
	private Game game;

	//private boolean canPlay;

	public Player () {}

	/**
	 * 
	 * @param firstname
	 * @param lastname
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

	public int[] throwDice() {
		int [] results = new int[2];
		for (int i=0; i < dices.size(); i++) {
			results[i] = dices.get(i).roll();
		}
		return results;
	}

	public List<Dice> getDices() {
		return dices;
	}

	public void setDices(List<Dice> dices) {
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

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	/*public boolean isCanPlay() {
		return canPlay;
	}

	public void setCanPlay(boolean canPlay) {
		this.canPlay = canPlay;
	}*/
}