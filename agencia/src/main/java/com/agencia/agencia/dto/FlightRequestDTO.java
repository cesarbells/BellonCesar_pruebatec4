package com.agencia.agencia.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FlightRequestDTO {
    private String flightNumber;
    private String name;
    private String origin;
    private String destination;
    private String seatType;
    private Double flightPrice;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private Date date;



}
