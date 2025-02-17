package com.agencia.agencia.controller;

import com.agencia.agencia.dto.HotelRequestDTO;
import com.agencia.agencia.dto.HotelResponseDTO;
import com.agencia.agencia.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/agency/hotels")
public class HotelController {

    @Autowired
    private HotelService hotelService;

    // Obtener todos los hoteles
    @GetMapping
    public ResponseEntity<List<HotelResponseDTO>> getAllHotels() {
        return ResponseEntity.ok(hotelService.getAllHotels());
    }

    // Obtener un hotel por ID
    @GetMapping("/{id}")
    public ResponseEntity<HotelResponseDTO> getHotelById(@PathVariable Long id) {
        // Obtenemos el hotel directamente (no Optional)
        HotelResponseDTO hotel = hotelService.getHotelById(id);

        // Si el hotel es null, respondemos con Not Found, de lo contrario, con OK
        return hotel != null ? ResponseEntity.ok(hotel) : ResponseEntity.notFound().build();
    }

    // Crear un nuevo hotel
    @PostMapping
    public ResponseEntity<HotelResponseDTO> createHotel(@RequestBody HotelRequestDTO hotelRequestDTO) {
        HotelResponseDTO newHotel = hotelService.createHotel(hotelRequestDTO);
        return ResponseEntity.status(201).body(newHotel);
    }

    // Actualizar un hotel existente
    @PutMapping("/{id}")
    public ResponseEntity<HotelResponseDTO> updateHotel(@PathVariable Long id, @RequestBody HotelRequestDTO hotelRequestDTO) {
        // Actualizamos el hotel y si no se encuentra, respondemos con Not Found
        HotelResponseDTO updatedHotel = hotelService.updateHotel(id, hotelRequestDTO);
        return updatedHotel != null ? ResponseEntity.ok(updatedHotel) : ResponseEntity.notFound().build();
    }

    // Eliminar un hotel
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteHotel(@PathVariable Long id) {
        boolean deleted = hotelService.deleteHotel(id);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
