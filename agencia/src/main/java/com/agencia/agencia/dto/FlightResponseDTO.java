package com.agencia.agencia.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FlightResponseDTO {
    private Long id;
    private String flightNumber;
    private String name;
    private String origin;
    private String destination;
    private String seatType;
    private Double flightPrice;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private Date date;




}
