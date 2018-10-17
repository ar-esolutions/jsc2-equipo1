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

	//Metodo para traer los guest de la base de datos
	private List<Guest> findAllGuestInRepository() {
		//Buscar y trae todos los guests de la base
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

	//Este metodo va a agrupar todos los guest iguales
	private Map<String, List<String>> groupingByGuest(LinkedList<String> lastNameOfGuests){
		Map<String, List<String>> duplicateList = lastNameOfGuests
				//Stream se utiliza para ingresar a metodos extras de una lista
				.stream()
				//Agrupar por guest
				.collect(Collectors.groupingBy(s -> s));

		return duplicateList;
	}

	//Este metodo es para buscar los repetidos y solo mantener uno
	private List<String> findRepetitionsOfGuest(Map<String, List<String>> duplicateList){
		List<String> duplicateListAndSorted = duplicateList.entrySet()
				.stream()
				//filtrar los que tienen el mismo apellido
				//fijandose si el mismo esta mas de una vez
				.filter(e -> e.getValue().size() > 1)
				.map(e -> e.getKey())
				.collect(Collectors.toList());

		return duplicateListAndSorted;
	}

	private List<String> orderLastNames(List<String> repetitionsOfGuests){

		//Collator permite ordenar alfabeticamente respetando acentos y caracteres especiales
		Collator primaryCollator = Collator.getInstance(new Locale("es"));
		//Primary es para que respete dichos caracteres
		primaryCollator.setStrength(Collator.PRIMARY);
		repetitionsOfGuests.sort(primaryCollator);

		//Collections.sort(repetitionsOfGuests);

		return repetitionsOfGuests;
	}

	public RepeatedGuest returnMapOfRepetitionsOfGuest(){
		RepeatedGuest repeatedGuest = new RepeatedGuest();

		//Primero buscamos todos los guests
		List<Guest> allGuestInRepository = findAllGuestInRepository();
		//Despues solo traemos los apellidos de los guests, antes de la coma
		LinkedList<String> lastNameOfGuests = lastNameOfGuests(allGuestInRepository);
		//Luego separamos a los que estan duplicados
		Map<String, List<String>> duplicateList = groupingByGuest(lastNameOfGuests);
		//Luego dejamos solo los que estan repetidos
		List<String> repetitionsOfGuest = findRepetitionsOfGuest(duplicateList);
		//Los ordenamos alfabeticamente
		List<String> orderLastNames = orderLastNames(repetitionsOfGuest);
		//Los seteamos al response que creamos
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
