package com.agencia.agencia.service;

import com.agencia.agencia.dto.FlightBookingRequestDTO;
import com.agencia.agencia.dto.FlightBookingResponseDTO;
import com.agencia.agencia.model.Flight;
import com.agencia.agencia.model.FlightBooking;
import com.agencia.agencia.repository.FlightBookingRepository;
import com.agencia.agencia.repository.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FlightBookingService implements IFlightBookingService {

    private final FlightBookingRepository flightBookingRepository;
    private final FlightRepository flightRepository;

    @Autowired
    public FlightBookingService(FlightBookingRepository flightBookingRepository, FlightRepository flightRepository) {
        this.flightBookingRepository = flightBookingRepository;
        this.flightRepository = flightRepository;
    }

    // Método para obtener todas las reservas

    public List<FlightBookingResponseDTO> getAllFlightBookings() {
        List<FlightBooking> bookings = flightBookingRepository.findAll();
        if (bookings.isEmpty()) {
            throw new RuntimeException("No hay reservas registradas");
        }
        return bookings.stream()
                .map(booking -> new FlightBookingResponseDTO(booking))
                .collect(Collectors.toList());
    }

    @Override
    public List<FlightBookingResponseDTO> getAllBookings() {
        return List.of();
    }

    // Método para crear una nueva reserva de vuelo (implementación de la interfaz IFlightBookingService)
    @Override
    public FlightBookingResponseDTO createBooking(FlightBookingRequestDTO requestDTO) {
        // Verificar si ya existe una reserva con las mismas características
        if (flightBookingRepository.existsByFlightIdAndSeatTypeAndDate(
                requestDTO.getFlightId(),
                requestDTO.getSeatType(),
                requestDTO.getDate())) {
            throw new RuntimeException("Ya existe una reserva con estas características");
        }

        // Obtener el vuelo por ID
        Flight flight = flightRepository.findById(requestDTO.getFlightId())
                .orElseThrow(() -> new RuntimeException("Vuelo no encontrado"));

        // Crear una nueva reserva de vuelo
        FlightBooking flightBooking = new FlightBooking();
        flightBooking.setDate(new java.sql.Date(requestDTO.getDate().getTime())); // Conversión de Date a SQL Date
        flightBooking.setOrigin(requestDTO.getOrigin());
        flightBooking.setDestination(requestDTO.getDestination());
        flightBooking.setPassengerName(requestDTO.getPassengerName());
        flightBooking.setFlight(flight);
        flightBooking.setPeopleQ(requestDTO.getPeopleQ());
        flightBooking.setSeatType(requestDTO.getSeatType());

        // Guardar la reserva en la base de datos
        flightBooking = flightBookingRepository.save(flightBooking);

        return new FlightBookingResponseDTO(flightBooking);
    }

    // Método para eliminar una reserva de vuelo

    public void deleteFlightBooking(Long bookingId) {
        FlightBooking booking = flightBookingRepository.findById(bookingId)
                .orElseThrow(() -> new RuntimeException("Reserva no encontrada"));

        // Verificar si el vuelo ya está en una reserva activa
        if (flightBookingRepository.existsByFlightIdAndStatus(booking.getFlight().getId(), "active")) {
            throw new RuntimeException("No se puede eliminar el vuelo porque está en una reserva activa");
        }

        // Eliminar la reserva
        flightBookingRepository.delete(booking);
    }
}

