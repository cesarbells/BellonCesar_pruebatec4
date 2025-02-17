package com.agencia.agencia.dto;

import com.agencia.agencia.model.Hotel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HotelResponseDTO {
    private Long id;
    private String name;
    private String location;

    public HotelResponseDTO(Hotel hotel) {
        this.id = hotel.getId();
        this.name = hotel.getName();
        this.location = hotel.getLocation();
    }
}

