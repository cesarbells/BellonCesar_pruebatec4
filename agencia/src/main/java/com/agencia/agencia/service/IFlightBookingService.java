package com.agencia.agencia.service;

import com.agencia.agencia.dto.FlightBookingRequestDTO;
import com.agencia.agencia.dto.FlightBookingResponseDTO;

import java.util.List;

public interface IFlightBookingService {
    List<FlightBookingResponseDTO> getAllBookings();
    FlightBookingResponseDTO createBooking(FlightBookingRequestDTO bookingRequestDTO);
    }

