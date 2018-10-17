package com.esolutions.trainings.jsc2.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ROOMS")
public class JPARoom {
	@Id
	private Long id;

	@Column(name = "type", nullable = false, unique = true)
	private String type;

	@Column(name = "floor", nullable = false, unique = true)
	private int floor;

	@Column(name = "nro", nullable = false, unique = true)
	private int nro;

	private JPARoom() {

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getFloor() { return floor; }


	public int getNro() { return nro; }

}
