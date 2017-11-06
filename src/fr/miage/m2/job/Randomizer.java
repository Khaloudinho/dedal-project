package fr.miage.m2.job;
import java.util.concurrent.ThreadLocalRandom;

public class Randomizer {

	private int value;

	public Randomizer() {
		this.value = (int)Math.random() * ((6 - 1) + 1);
	}

	public int getValue() {
		//this.value=(int)Math.random() * ((6 - 1) + 1);
		this.value=ThreadLocalRandom.current().nextInt(1, 6 + 1);

		//this.value=1;
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