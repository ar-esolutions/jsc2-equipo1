package com.esolutions.trainings.jsc2.logic;

import com.esolutions.trainings.jsc2.model.Guest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class GuestRepeatedService {
	private final JpaRepository<Guest, Long> repository;

	@Autowired
	public GuestRepeatedService(JpaRepository<Guest, Long> repository) {
		this.repository = repository;
	}

	public void alphabeticallySortedRepeatedGuestsByLastName() {
		final List<Guest> allGuests = this.repository.findAll();

		//Write your code here!
	}

	private List<String> lastNameOfGuests(List<Guest> guests){
		LinkedList<String> lastNames = new LinkedList<>();
		for ( Guest g: guests) {
			String lastName = getLastNameFromFullName(g);
			lastName = upperCaseFirstDigit(lastName);
			lastNames.add(lastName);
		}
		return lastNames;
	}

	private String parseBinaryFloorAndRoom(int floor, int room){

		String binaryFloor = Integer.toBinaryString(floor);  // Paso el int floor a binario y lo guardo en string
		String binaryRoom = Integer.toBinaryString(room);    // Paso el int room a binario y lo guardo en string

		String concatenado = binaryFloor.concat(binaryRoom); // concateno los 2 string binaryFloor y binaryRoom
		return concatenado;
	}

	//Toma el nombre completo y obtiene unicamente su apellido
	private String getLastNameFromFullName(Guest guest){
		String fullName = guest.getName();
		return fullName.split(",")[0];
	}

	//Agrega mayuscula al primer digito de una cadena
	private String upperCaseFirstDigit(String s){
		s = s.toLowerCase();
		return s.substring(0, 1).toUpperCase() + s.substring(1);
	}



}
