package fr.miage.m2.job;

public class Randomizer {

	private int value;

	public Randomizer() {
		this.value = (int)Math.random() * ((6 - 1) + 1);
	}

	public int getValue() {
		return value;
	}

	/**
	 * 
	 * @param value
	 */
	public void setValue(int value) {
		this.value = value;
	}

}