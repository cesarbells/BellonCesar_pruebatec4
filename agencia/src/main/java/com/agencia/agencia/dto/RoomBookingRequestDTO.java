package com.agencia.agencia.dto;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RoomBookingRequestDTO {

    private Long hotelId;  // ID del hotel
    private String roomType; // Tipo de habitación
    private String customerName;  // Nombre del cliente
    private LocalDate checkInDate;  // Fecha de entrada
    private LocalDate checkOutDate; // Fecha de salida
    private Long id;
    private BigDecimal pricePerNight;
    private LocalDate availableFrom;
    private LocalDate availableUntil;
    private String Destination;

}


