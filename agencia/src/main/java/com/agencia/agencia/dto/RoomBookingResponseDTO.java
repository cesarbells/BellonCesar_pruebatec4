package com.agencia.agencia.dto;


import com.agencia.agencia.model.RoomBooking;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RoomBookingResponseDTO {

    private Long id;
    private Long hotelId;
    private String roomType;
    private BigDecimal pricePerNight;
    private LocalDate availableFrom;
    private LocalDate availableUntil;
    private String customerName;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;
    private String Destination;

    // Constructor a partir de la entidad RoomBooking
    public RoomBookingResponseDTO(RoomBooking roomBooking) {
        this.id = roomBooking.getId();
        this.hotelId = roomBooking.getHotel().getId();  // Extraemos el ID del hotel asociado
        this.roomType = roomBooking.getRoomType();
        this.pricePerNight = roomBooking.getPricePerNight();
        this.availableFrom = roomBooking.getAvailableFrom();
        this.availableUntil = roomBooking.getAvailableUntil();
        this.customerName = roomBooking.getCustomerName();
        this.checkInDate = roomBooking.getCheckIn();
        this.checkOutDate = roomBooking.getCheckOut();
        this.Destination= roomBooking.getDestination();
    }
}


