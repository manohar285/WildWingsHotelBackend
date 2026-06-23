package com.example.testhms.controller;

import com.example.testhms.dto.BookingDto;
import com.example.testhms.model.Booking;
import com.example.testhms.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// controller/BookingController.java
@CrossOrigin(origins = "https://wildwingshotel1.onrender.com")
@RestController
@RequestMapping("/api/bookings")
@RequiredArgsConstructor
public class BookingController {
    private final BookingService bookingService;

    @PostMapping
    public ResponseEntity<Booking> create(
            @RequestBody BookingDto.BookingRequest req) {
        return ResponseEntity.ok(bookingService.createBooking(req));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Booking>> userBookings(
            @PathVariable Long userId) {
        return ResponseEntity.ok(bookingService.getUserBookings(userId));
    }

    @PutMapping("/{id}/cancel")
    public ResponseEntity<Booking> cancel(@PathVariable Long id) {
        return ResponseEntity.ok(bookingService.cancelBooking(id));
    }
}