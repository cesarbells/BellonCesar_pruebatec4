package com.agencia.agencia.controller;

import com.agencia.agencia.dto.FlightRequestDTO;
import com.agencia.agencia.dto.FlightResponseDTO;
import com.agencia.agencia.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/agency/flights")
public class FlightController {

    @Autowired
    private FlightService flightService;

    // Obtener listado de todos los vuelos registrados
    @GetMapping
    public ResponseEntity<List<FlightResponseDTO>> getAllFlights() {
        return ResponseEntity.ok(flightService.getAllFlights());
    }

    // Obtener vuelos disponibles según fecha de ida, fecha de vuelta, origen y destino
    @GetMapping(params = {"dateFrom", "dateTo", "origin", "destination"})
    public ResponseEntity<List<FlightResponseDTO>> getAvailableFlights(
            @RequestParam @DateTimeFormat(pattern = "dd/MM/yyyy") Date dateFrom,
            @RequestParam @DateTimeFormat(pattern = "dd/MM/yyyy") Date dateTo,
            @RequestParam String origin,
            @RequestParam String destination) {

        List<FlightResponseDTO> availableFlights = flightService.getAvailableFlights(dateFrom, dateTo, origin, destination);
        return ResponseEntity.ok(availableFlights);
    }

    // Crear un nuevo vuelo
    @PostMapping("/new")
    public ResponseEntity<FlightResponseDTO> createFlight(@RequestBody FlightRequestDTO flightRequestDTO) {
        FlightResponseDTO responseDTO = flightService.createFlight(flightRequestDTO);
        return ResponseEntity.ok(responseDTO);
    }

    // Editar un vuelo existente
    @PutMapping("/edit/{id}")
    public ResponseEntity<FlightResponseDTO> updateFlight(@PathVariable Long id, @RequestBody FlightRequestDTO flightRequestDTO) {
        FlightResponseDTO updatedFlight = flightService.updateFlight(id, flightRequestDTO);
        return ResponseEntity.ok(updatedFlight);
    }

    // Eliminar un vuelo
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteFlight(@PathVariable Long id) {
        flightService.deleteFlight(id);
        return ResponseEntity.noContent().build();
    }

    // Obtener un vuelo específico
    @GetMapping("/{id}")
    public ResponseEntity<FlightResponseDTO> getFlightById(@PathVariable Long id) {
        FlightResponseDTO flightResponseDTO = flightService.getFlightById(id);
        return ResponseEntity.ok(flightResponseDTO);
    }
}

