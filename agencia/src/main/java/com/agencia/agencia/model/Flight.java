package com.agencia.agencia.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Flight {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String flightNumber;
    private String flightName;
    private String origin;
    private String destination;
    private String seatType;
    private Double flightPrice;

    @Temporal(TemporalType.DATE)
    private Date date;

    @OneToMany(mappedBy = "flight", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<FlightBooking> bookings = new ArrayList<>();

    public String getFlightName() {
        return flightName;
    }

    public void setFlightName(String flightName) {
        this.flightName = flightName;
    }

    public Flight(String flightNumber, String flightName, String origin, String destination,
                  String seatType, Double flightPrice, Date date) {
        this.flightNumber = flightNumber;
        this.flightName = flightName;
        this.origin = origin;
        this.destination = destination;
        this.seatType = seatType;
        this.flightPrice = flightPrice;
        this.date = date;
    }
}

