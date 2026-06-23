package com.example.testhms.controller;

import com.example.testhms.model.Booking;
import com.example.testhms.service.BookingService;
import com.example.testhms.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

// controller/AdminController.java
@CrossOrigin(origins = "https://wildwingshotel1.onrender.com")
@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminController {
    private final BookingService bookingService;
    private final RoomService roomService;

    @GetMapping("/bookings")
    public ResponseEntity<List<Booking>> allBookings() {
        return ResponseEntity.ok(bookingService.getAllBookings());
    }

    @GetMapping("/dashboard")
    public ResponseEntity<Map<String, Object>> dashboard() {
        var bookings = bookingService.getAllBookings();
        long confirmed = bookings.stream()
                .filter(b -> b.getStatus() == Booking.BookingStatus.CONFIRMED)
                .count();
        return ResponseEntity.ok(Map.of(
                "totalBookings",     bookings.size(),
                "confirmedBookings", confirmed,
                "totalRooms",        roomService.getAllRooms().size()
        ));
    }
}