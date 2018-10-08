package com.esolutions.trainings.jsc2.web;

import com.esolutions.trainings.jsc2.logic.RoomRepeatedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class RoomController {
    private final RoomRepeatedService service;

    @Autowired
    public RoomController(RoomRepeatedService service) {
        this.service = service;
    }
/*
    @GetMapping(value = "/floors/{floor}/rooms/{room}")
    public GuestResponse getGuestNumber(@PathVariable int floor, @PathVariable int room){
        return new GuestResponse(null);
    }
*/
    @RequestMapping(method = RequestMethod.POST, value = "/floors/{floor}/rooms/{room}/book")
    public void getRoomAndFloor(@PathVariable int floor, @PathVariable int room){
         this.service.busquedaRooms(floor, room);
    }

}
