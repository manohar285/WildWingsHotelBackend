package com.example.testhms.repository;

import com.example.testhms.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

// repository/RoomRepository.java
@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {
    List<Room> findByStatus(Room.RoomStatus status);
    List<Room> findByLocationId(Long locationId);

    // Availability check — excludes rooms with overlapping bookings
    @Query("""
        SELECT r FROM Room r
        WHERE r.status = 'AVAILABLE'
        AND r.maxGuests >= :guests
        AND r.id NOT IN (
            SELECT b.room.id FROM Booking b
            WHERE b.status NOT IN ('CANCELLED')
            AND (b.checkInDate < :checkOut
            AND  b.checkOutDate > :checkIn)
        )
    """)
    List<Room> findAvailableRooms(
            @Param("checkIn") LocalDate checkIn,
            @Param("checkOut") LocalDate checkOut,
            @Param("guests")   int guests
    );
}