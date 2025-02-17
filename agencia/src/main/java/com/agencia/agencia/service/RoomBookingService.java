package com.agencia.agencia.service;

import com.agencia.agencia.dto.RoomBookingRequestDTO;
import com.agencia.agencia.dto.RoomBookingResponseDTO;
import com.agencia.agencia.model.Hotel;
import com.agencia.agencia.model.RoomBooking;
import com.agencia.agencia.repository.HotelRepository;
import com.agencia.agencia.repository.RoomBookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RoomBookingService {

    @Autowired
    private RoomBookingRepository roomBookingRepository;

    @Autowired
    private HotelRepository hotelRepository;

    // Obtener todas las reservas
    public List<RoomBookingResponseDTO> getAllRoomBookings() {
        return roomBookingRepository.findAll()
                .stream()
                .map(RoomBookingResponseDTO::new)
                .collect(Collectors.toList());
    }

    // Obtener habitaciones disponibles en un rango de fechas y destino
    public List<RoomBookingResponseDTO> getAvailableRooms(String dateFrom, String dateTo, String destination) {
        // Parsear las fechas
        LocalDate startDate = LocalDate.parse(dateFrom);
        LocalDate endDate = LocalDate.parse(dateTo);

        // Buscar hoteles por destino
        List<Hotel> hotels = hotelRepository.findByDestination(destination);

        if (hotels.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No hotels found for the given destination");
        }

        // Filtrar las reservas disponibles en los hoteles encontrados
        List<RoomBooking> availableBookings = roomBookingRepository.findAll().stream()
                .filter(booking -> hotels.contains(booking.getHotel()) &&
                        (booking.getCheckOut().isBefore(startDate) || booking.getCheckIn().isAfter(endDate)))
                .collect(Collectors.toList());

        return availableBookings.stream()
                .map(RoomBookingResponseDTO::new)
                .collect(Collectors.toList());
    }

    // Obtener una reserva por ID
    public Optional<RoomBookingResponseDTO> getRoomBookingById(Long id) {
        return roomBookingRepository.findById(id)
                .map(RoomBookingResponseDTO::new);
    }

    // Crear una nueva reserva de habitación
    public RoomBookingResponseDTO createRoomBooking(RoomBookingRequestDTO roomBookingRequestDTO) {
        RoomBooking roomBooking = new RoomBooking();
        return createOrUpdateRoomBooking(roomBooking, roomBookingRequestDTO);
    }

    // Actualizar una reserva existente
    public Optional<RoomBookingResponseDTO> updateRoomBooking(Long id, RoomBookingRequestDTO roomBookingRequestDTO) {
        return roomBookingRepository.findById(id)
                .map(existingBooking -> createOrUpdateRoomBooking(existingBooking, roomBookingRequestDTO));
    }

    // Eliminar una reserva
    public boolean deleteRoomBooking(Long id) {
        return roomBookingRepository.findById(id)
                .map(roomBooking -> {
                    roomBookingRepository.delete(roomBooking);
                    return true;
                })
                .orElse(false);
    }

    // Lógica de creación y actualización de la reserva
    private RoomBookingResponseDTO createOrUpdateRoomBooking(RoomBooking roomBooking, RoomBookingRequestDTO roomBookingRequestDTO) {
        if (roomBookingRequestDTO.getHotelId() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Hotel ID must not be null");
        }

        Optional<Hotel> hotel = hotelRepository.findById(roomBookingRequestDTO.getHotelId());

        if (hotel.isPresent()) {
            if (roomBookingRequestDTO.getCheckInDate().isAfter(roomBookingRequestDTO.getCheckOutDate())) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Check-in date must be before check-out date");
            }

            // Asignamos todos los campos del DTO a la entidad RoomBooking
            roomBooking.setHotel(hotel.get());  // Asociar el hotel
            roomBooking.setRoomType(roomBookingRequestDTO.getRoomType());  // Tipo de habitación
            roomBooking.setCustomerName(roomBookingRequestDTO.getCustomerName());  // Nombre del cliente
            roomBooking.setCheckIn(roomBookingRequestDTO.getCheckInDate());  // Fecha de entrada
            roomBooking.setCheckOut(roomBookingRequestDTO.getCheckOutDate());  // Fecha de salida
            roomBooking.setPricePerNight(roomBookingRequestDTO.getPricePerNight());  // Precio por noche
            roomBooking.setAvailableFrom(roomBookingRequestDTO.getAvailableFrom());  // Fecha de disponibilidad desde
            roomBooking.setAvailableUntil(roomBookingRequestDTO.getAvailableUntil());  // Fecha de disponibilidad hasta
            roomBooking.setDestination(roomBookingRequestDTO.getDestination());
            // Guardar la reserva en la base de datos
            RoomBooking savedRoomBooking = roomBookingRepository.save(roomBooking);

            // Retornar el DTO con la respuesta
            return new RoomBookingResponseDTO(savedRoomBooking);
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Hotel not found");
        }
    }

}
