package com.agencia.agencia.service;


import com.agencia.agencia.dto.FlightRequestDTO;
import com.agencia.agencia.dto.FlightResponseDTO;

import java.util.Date;
import java.util.List;

public interface IFlightService {
        List<FlightResponseDTO> getAllFlights();
        List<FlightResponseDTO> getAvailableFlights(Date dateFrom, Date dateTo, String origin, String destination);
        FlightResponseDTO createFlight(FlightRequestDTO flightRequestDTO);
}
