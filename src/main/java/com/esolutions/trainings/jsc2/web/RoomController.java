package com.esolutions.trainings.jsc2.web;

import com.esolutions.trainings.jsc2.logic.RoomRepeatedService;
import com.esolutions.trainings.jsc2.logic.WifiPasswordLogic;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.TreeMap;

import static com.esolutions.trainings.jsc2.logic.HaveANiceDayHotel.getGuestResponse;

@RestController
public class RoomController {
    private final RoomRepeatedService service;

    @Autowired
    public RoomController(RoomRepeatedService service ) {
        this.service = service;
    }

    @GetMapping(value = "/floors/{floor}/rooms/{room}")
    public GuestResponse getGuestNumber(@PathVariable int floor, @PathVariable int room){
        return getGuestResponse(floor,room);
    }

    /*
    @RequestMapping(method = RequestMethod.POST, value = "/floors/{floor}/rooms/{room}/book")
    public void getRoomAndFloor(@PathVariable int floor, @PathVariable int room){
         this.service.busquedaRooms(floor, room);
    }
*/
    @RequestMapping(method = RequestMethod.GET, path = "/floors/{floor}/rooms/{room}/wifi/password")
    @ResponseBody
    public String calculatePassword(@PathVariable int floor, @PathVariable int room) {
        WifiPasswordLogic wifi = new WifiPasswordLogic();
        return wifi.calculatePassword(floor,room);
    }
    @RequestMapping(method = RequestMethod.POST, value = "/desde/{desde}/hasta/{hasta}/tipo/{tipo}/book")
    public void getPrecio(@PathVariable String desde, @PathVariable String hasta, @PathVariable String tipo){

        this.service.precioDeReserva(desde, hasta, tipo);
    }





}
