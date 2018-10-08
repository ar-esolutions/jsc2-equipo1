package com.esolutions.trainings.jsc2.logic;

import com.esolutions.trainings.jsc2.model.JPARoom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;

@Service
public class RoomRepeatedService {
	private final JpaRepository<JPARoom, Long> repository;

	@Autowired
	public RoomRepeatedService(JpaRepository<JPARoom, Long> repository) {
		this.repository = repository;
	}

	public String busquedaRooms(int floor, int room) {
		String tipo = "";
		final List<JPARoom> allJPARooms = this.repository.findAll();
		Iterator<JPARoom> i = allJPARooms.iterator();
		JPARoom r = null;
		while(i.hasNext() ){
			r = i.next();
			if (r.getFloor() == floor && r.getNro() == room){
				tipo = r.getType();
				return tipo;
			}
		}
		return tipo;

		//Write your code here!
	}

	public int precio(){




	    return 1;
	}
}
