package com.agencia.agencia.repository;

import com.agencia.agencia.model.FlightBooking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface FlightBookingRepository extends JpaRepository<FlightBooking, Long> {

    // Método para buscar todas las reservas de vuelos
    List<FlightBooking> findAll();

    // Método para buscar una reserva de vuelo por su ID
    Optional<FlightBooking> findById(Long id);

    // Método para buscar todas las reservas de vuelos para un pasajero por su nombre
    List<FlightBooking> findByPassengerName(String passengerName);

    // Método para buscar todas las reservas de vuelos para un vuelo específico
    List<FlightBooking> findByFlightId(Long flightId);

    // Método para buscar reservas por tipo de asiento
    List<FlightBooking> findBySeatType(String seatType);

    // Método para buscar reservas de vuelos por fecha
    List<FlightBooking> findByDate(java.sql.Date date);

    boolean existsByFlightIdAndSeatTypeAndDate(Long flightId, String seatType, Date date);

    boolean existsByFlightIdAndStatus(Long id, String active);
}
