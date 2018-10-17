package com.esolutions.trainings.jsc2.web;

import com.esolutions.trainings.jsc2.logic.GenerateKeyLogic;
import com.esolutions.trainings.jsc2.logic.RoomRepeatedService;
import com.esolutions.trainings.jsc2.logic.WifiPasswordLogic;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static com.esolutions.trainings.jsc2.logic.HaveANiceDayHotel.getGuestResponse;

@RestController
public class RoomController {
    private final RoomRepeatedService service;
    private final WifiPasswordLogic wifiService;
    private final GenerateKeyLogic keyService;

    @Autowired
    public RoomController(RoomRepeatedService service) {
        this.service = service;
        this.wifiService = new WifiPasswordLogic();
        this.keyService = new GenerateKeyLogic();
    }

    @GetMapping(value = "/floors/{floor}/rooms/{room}")
    public GuestResponse getGuestNumber(@PathVariable int floor, @PathVariable int room) {
        return getGuestResponse(floor, room);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/floors/{floor}/rooms/{room}/wifi/password")
    public WifiPasswordResponse calculatePassword(@PathVariable int floor, @PathVariable int room) {
        return wifiService.calculatePassword(floor, room);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/floors/{floor}/rooms/{room}/wifi/ssid")
    @ResponseBody
    public WifiSsidResponse calculateSsid(@PathVariable int floor, @PathVariable int room) {
        return keyService.generateSsid(floor, room);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/desde/{desde}/hasta/{hasta}/tipo/{tipo}/book")
    public void getPrecio(@PathVariable String desde, @PathVariable String hasta, @PathVariable String tipo) {

        this.service.precioDeReserva(desde, hasta, tipo);
    }


}
