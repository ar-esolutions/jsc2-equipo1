package com.esolutions.trainings.jsc2.logic;


import com.esolutions.trainings.jsc2.model.JPAReserva;
import com.esolutions.trainings.jsc2.model.JPARoom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@Service
public class ReservaRepeatedService {
	private final JpaRepository<JPAReserva, Long> repository;

	@Autowired
	public ReservaRepeatedService(JpaRepository<JPAReserva, Long> repository) {
		this.repository = repository;
	}

	public void guardarReserva(int floor, int nro, String checkIn, String checkout){
		JPAReserva r = new JPAReserva();

		r.setFloor(floor);
		r.setNro(nro);
		r.setFecha_entrada(parseFecha(checkIn));
		r.setFecha_salida(parseFecha(checkout));
		repository.save(r);

	}

	public Date parseFecha(String stfecha){

		SimpleDateFormat formatoDelTexto = new SimpleDateFormat("yyyyMMdd");
		String strFecha = stfecha;
		Date fecha = null;

		try {
			fecha = formatoDelTexto.parse(strFecha);

		} catch (ParseException ex) {

			ex.printStackTrace();

		}
		return fecha;
	}


	public List<JPAReserva> buscarReserva(int floor, int room) {

		Date fecha_entrada = null;
		List <JPAReserva> reservas = null;
		JPAReserva newReserva = new JPAReserva();

		final List<JPAReserva> allJPAReserva = this.repository.findAll();
		Iterator<JPAReserva> i = allJPAReserva.iterator();
		JPAReserva r = null;
		while(i.hasNext() ){
			r = i.next();
			if (r.getFloor() == floor && r.getNro() == room){
				newReserva.setNro(room);
				newReserva.setFloor(floor);
				newReserva.setFecha_salida(r.getFecha_salida());
				newReserva.setFecha_entrada(r.getFecha_entrada());
				reservas.add(newReserva);

			}

		}
		return reservas;

	}

}
