package com.agencia.agencia.service;

import com.agencia.agencia.dto.FlightRequestDTO;
import com.agencia.agencia.dto.FlightResponseDTO;
import com.agencia.agencia.model.Flight;
import com.agencia.agencia.repository.FlightBookingRepository;
import com.agencia.agencia.repository.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FlightService implements IFlightService {

    @Autowired
    private FlightRepository flightRepository;

    @Autowired
    private FlightBookingRepository flightBookingRepository;  // Asegúrate de inyectar el repositorio correcto

    // Método para obtener todos los vuelos
    public List<FlightResponseDTO> getAllFlights() {
        List<Flight> flights = flightRepository.findAll();
        if (flights.isEmpty()) {
            throw new RuntimeException("No hay vuelos registrados en el sistema");
        }
        return flights.stream()
                .map(this::convertToResponseDTO)
                .collect(Collectors.toList());
    }

    // Método para obtener vuelos disponibles dentro de un rango de fechas y origen/destino
    public List<FlightResponseDTO> getAvailableFlights(Date dateFrom, Date dateTo, String origin, String destination) {
        if (dateFrom == null || dateTo == null || origin == null || destination == null) {
            throw new IllegalArgumentException("Los parámetros de búsqueda no pueden ser nulos");
        }

        List<Flight> availableFlights = flightRepository.findByDateBetweenAndOriginAndDestination(dateFrom, dateTo, origin, destination);
        if (availableFlights.isEmpty()) {
            throw new RuntimeException("No se encontraron vuelos disponibles con los parámetros proporcionados");
        }

        return availableFlights.stream()
                .map(this::convertToResponseDTO)
                .collect(Collectors.toList());
    }

    // Método para crear un vuelo
    public FlightResponseDTO createFlight(FlightRequestDTO flightRequestDTO) {
        // Validar si ya existe el vuelo
        List<Flight> existingFlight = flightRepository.findByFlightNumber(flightRequestDTO.getFlightNumber());
        if (existingFlight != null && !existingFlight.isEmpty()) {
            throw new RuntimeException("Ya existe un vuelo con este número");
        }

        // Convertir DTO a entidad
        Flight flight = convertToEntity(flightRequestDTO);
        flight = flightRepository.save(flight);
        return convertToResponseDTO(flight);
    }

    // Método para actualizar un vuelo existente
    public FlightResponseDTO updateFlight(Long id, FlightRequestDTO flightRequestDTO) {
        // Buscar el vuelo
        Flight flight = flightRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Vuelo no encontrado"));

        // Actualizar los detalles
        flight.setFlightNumber(flightRequestDTO.getFlightNumber());
        flight.setFlightName(flightRequestDTO.getName());
        flight.setOrigin(flightRequestDTO.getOrigin());
        flight.setDestination(flightRequestDTO.getDestination());
        flight.setSeatType(flightRequestDTO.getSeatType());
        flight.setFlightPrice(flightRequestDTO.getFlightPrice());
        flight.setDate(flightRequestDTO.getDate());

        // Guardar y devolver
        flight = flightRepository.save(flight);
        return convertToResponseDTO(flight);
    }

    // Método para eliminar un vuelo
    public void deleteFlight(Long id) {
        // Verificar si el vuelo está en alguna reserva activa
        boolean existsActiveBooking = flightBookingRepository.existsByFlightIdAndStatus(id, "active");
        if (existsActiveBooking) {
            throw new RuntimeException("No se puede eliminar el vuelo porque está en una reserva activa");
        }

        // Eliminar el vuelo si no está en una reserva activa
        flightRepository.deleteById(id);
    }

    // Método para obtener un vuelo por ID
    public FlightResponseDTO getFlightById(Long id) {
        Flight flight = flightRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Vuelo no encontrado"));
        return convertToResponseDTO(flight);
    }

    // Métodos auxiliares para convertir entre DTOs y entidades
    private Flight convertToEntity(FlightRequestDTO dto) {
        return new Flight(dto.getFlightNumber(), dto.getName(), dto.getOrigin(), dto.getDestination(), dto.getSeatType(), dto.getFlightPrice(), dto.getDate());
    }

    private FlightResponseDTO convertToResponseDTO(Flight flight) {
        return new FlightResponseDTO(flight.getId(), flight.getFlightNumber(), flight.getFlightName(), flight.getOrigin(), flight.getDestination(), flight.getSeatType(), flight.getFlightPrice(), flight.getDate());
    }
}
