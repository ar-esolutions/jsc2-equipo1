package com.esolutions.trainings.jsc2.web;

import com.esolutions.trainings.jsc2.logic.GuestRepeatedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class GuestController {
	private final GuestRepeatedService service;

	@Autowired
	public GuestController(GuestRepeatedService service) {
		this.service = service;
	}

    @RequestMapping(method = RequestMethod.GET, path = "/guests/last-name/repeated")
    public RepeatedGuest repeatedLastName() {
        return service.returnMapOfRepetitionsOfGuest();
    }
}
