package fr.miage.m2.job;

public class Dice {

	private int value;
	private static Randomizer r = new Randomizer();

	public Dice() {
		this.value = r.getValue();
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public void roll() {
		this.value = r.getValue();
	}

}