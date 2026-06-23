package com.example.testhms.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "locations")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;       // Gachibowli, Madhapur etc.
    private String address;
    private String phone;
    private String description;
    private String imageUrl;

    @OneToMany(mappedBy = "location", cascade = CascadeType.ALL)
    private List<Room> rooms;
}
