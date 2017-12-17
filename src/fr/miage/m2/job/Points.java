package fr.miage.m2.job;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Class wich represent the Points system
 * no real need but was in inital conception
 */
@Entity
public class Points implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int Id;

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

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}
}