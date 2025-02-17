package com.agencia.agencia.controller;

import com.agencia.agencia.dto.RoomBookingRequestDTO;
import com.agencia.agencia.dto.RoomBookingResponseDTO;
import com.agencia.agencia.service.RoomBookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/agency/room-bookings")
public class RoomBookingController {

    @Autowired
    private RoomBookingService roomBookingService;

    // Obtener todas las reservas
    @GetMapping
    public ResponseEntity<List<RoomBookingResponseDTO>> getAllRoomBookings() {
        return ResponseEntity.ok(roomBookingService.getAllRoomBookings());
    }

    // Obtener habitaciones disponibles según fecha y destino
    @GetMapping("/available")
    public ResponseEntity<List<RoomBookingResponseDTO>> getAvailableRooms(
            @RequestParam String dateFrom,
            @RequestParam String dateTo,
            @RequestParam String destination) {

        List<RoomBookingResponseDTO> availableRooms = roomBookingService.getAvailableRooms(dateFrom, dateTo, destination);
        return ResponseEntity.ok(availableRooms);
    }

    // Obtener una reserva por ID
    @GetMapping("/{id}")
    public ResponseEntity<RoomBookingResponseDTO> getRoomBookingById(@PathVariable Long id) {
        if (id == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "ID must not be null");
        }

        Optional<RoomBookingResponseDTO> roomBooking = roomBookingService.getRoomBookingById(id);
        return roomBooking.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Crear una nueva reserva de hotel
    @PostMapping
    public ResponseEntity<RoomBookingResponseDTO> createRoomBooking(@RequestBody RoomBookingRequestDTO roomBookingRequestDTO) {
        // Validar que el ID del hotel no sea nulo
        if (roomBookingRequestDTO.getHotelId() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Hotel ID must not be null");
        }

        try {
            RoomBookingResponseDTO newRoomBooking = roomBookingService.createRoomBooking(roomBookingRequestDTO);
            return ResponseEntity.status(201).body(newRoomBooking);
        } catch (ResponseStatusException e) {
            return ResponseEntity.status(e.getStatusCode()).body(null);
        }
    }

    // Actualizar una reserva existente
    @PutMapping("/{id}")
    public ResponseEntity<RoomBookingResponseDTO> updateRoomBooking(@PathVariable Long id, @RequestBody RoomBookingRequestDTO roomBookingRequestDTO) {
        // Validar que el ID de la reserva y el hotel no sean nulos
        if (id == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "ID must not be null");
        }

        if (roomBookingRequestDTO.getHotelId() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Hotel ID must not be null");
        }

        Optional<RoomBookingResponseDTO> updatedRoomBooking = roomBookingService.updateRoomBooking(id, roomBookingRequestDTO);
        return updatedRoomBooking.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Eliminar una reserva de hotel
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRoomBooking(@PathVariable Long id) {
        if (id == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "ID must not be null");
        }

        boolean deleted = roomBookingService.deleteRoomBooking(id);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
