package com.example.testhms.service;

import com.example.testhms.dto.RoomDto;
import com.example.testhms.model.Room;
import com.example.testhms.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

// service/RoomService.java
@Service
@RequiredArgsConstructor
public class RoomService {
    private final RoomRepository roomRepo;

    public List<RoomDto.RoomResponse> getAllRooms() {
        return roomRepo.findAll().stream().map(this::toDto).toList();
    }

    public RoomDto.RoomResponse getRoomById(Long id) {
        return toDto(roomRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Room not found")));
    }

    public List<RoomDto.RoomResponse> getAvailableRooms(
            LocalDate checkIn, LocalDate checkOut, int guests) {
        return roomRepo.findAvailableRooms(checkIn, checkOut, guests)
                .stream().map(this::toDto).toList();
    }

    public RoomDto.RoomResponse createRoom(RoomDto.RoomRequest req) {
        Room room = Room.builder()
                .name(req.getName()).type(req.getType())
                .pricePerNight(req.getPricePerNight())
                .description(req.getDescription()).size(req.getSize())
                .maxGuests(req.getMaxGuests()).floorNumber(req.getFloorNumber())
                .imageUrl(req.getImageUrl()).amenities(req.getAmenities())
                .build();
        return toDto(roomRepo.save(room));
    }

    public void deleteRoom(Long id) { roomRepo.deleteById(id); }

    private RoomDto.RoomResponse toDto(Room r) {
        return RoomDto.RoomResponse.builder()
                .id(r.getId()).name(r.getName()).type(r.getType())
                .pricePerNight(r.getPricePerNight())
                .description(r.getDescription()).size(r.getSize())
                .maxGuests(r.getMaxGuests()).floorNumber(r.getFloorNumber())
                .status(r.getStatus().name()).imageUrl(r.getImageUrl())
                .amenities(r.getAmenities())
                .locationName(r.getLocation() != null ? r.getLocation().getName() : null)
                .build();
    }
}
