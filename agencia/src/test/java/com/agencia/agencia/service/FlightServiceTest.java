package com.agencia.agencia.service;
import com.agencia.agencia.dto.FlightResponseDTO;
import com.agencia.agencia.model.Flight;
import com.agencia.agencia.repository.FlightRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class FlightServiceTest {

    @Mock
    private FlightRepository flightRepository;

    @InjectMocks
    private FlightService flightService;

    private Flight flight;

    @BeforeEach
    void setUp() {
        flight = new Flight();
        flight.setId(1L);
        flight.setFlightNumber("AB123");
        flight.setFlightName("Airbus 320");
        flight.setOrigin("Madrid");
        flight.setDestination("Barcelona");
        flight.setSeatType("Economy");
        flight.setFlightPrice(150.0);
        flight.setDate(new Date());
    }

    // Caso positivo: hay vuelos registrados
    @Test
    void testGetAllFlights_Success() {
        when(flightRepository.findAll()).thenReturn(List.of(flight));

        List<FlightResponseDTO> flights = flightService.getAllFlights();

        assertNotNull(flights);
        assertEquals(1, flights.size());
        assertEquals(flight.getFlightNumber(), flights.get(0).getFlightNumber());

        verify(flightRepository, times(1)).findAll();
    }

    // Caso negativo: no hay vuelos registrados
    @Test
    void testGetAllFlights_NoFlightsRegistered() {
        when(flightRepository.findAll()).thenReturn(Collections.emptyList());

        Exception exception = assertThrows(RuntimeException.class, flightService::getAllFlights);

        assertEquals("No hay vuelos registrados en el sistema", exception.getMessage());

        verify(flightRepository, times(1)).findAll();
    }
}

