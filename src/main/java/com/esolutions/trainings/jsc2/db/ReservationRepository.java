package com.esolutions.trainings.jsc2.db;

import com.esolutions.trainings.jsc2.model.JPAReserva;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservationRepository extends JpaRepository<JPAReserva, Long> {
}
