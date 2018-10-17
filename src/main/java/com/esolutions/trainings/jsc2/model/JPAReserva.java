package com.esolutions.trainings.jsc2.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "RESERVATION")
public class JPAReserva {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "floor", nullable = false, unique = true)
	private int floor;

	@Column(name = "nro", nullable = false, unique = true)
	private int nro;

	@Column(name = "fechaEntrada", nullable = false, unique = true)
	private Date fechaEntrada;


	@Column(name = "fechaSalida", nullable = false, unique = true)
	private Date fechaSalida;

	public JPAReserva(int id, int floor, int nro, Date fechaEntrada, Date fechaSalida) {
		this.id = id;
		this.floor = floor;
		this.nro = nro;
		this.fechaEntrada = fechaEntrada;
		this.fechaSalida = fechaSalida;
	}
	public JPAReserva() {

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getFloor() {
		return floor;
	}

	public void setFloor(int floor) {
		this.floor = floor;
	}

	public int getNro() {
		return nro;
	}

	public void setNro(int nro) {
		this.nro = nro;
	}

	public Date getFechaEntrada() {
		return fechaEntrada;
	}

	public void setFechaEntrada(Date fechaEntrada) {
		this.fechaEntrada = fechaEntrada;
	}

	public Date getFechaSalida() {
		return fechaSalida;
	}

	public void setFechaSalida(Date fechaSalida) {
		this.fechaSalida = fechaSalida;
	}
}
