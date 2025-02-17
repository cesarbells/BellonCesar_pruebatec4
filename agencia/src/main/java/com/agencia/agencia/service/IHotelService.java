package com.agencia.agencia.service;


import com.agencia.agencia.dto.HotelRequestDTO;
import com.agencia.agencia.dto.HotelResponseDTO;

import java.util.List;

public interface IHotelService {

    // Obtener todos los hoteles
    List<HotelResponseDTO> getAllHotels();

    // Obtener un hotel por su ID
    HotelResponseDTO getHotelById(Long id);

    // Crear un nuevo hotel
    HotelResponseDTO createHotel(HotelRequestDTO hotelRequestDTO);

    // Actualizar un hotel existente
    HotelResponseDTO updateHotel(Long id, HotelRequestDTO hotelRequestDTO);

    // Eliminar un hotel
    boolean deleteHotel(Long id);
}
