package com.agencia.agencia.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class FlightBookingRequestDTO {
    private Date date;  // Cambié Date a LocalDate
    private String origin;
    private String destination;
    private String passengerName;
    private Long flightId;  // Usando Long como ID
    private Integer peopleQ;
    private String seatType;


    public String getSeatType() {
        return seatType;
    }

    public void setSeatType(String seatType) {
        this.seatType = seatType;
    }
}

