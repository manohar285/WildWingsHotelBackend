package com.example.testhms.service;

import com.example.testhms.dto.BookingDto;
import com.example.testhms.model.Booking;
import com.example.testhms.model.Room;
import com.example.testhms.repository.BookingRepository;
import com.example.testhms.repository.RoomRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.UUID;

// service/BookingService.java
@Service
@RequiredArgsConstructor
@Transactional
public class BookingService {
    private final BookingRepository bookingRepo;
    private final RoomRepository roomRepo;

    public Booking createBooking(BookingDto.BookingRequest req) {
        Room room = roomRepo.findById(req.getRoomId())
                .orElseThrow(() -> new RuntimeException("Room not found"));

        long nights = ChronoUnit.DAYS.between(
                req.getCheckInDate(), req.getCheckOutDate());
        if (nights < 1) throw new RuntimeException("Invalid dates");

        BigDecimal total = room.getPricePerNight()
                .multiply(BigDecimal.valueOf(nights));

        Booking booking = Booking.builder()
                .bookingReference("WW-" + UUID.randomUUID()
                        .toString().substring(0,8).toUpperCase())
                .room(room)
                .checkInDate(req.getCheckInDate())
                .checkOutDate(req.getCheckOutDate())
                .numGuests(req.getNumGuests())
                .totalAmount(total)
                .guestName(req.getGuestName())
                .guestEmail(req.getGuestEmail())
                .guestPhone(req.getGuestPhone())
                .specialRequests(req.getSpecialRequests())
                .status(Booking.BookingStatus.CONFIRMED)
                .build();

        return bookingRepo.save(booking);
    }

    public Booking cancelBooking(Long id) {
        Booking b = bookingRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Not found"));
        b.setStatus(Booking.BookingStatus.CANCELLED);
        return bookingRepo.save(b);
    }

    public List<Booking> getUserBookings(Long userId) {
        return bookingRepo.findByUserId(userId);
    }

    public List<Booking> getAllBookings() {
        return bookingRepo.findAll();
    }
}
