package com.esolutions.trainings.jsc2.web;

import com.esolutions.trainings.jsc2.logic.GenerateKeyLogic;
import com.esolutions.trainings.jsc2.logic.WifiPasswordLogic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import static com.esolutions.trainings.jsc2.logic.HaveANiceDayHotel.getGuestResponse;

@RestController
public class RoomController {
    private final WifiPasswordLogic wifiService;
    private final GenerateKeyLogic keyService;

    @Autowired
    public RoomController() {
        this.wifiService = new WifiPasswordLogic();
        this.keyService = new GenerateKeyLogic();
    }

    @GetMapping(value = "/floors/{floor}/rooms/{room}")
    public GuestResponse getGuestNumber(@PathVariable int floor, @PathVariable int room) {
        return getGuestResponse(floor, room);
    }

    @GetMapping(path = "/floors/{floor}/rooms/{room}/wifi/password")
    public WifiPasswordResponse calculatePassword(@PathVariable int floor, @PathVariable int room) {
        return wifiService.calculatePassword(floor, room);
    }

    @GetMapping(path = "/floors/{floor}/rooms/{room}/wifi/ssid")
    @ResponseBody
    public WifiSsidResponse calculateSsid(@PathVariable int floor, @PathVariable int room) {
        return keyService.generateSsid(floor, room);
    }


}
