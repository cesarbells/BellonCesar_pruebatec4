package com.agencia.agencia.repository;

import com.agencia.agencia.model.Hotel;
import com.agencia.agencia.model.RoomBooking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface RoomBookingRepository extends JpaRepository<RoomBooking, Long> {
//Método para encontrar las reservas asociadas a un hotel
    List<RoomBooking> findByHotel(Hotel hotel);
    // Método para obtener reservas de habitaciones por destino y rango de fechas
    @Query("SELECT rb FROM RoomBooking rb WHERE rb.destination = :destination " +
            "AND rb.availableFrom <= :dateTo AND rb.availableUntil >= :dateFrom " +
            "AND (rb.checkIn IS NULL OR rb.checkOut IS NULL OR rb.checkOut < :dateFrom OR rb.checkIn > :dateTo)")
    List<RoomBooking> findByDestinationAndAvailableDates(
            @Param("destination") String destination,
            @Param("dateFrom") LocalDate dateFrom,
            @Param("dateTo") LocalDate dateTo
    );



    // Método para obtener las reservas por hotelId
    List<RoomBooking> findByHotelId(Long hotelId);
}
