package com.agencia.agencia.service;
import com.agencia.agencia.dto.RoomBookingRequestDTO;
import com.agencia.agencia.dto.RoomBookingResponseDTO;

import java.util.List;
import java.util.Optional;

public interface IRoomBookingService {

    // Obtener todas las reservas
    List<RoomBookingResponseDTO> getAllRoomBookings();

    // Obtener habitaciones disponibles en un rango de fechas y destino
    List<RoomBookingResponseDTO> getAvailableRooms(String dateFrom, String dateTo, String destination);

    // Obtener una reserva por ID
    Optional<RoomBookingResponseDTO> getRoomBookingById(Long id);

    // Crear una nueva reserva de habitación
    RoomBookingResponseDTO createRoomBooking(RoomBookingRequestDTO roomBookingRequestDTO);

    // Actualizar una reserva existente
    Optional<RoomBookingResponseDTO> updateRoomBooking(Long id, RoomBookingRequestDTO roomBookingRequestDTO);

    // Eliminar una reserva
    boolean deleteRoomBooking(Long id);
}

