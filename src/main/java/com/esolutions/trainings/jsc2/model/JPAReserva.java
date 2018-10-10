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

	@Column(name = "fecha_entrada", nullable = false, unique = true)
	private Date fecha_entrada;


	@Column(name = "fecha_salida", nullable = false, unique = true)
	private Date fecha_salida;

	public JPAReserva(int id, int floor, int nro, Date fecha_entrada, Date fecha_salida) {
		this.id = id;
		this.floor = floor;
		this.nro = nro;
		this.fecha_entrada = fecha_entrada;
		this.fecha_salida = fecha_salida;
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

	public Date getFecha_entrada() {
		return fecha_entrada;
	}

	public void setFecha_entrada(Date fecha_entrada) {
		this.fecha_entrada = fecha_entrada;
	}

	public Date getFecha_salida() {
		return fecha_salida;
	}

	public void setFecha_salida(Date fecha_salida) {
		this.fecha_salida = fecha_salida;
	}
}
