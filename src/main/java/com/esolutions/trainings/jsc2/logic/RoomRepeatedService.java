package com.esolutions.trainings.jsc2.logic;

import com.esolutions.trainings.jsc2.model.JPARoom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

@Service
public class RoomRepeatedService {
    private final JpaRepository<JPARoom, Long> repository;
    private float pSuitePromo = 202.99f;
    private float pEstPromo = 191.99f;
    private float pSuiteSinPromo = 187.99f;
    private float pEstSinPromo = 150.99f;


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

    public BigDecimal precioDeReserva(String desde, String hasta, String tipo) {

        Calendar ingreso = Calendar.getInstance();
        Calendar salida = Calendar.getInstance();
        double total;

        long resDias = dias(convertir(desde), convertir(hasta), ingreso, salida);

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

        long diasSinPromo = resDias - diasPromo;

        if (tipo.equals("SUITE")) {
            total = diasSinPromo * pSuiteSinPromo + diasPromo * pSuitePromo;
            BigDecimal formatNumber = BigDecimal.valueOf(total);
            return formatNumber.setScale(2, RoundingMode.DOWN);

        } else {
            total = diasSinPromo * pEstSinPromo+ diasPromo * pEstPromo;
            BigDecimal formatNumber = BigDecimal.valueOf(total);
            return formatNumber.setScale(2, RoundingMode.DOWN);
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

    public long dias(int[] aDesde, int[] aHasta, Calendar ingreso, Calendar salida) {

        ingreso.set(aDesde[0], aDesde[1] - 1, aDesde[2]);
        salida.set(aHasta[0], aHasta[1] - 1, aHasta[2]);

        return  (salida.getTimeInMillis() - ingreso.getTimeInMillis()) / 86400000;
    }


}
