package com.example.testhms.service;

// service/LocationService.java
import com.example.testhms.model.Location;
import com.example.testhms.repository.LocationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LocationService {

    private final LocationRepository locationRepository;

    // ── Get all locations ─────────────────────────────────────────────────────
    public List<Location> getAllLocations() {
        return locationRepository.findAll();
    }

    // ── Get single location by ID ─────────────────────────────────────────────
    public Location getLocationById(Long id) {
        return locationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Location not found: " + id));
    }

    // ── Create location (admin) ───────────────────────────────────────────────
    public Location createLocation(Location location) {
        return locationRepository.save(location);
    }

    // ── Update location (admin) ───────────────────────────────────────────────
    public Location updateLocation(Long id, Location updated) {
        Location existing = locationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Location not found: " + id));

        existing.setName(updated.getName());
        existing.setAddress(updated.getAddress());
        existing.setPhone(updated.getPhone());
        existing.setDescription(updated.getDescription());
        existing.setImageUrl(updated.getImageUrl());

        return locationRepository.save(existing);
    }

    // ── Delete location (admin) ───────────────────────────────────────────────
    public void deleteLocation(Long id) {
        if (!locationRepository.existsById(id)) {
            throw new RuntimeException("Location not found: " + id);
        }
        locationRepository.deleteById(id);
    }
}