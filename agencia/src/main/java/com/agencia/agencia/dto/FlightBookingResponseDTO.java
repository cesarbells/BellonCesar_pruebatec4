package com.agencia.agencia.dto;

import com.agencia.agencia.model.FlightBooking;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FlightBookingResponseDTO {

    private Long id;
    private Date date;
    private String origin;
    private String destination;
    private String passengerName;
    private String flightName;  // Aquí se puede obtener el nombre del vuelo
    private Integer peopleQ;
    private String seatType;

    // Constructor que acepta un FlightBooking
    public FlightBookingResponseDTO(FlightBooking flightBooking) {
        this.id = flightBooking.getId();
        this.date = flightBooking.getDate();
        this.origin = flightBooking.getOrigin();
        this.destination = flightBooking.getDestination();
        this.passengerName = flightBooking.getPassengerName();
        this.flightName = flightBooking.getFlight() != null ? flightBooking.getFlight().getFlightName() : null;
        this.peopleQ = flightBooking.getPeopleQ();

    }

}
