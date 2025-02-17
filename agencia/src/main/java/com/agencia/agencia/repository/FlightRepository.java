package com.agencia.agencia.repository;

import com.agencia.agencia.model.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface FlightRepository extends JpaRepository<Flight, Long> {

    // Método para buscar vuelos dentro de un rango de fechas y por origen y destino
    List<Flight> findByDateBetweenAndOriginAndDestination(Date dateFrom, Date dateTo, String origin, String destination);

    // Método para buscar un vuelo por su ID
    Optional<Flight> findById(Long id);

    // Método para buscar vuelos por número de vuelo
    List<Flight> findByFlightNumber(String flightNumber);

    // Método para buscar vuelos por nombre del vuelo
    List<Flight> findByFlightName(String flightName);
}

