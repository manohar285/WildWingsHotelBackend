package com.example.testhms.controller;

import com.example.testhms.dto.RoomDto;
import com.example.testhms.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

// controller/RoomController.java
@CrossOrigin(origins = "https://wildwingshotel1.onrender.com")
@RestController
@RequestMapping("/api/rooms")
@RequiredArgsConstructor
public class RoomController {
    private final RoomService roomService;

    @GetMapping
    public ResponseEntity<List<RoomDto.RoomResponse>> getAll() {
        return ResponseEntity.ok(roomService.getAllRooms());
    }

    @GetMapping("/{id}")
    public ResponseEntity<RoomDto.RoomResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(roomService.getRoomById(id));
    }

    @GetMapping("/available")
    public ResponseEntity<List<RoomDto.RoomResponse>> available(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate checkIn,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate checkOut,
            @RequestParam(defaultValue = "1") int guests) {
        return ResponseEntity.ok(
                roomService.getAvailableRooms(checkIn, checkOut, guests));
    }

    @PostMapping          // admin only
    public ResponseEntity<RoomDto.RoomResponse> create(
            @RequestBody RoomDto.RoomRequest req) {
        return ResponseEntity.ok(roomService.createRoom(req));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        roomService.deleteRoom(id);
        return ResponseEntity.noContent().build();
    }
}
