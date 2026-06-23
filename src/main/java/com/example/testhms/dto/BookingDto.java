package com.example.testhms.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

// dto/BookingDto.java
public class BookingDto {
    @Getter @Setter
    public static class BookingRequest {
        private Long roomId;
        private LocalDate checkInDate, checkOutDate;
        private Integer numGuests;
        private String guestName, guestEmail, guestPhone, specialRequests;
    }

    @Getter
    @Setter
    @Builder
    public static class BookingResponse {
        private Long id;
        private String bookingReference, roomName, status;
        private LocalDate checkInDate, checkOutDate;
        private Integer numGuests;
        private BigDecimal totalAmount;
    }
}