package com.agencia.agencia.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class FlightBooking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Temporal(TemporalType.TIMESTAMP)  // Almacenar la fecha y hora de la reserva
    private Date date;  // Usar java.util.Date

    private String origin;
    private String destination;
    private String passengerName;

    @ManyToOne
    @JoinColumn(name = "flight_id")
    private Flight flight;

    private Integer peopleQ;
    private String seatType;
}

