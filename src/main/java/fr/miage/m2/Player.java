package fr.miage.m2;

public class Player {

	private String firstname;
	private String lastname;
	private int points;

	public Player (){

	}

	/**
	 * 
	 * @param firstname
	 * @param lastname
	 */
	public Player(String firstname, String lastname) {
		this.firstname = firstname;
		this.lastname = lastname;
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

	}

	public String toString(){
		return "Player - Firstname : " + this.firstname + " - Lastname : " + this.lastname + " - Points : " + this.points;
	}
}