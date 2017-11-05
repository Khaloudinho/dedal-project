package fr.miage.m2.job;

import javax.persistence.Entity;
import java.io.Serializable;

@Entity
public class Dice implements Serializable {

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