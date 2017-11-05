package fr.miage.m2.job;

import javax.persistence.Entity;
import java.io.Serializable;

@Entity
public class Points implements Serializable {

	private int points;

	public Points(){

	}

	public Points(int points) {
		this.points = points;
	}

	/**
	 * 
	 * @param name
	 * @param points
	 */
	public void add(String name, int points) {

	}

}