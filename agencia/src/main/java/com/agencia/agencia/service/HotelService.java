package com.agencia.agencia.service;

import com.agencia.agencia.dto.HotelRequestDTO;
import com.agencia.agencia.dto.HotelResponseDTO;
import com.agencia.agencia.model.Hotel;
import com.agencia.agencia.model.RoomBooking;
import com.agencia.agencia.repository.HotelRepository; // Asegúrate de tener el repositorio Hotel
import com.agencia.agencia.repository.RoomBookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class HotelService implements IHotelService {

    @Autowired
    private HotelRepository hotelRepository;

    @Autowired
    private RoomBookingRepository roombookingRepository;  // Para gestionar las reservas

    // Obtener todos los hoteles
    public List<HotelResponseDTO> getAllHotels() {
        List<Hotel> hotels = hotelRepository.findAll();
        if (hotels.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No se encontraron hoteles.");
        }
        return hotels.stream()
                .map(HotelResponseDTO::new)
                .collect(Collectors.toList());
    }

    // Obtener un hotel por ID
    public HotelResponseDTO getHotelById(Long id) {
        Hotel hotel = hotelRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Hotel no encontrado."));
        return new HotelResponseDTO(hotel);
    }

    // Crear un nuevo hotel
    public HotelResponseDTO createHotel(HotelRequestDTO hotelRequestDTO) {
        // Verificar si ya existe un hotel con las mismas características
        Optional<Hotel> existingHotel = hotelRepository.findByNameAndLocation(hotelRequestDTO.getName(), hotelRequestDTO.getLocation());
        if (existingHotel != null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Ya existe un hotel con esas características.");
        }

        Hotel hotel = new Hotel();
        hotel.setName(hotelRequestDTO.getName());
        hotel.setLocation(hotelRequestDTO.getLocation());

        Hotel savedHotel = hotelRepository.save(hotel);
        return new HotelResponseDTO(savedHotel);
    }

    // Actualizar un hotel existente
    public HotelResponseDTO updateHotel(Long id, HotelRequestDTO hotelRequestDTO) {
        Hotel hotel = hotelRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Hotel no encontrado."));

        hotel.setName(hotelRequestDTO.getName());
        hotel.setLocation(hotelRequestDTO.getLocation());

        Hotel updatedHotel = hotelRepository.save(hotel);
        return new HotelResponseDTO(updatedHotel);
    }

    // Eliminar un hotel
    public boolean deleteHotel(Long id) {
        Hotel hotel = hotelRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Hotel no encontrado."));

        // Verificar si el hotel está asociado a alguna reserva
        List<RoomBooking> reservas = roombookingRepository.findByHotel(hotel);
        if (!reservas.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No se puede eliminar el hotel, ya tiene reservas asociadas.");
        }

        hotelRepository.delete(hotel);
        return true;
    }
}
