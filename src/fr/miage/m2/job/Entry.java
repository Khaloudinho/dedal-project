package fr.miage.m2.job;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Class wich will use for storage
 */
@Entity
public class Entry implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int Id;

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

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}
}