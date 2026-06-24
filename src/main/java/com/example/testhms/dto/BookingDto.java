// dto/BookingDto.java
package com.example.testhms.dto;

import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDate;

public class BookingDto {

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class BookingRequest {
        private Long roomId;
        private LocalDate checkInDate;
        private LocalDate checkOutDate;
        private Integer numGuests;
        private String guestName;
        private String guestEmail;
        private String guestPhone;
        private String specialRequests;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class BookingResponse {
        private Long id;
        private String bookingReference;
        private String roomName;
        private String status;
        private LocalDate checkInDate;
        private LocalDate checkOutDate;
        private Integer numGuests;
        private BigDecimal totalAmount;
    }
}








//package com.example.testhms.dto;
//
//import lombok.Builder;
//import lombok.Getter;
//import lombok.Setter;
//
//import java.math.BigDecimal;
//import java.time.LocalDate;
//  @Getter
//  @Setter
//// dto/BookingDto.java
//public class BookingDto {
//
//    public static class BookingRequest {
//        private Long roomId;
//        private LocalDate checkInDate;
//        private LocalDate checkOutDate;
//        private Integer numGuests;
//        private String guestName;
//        private String guestEmail;
//        private String guestPhone;
//        private String specialRequests;guestName, guestEmail, guestPhone, specialRequests;
//
//    }
//
//    @Getter
//    @Setter
//    @Builder
//    public static class BookingResponse {
//        private Long id;
//        private String bookingReference;
//        private String roomName;
//        private String status;
//        private LocalDate checkInDate;
//        private LocalDate checkOutDate;
//        private Integer numGuests;
//        private BigDecimal totalAmount;
//    }
//}