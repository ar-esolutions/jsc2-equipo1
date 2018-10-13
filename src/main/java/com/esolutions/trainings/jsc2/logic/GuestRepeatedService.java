package com.esolutions.trainings.jsc2.logic;

import com.esolutions.trainings.jsc2.model.Guest;
import com.esolutions.trainings.jsc2.web.RepeatedGuest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import java.text.Collator;
import java.util.*;
import java.util.stream.Collectors;


@Service
public class GuestRepeatedService {
	private final JpaRepository<Guest, Long> repository;

	@Autowired
	public GuestRepeatedService(JpaRepository<Guest, Long> repository) {
		this.repository = repository;
	}

	private List<Guest> findAllGuestInRepository() {
		 List<Guest> allGuests = repository.findAll();
		return allGuests;
	}

	private LinkedList<String> lastNameOfGuests(List<Guest> guests){
		LinkedList<String> lastNames = new LinkedList<>();
		for ( Guest g: guests) {
			String lastName = getLastNameFromFullName(g);
			lastName = upperCaseFirstDigit(lastName);
			lastNames.add(lastName);
		}
		return lastNames;
	}

	private List<String> findRepetitionsOfGuest(LinkedList<String> lastNameOfGuests){

		List<String> duplicateList =
				lastNameOfGuests
						.stream()
						//Agrupar por guest
						.collect(Collectors.groupingBy(s -> s))
						.entrySet()
						.stream()
						//filtrar los que tienen el mismo apellido
						.filter(e -> e.getValue().size() > 1)
						.map(e -> e.getKey())
						.collect(Collectors.toList());

		return duplicateList;
	}

	private List<String> orderLastNames(List<String> repetitionsOfGuests){

		Collator primaryCollator = Collator.getInstance(new Locale("es"));
		primaryCollator.setStrength(Collator.PRIMARY);

		repetitionsOfGuests.sort(primaryCollator);

		//Collections.sort(repetitionsOfGuests);

		return repetitionsOfGuests;
	}

	public RepeatedGuest returnMapOfRepetitionsOfGuest(){
		RepeatedGuest repeatedGuest = new RepeatedGuest();

		List<Guest> allGuestInRepository = findAllGuestInRepository();
		LinkedList<String> lastNameOfGuests = lastNameOfGuests(allGuestInRepository);
		List<String> repetitionsOfGuest = findRepetitionsOfGuest(lastNameOfGuests);
		List<String> orderLastNames = orderLastNames(repetitionsOfGuest);
		repeatedGuest.setLastNames(orderLastNames);

		return repeatedGuest;
	}

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
