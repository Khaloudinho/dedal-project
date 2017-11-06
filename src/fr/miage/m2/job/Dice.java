package fr.miage.m2.job;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
public class Dice implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int Id;

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

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public static Randomizer getR() {
		return r;
	}

	public static void setR(Randomizer r) {
		Dice.r = r;
	}
}