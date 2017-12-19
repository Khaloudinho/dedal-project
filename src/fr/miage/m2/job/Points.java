package fr.miage.m2.job;

import java.io.Serializable;

/**
 * Class wich represent the Points system
 * no real need but was in inital conception
 */
public class Points implements Serializable {

	private int points;

	public Points(){

	}

	public Points(int points) {
		this.points = points;
	}

	public int getPoints() {
		return points;
	}
}