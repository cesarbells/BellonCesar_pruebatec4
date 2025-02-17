package com.agencia.agencia.controller;

import com.agencia.agencia.dto.FlightBookingRequestDTO;
import com.agencia.agencia.dto.FlightBookingResponseDTO;
import com.agencia.agencia.service.FlightBookingService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/agency/reserva")
public class FlightBookingController {

    private final FlightBookingService flightBookingService;

    @Autowired
    public FlightBookingController(FlightBookingService flightBookingService) {
        this.flightBookingService = flightBookingService;
    }

    @PostMapping("/new")
    public ResponseEntity<FlightBookingResponseDTO> bookFlight(@RequestBody FlightBookingRequestDTO requestDTO) {
        // Verificar el valor de seatType antes de llamar al servicio
        System.out.println("Received seatType: " + requestDTO.getSeatType());

        // Llamar al servicio para crear la reserva de vuelo
        FlightBookingResponseDTO response = flightBookingService.createBooking(requestDTO);

        // Devolver la respuesta del servicio
        return ResponseEntity.ok(response);
    }

    // Método GET para obtener todas las reservas
    @GetMapping("/all")
    public ResponseEntity<List<FlightBookingResponseDTO>> getAllBookings() {
        // Llamar al servicio para obtener todas las reservas
        List<FlightBookingResponseDTO> bookings = flightBookingService.getAllBookings();

        // Devolver la lista de reservas
        return ResponseEntity.ok(bookings);
    }
}

