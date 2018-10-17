package com.esolutions.trainings.jsc2.web;

import com.esolutions.trainings.jsc2.logic.GuestRepeatedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GuestController {
    private final GuestRepeatedService service;

    @Autowired
    public GuestController(GuestRepeatedService service) {
        this.service = service;
    }

    @GetMapping(path = "/guests/last-name/repeated")
    public RepeatedGuest repeatedLastName() {
        return service.returnMapOfRepetitionsOfGuest();
    }
}
