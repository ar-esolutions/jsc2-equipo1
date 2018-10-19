package com.esolutions.trainings.jsc2.logic;


import com.esolutions.trainings.jsc2.model.JPAReserva;
import com.esolutions.trainings.jsc2.web.ReservationResponse;
import org.apache.logging.log4j.LoggingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class ReservaRepeatedService {
	private final JpaRepository<JPAReserva, Long> repository;
	private final RoomRepeatedService roomService;
	private static Logger logger = Logger.getLogger(LoggingException.class.getName());

	@Autowired
	public ReservaRepeatedService(JpaRepository<JPAReserva, Long> repository, RoomRepeatedService roomService) {
		this.repository = repository;
		this.roomService = roomService;
	}


	private void guardarReserva(int floor, int nro, String checkIn, String checkout) {
		JPAReserva r = new JPAReserva();

		r.setFloor(floor);
		r.setNro(nro);
		r.setFechaEntrada(parseFecha(checkIn));
		r.setFechaSalida(parseFecha(checkout));
		repository.save(r);

	}

	private Date parseFecha(String stfecha) {

		SimpleDateFormat formatoDelTexto = new SimpleDateFormat("yyyy-MM-dd");
		Date fecha = null;


		try {
			fecha = formatoDelTexto.parse(stfecha);
		} catch (ParseException e) {
			if (logger.isLoggable(Level.SEVERE)) {
				logger.log(Level.SEVERE, "Error parsing date", e);
			}
		}

		return fecha;
	}

    private Calendar dateToCalendar(Date date) {

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar;

    }

	private boolean validarIntervaloDeReserva(List<JPAReserva> reservas, Calendar fechaDesdeConsulta,
                                              Calendar fechaHastaConsulta){
        Iterator<JPAReserva> i = reservas.iterator();
        JPAReserva r = null;
        boolean flag = true;
        while(i.hasNext() ){
            r = i.next();
            Calendar fechaDesde = dateToCalendar(r.getFechaEntrada());
            Calendar fechaHasta = dateToCalendar(r.getFechaSalida());
            if(!validarIntervaloFechas(fechaDesde, fechaHasta,fechaDesdeConsulta, fechaHastaConsulta)){
                flag = false;
            }
        }
        return flag;
    }

	private boolean validarIntervaloFechas(Calendar fechaDesdeReserva, Calendar fechaHastaReserva,
                                      Calendar fechaDesdeConsulta, Calendar fechaHastaConsulta){
		return (fechaHastaConsulta.compareTo(fechaDesdeReserva) < 0) || ((fechaHastaReserva.compareTo(fechaDesdeConsulta)) < 0);
    }


	private List<JPAReserva> buscarReserva(int floor, int room) {
		//Esto no estaba inicializado , por eso tiraba null exception
		List <JPAReserva> reservas = new ArrayList<>();

		final List<JPAReserva> allJPAReserva = this.repository.findAll();
		Iterator<JPAReserva> i = allJPAReserva.iterator();
		JPAReserva r = null;
		while(i.hasNext() ){
			r = i.next();
			if (r.getFloor() == floor && r.getNro() == room){
				reservas.add(r);

			}

		}
		return reservas;
	}

	private boolean validarReserva(int floor, int room, String desde, String hasta) {

		List<JPAReserva> reservas = buscarReserva(floor, room);

		if(reservas.isEmpty()){ //No existe reserva

			guardarReserva(floor, room, desde, hasta); //Crea reserva

			return true;

		}else{

			Calendar cDesde = Calendar.getInstance();
			Calendar cHasta = Calendar.getInstance();

			this.parseCalendar(convertir(desde),cDesde);
			this.parseCalendar(convertir(hasta),cHasta);

			if(!validarIntervaloDeReserva(reservas, cDesde, cHasta)){
				return false;
			}

			guardarReserva(floor, room, desde, hasta); //Crea reserva

			return true;
		}

	}

	private void parseCalendar(int[] fecha,Calendar calendar){

		calendar.set(fecha[0], fecha[1] - 1, fecha[2]);
	}

	private int[] convertir(String fecha){

		int[] aux = new int[3];

		String[] a = fecha.split("-");

		for(int i = 0;i < a.length; i++){

			aux[i] = Integer.parseInt(a[i].trim());
		}

		return aux;
	}


	public ReservationResponse getReservationResponse(int floor, int room, Map<String, String> parm) {
		ReservationResponse reservationResponse = new ReservationResponse();
		//Trae lo que hay escrito en el body
		String checkIn = parm.get("checkIn");
		String checkOut = parm.get("checkOut");


		boolean reserved = validarReserva(floor, room, checkIn, checkOut);
		String tipo = roomService.busquedaRooms(floor,room);
		BigDecimal precio = roomService.precioDeReserva(checkIn, checkOut, tipo);

		reservationResponse.setBooked(reserved);
		reservationResponse.setPrice(precio);

		return reservationResponse;
	}

}
