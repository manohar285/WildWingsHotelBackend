package com.example.testhms.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "rooms")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;       // Suite Room, Deluxe Room, Glass Room
    private String type;       // SUITE, DELUXE, GLASS, STANDARD
    private BigDecimal pricePerNight;
    private String description;
    private String size;
    private Integer maxGuests;
    private Integer floorNumber;
    private String imageUrl;

    @Enumerated(EnumType.STRING)
    private RoomStatus status;

    @ElementCollection
    @CollectionTable(name = "room_amenities",
            joinColumns = @JoinColumn(name = "room_id"))
    @Column(name = "amenity")
    private List<String> amenities;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "location_id")
    private Location location;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        if (status == null) status = RoomStatus.AVAILABLE;
    }

    public enum RoomStatus { AVAILABLE, OCCUPIED, MAINTENANCE }
}
