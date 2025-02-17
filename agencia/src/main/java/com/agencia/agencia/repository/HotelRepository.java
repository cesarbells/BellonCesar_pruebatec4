
package com.agencia.agencia.repository;

import com.agencia.agencia.model.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface HotelRepository extends JpaRepository<Hotel, Long> {
    // Método para buscar hoteles por destino
    List<Hotel> findByDestination(String destination);

    // Método para buscar un hotel por nombre y ubicación
    Optional<Hotel> findByNameAndLocation(String name, String location);
}

