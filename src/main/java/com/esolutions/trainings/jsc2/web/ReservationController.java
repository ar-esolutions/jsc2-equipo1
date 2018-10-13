package com.esolutions.trainings.jsc2.web;

import com.esolutions.trainings.jsc2.logic.ReservaRepeatedService;
import com.esolutions.trainings.jsc2.logic.RoomRepeatedService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
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
    private final ObjectMapper objectMapper;
    private final RoomRepeatedService roomService;

    @Autowired
    public ReservationController(ReservaRepeatedService service, ObjectMapper objectMapper, RoomRepeatedService roomService) {
        this.service = service;
        this.objectMapper = objectMapper;
        this.roomService = roomService;
    }
/*
*/
@RequestMapping(method = RequestMethod.POST, value = "/floors/{floor}/rooms/{room}/book")
public ObjectNode saveReserva(@PathVariable int floor, @PathVariable int room, @RequestBody Map<String, String> parm){
    String checkIn = parm.get("checkIn");
    String checkOut = parm.get("checkOut");

    ObjectNode objectNode = objectMapper.createObjectNode();


    boolean reserved = service.validarReserva(floor, room, checkIn, checkOut);
    String tipo = roomService.busquedaRooms(floor,room);
    double precio = roomService.precioDeReserva(checkIn, checkOut, tipo);

    objectNode.put("booked:",reserved);
    objectNode.put("price: ",precio);

    return objectNode;
}

}