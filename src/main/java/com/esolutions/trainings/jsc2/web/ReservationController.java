package com.esolutions.trainings.jsc2.web;

import com.esolutions.trainings.jsc2.logic.ReservaRepeatedService;
import com.esolutions.trainings.jsc2.logic.RoomRepeatedService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.media.jfxmedia.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

@RestController
public class ReservationController {

    private final ReservaRepeatedService service;

    @Autowired
    public ReservationController(ReservaRepeatedService service) {
        this.service = service;
    }


@RequestMapping(method = RequestMethod.POST, value = "/floors/{floor}/rooms/{room}/book")
public ReservationResponse saveReserva(@PathVariable int floor, @PathVariable int room, @RequestBody Map<String, String> parm){
    return service.getReservationResponse(floor, room, parm);
}


}