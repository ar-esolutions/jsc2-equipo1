package com.esolutions.trainings.jsc2.logic;


import com.esolutions.trainings.jsc2.model.JPAReserva;
import com.esolutions.trainings.jsc2.model.JPARoom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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
            Calendar fechaDesde = dateToCalendar(r.getFecha_entrada());
            Calendar fechaHasta = dateToCalendar(r.getFecha_salida());
            if(!validarIntervaloFechas(fechaDesde, fechaHasta,fechaDesdeConsulta, fechaHastaConsulta)){
                flag = false;
            }
        }
        return flag;
    }

	private boolean validarIntervaloFechas(Calendar fechaDesdeReserva, Calendar fechaHastaReserva,
                                      Calendar fechaDesdeConsulta, Calendar fechaHastaConsulta){
	    if((fechaHastaConsulta.compareTo(fechaDesdeReserva) < 0) && ((fechaHastaReserva.compareTo(fechaDesdeConsulta)) < 0)){
	        return true;
        }
        else{
            return false;
        }
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

	public boolean validarReserva(int floor, int room, String desde, String hasta){

		List<JPAReserva> reservas = buscarReserva(floor, room);

		if(reservas.size() == 0){ //No existe reserva

			guardarReserva(floor, room, desde, hasta); //Crea reserva

			return true;

		}else{

			Calendar cDesde = Calendar.getInstance();
			Calendar cHasta = Calendar.getInstance();

			this.parseCalendar(convertir(desde),cDesde);
			this.parseCalendar(convertir(hasta),cHasta);

			boolean valido = validarIntervaloDeReserva(reservas, cDesde, cHasta);

			if(valido == false){

				return false;
			}

			guardarReserva(floor, room, desde, hasta); //Crea reserva

			return true;
		}

	}

	private void parseCalendar(int[] fecha,Calendar calendar){

		calendar.set(fecha[0], fecha[1] - 1, fecha[2]);
	}

	public int[] convertir(String fecha){

		int[] aux = new int[3];

		String[] a = fecha.split("-");

		for(int i = 0;i < a.length; i++){

			aux[i] = Integer.parseInt(a[i].trim());
		}

		return aux;
	}

}
