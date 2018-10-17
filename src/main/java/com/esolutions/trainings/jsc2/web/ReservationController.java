package com.esolutions.trainings.jsc2.web;

import com.esolutions.trainings.jsc2.logic.ReservaRepeatedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class ReservationController {

    private final ReservaRepeatedService service;

    @Autowired
    public ReservationController(ReservaRepeatedService service) {
        this.service = service;
    }


    @RequestMapping(method = RequestMethod.POST, value = "/floors/{floor}/rooms/{room}/book")
    public ReservationResponse saveReserva(@PathVariable int floor, @PathVariable int room, @RequestBody Map<String, String> parm) {
        return service.getReservationResponse(floor, room, parm);
    }


}