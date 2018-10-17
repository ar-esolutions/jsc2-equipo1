package com.esolutions.trainings.jsc2.logic;

import com.esolutions.trainings.jsc2.model.JPARoom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

@Service
public class RoomRepeatedService {
    private final JpaRepository<JPARoom, Long> repository;
    private double pSuitePromo = 202.99;
    private double pEstPromo = 191.99;
    private double pSuiteSinPromo = 187.99;
    private double pEstSinPromo = 150.99;


    @Autowired
    public RoomRepeatedService(JpaRepository<JPARoom, Long> repository) {
        this.repository = repository;
    }

    public String busquedaRooms(int floor, int room) {
        String tipo = "";
        final List<JPARoom> allJPARooms = repository.findAll();
        Iterator<JPARoom> i = allJPARooms.iterator();
        JPARoom r;
        while (i.hasNext()) {
            r = i.next();
            if (r.getFloor() == floor && r.getNro() == room) {
                tipo = r.getType();
                return tipo;
            }
        }
        return tipo;
    }

    public double precioDeReserva(String desde, String hasta, String tipo) {

        int precio = 0;
        Calendar ingreso = Calendar.getInstance();
        Calendar salida = Calendar.getInstance();


        int resDias = dias(convertir(desde), convertir(hasta), ingreso, salida);

        int contador = ingreso.get(Calendar.DAY_OF_WEEK);

        int diasPromo = 0;

        for (int i = 0; i < resDias; i++) {

            if (contador == 6 || contador == 7 || contador == 1) {

                diasPromo += 1;
            }

            contador += 1;

            if (contador > 7) {
                contador = 1;
            }
        }

        int diasSinPromo = resDias - diasPromo;

        if (tipo.equals("SUITE")) {

            return diasSinPromo * pSuiteSinPromo + diasPromo * pSuitePromo;
        } else {

            return diasSinPromo * pEstSinPromo + diasPromo * pEstPromo;
        }
    }

    public int[] convertir(String fecha) {

        int[] aux = new int[3];

        String[] a = fecha.split("-");

        for (int i = 0; i < a.length; i++) {

            aux[i] = Integer.parseInt(a[i].trim());
        }

        return aux;
    }

    public int dias(int[] aDesde, int[] aHasta, Calendar ingreso, Calendar salida) {

        ingreso.set(aDesde[0], aDesde[1] - 1, aDesde[2]);
        salida.set(aHasta[0], aHasta[1] - 1, aHasta[2]);

        int resDias = (int) (salida.getTimeInMillis() - ingreso.getTimeInMillis()) / 86400000;

        return resDias;
    }


}
