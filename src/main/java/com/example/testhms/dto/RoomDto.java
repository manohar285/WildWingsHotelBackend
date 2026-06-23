package com.example.testhms.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

// dto/RoomDto.java
public class RoomDto {
    @Getter @Setter @Builder
    public static class RoomResponse {
        private Long id;
        private String name, type, description, size, status, imageUrl, locationName;
        private BigDecimal pricePerNight;
        private Integer maxGuests, floorNumber;
        private List<String> amenities;
    }

    @Getter
    @Setter
    public static class RoomRequest {
        private String name, type, description, size, imageUrl;
        private BigDecimal pricePerNight;
        private Integer maxGuests, floorNumber;
        private List<String> amenities;
        private Long locationId;
    }
}