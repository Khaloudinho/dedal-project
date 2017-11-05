package fr.miage.m2.job;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import java.io.Serializable;

@Entity
public class Entry implements Serializable {

	private String name;
	private int points;

	@OneToOne
	Points point;

	public Entry() {
	}

	/**
	 * 
	 * @param name
	 * @param points
	 */
	public Entry(String name, int points) {
		this.name = name;
		this.points = points;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}

	public Points getPoint() {
		return point;
	}

	public void setPoint(Points point) {
		this.point = point;
	}
}