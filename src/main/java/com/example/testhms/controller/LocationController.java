package com.example.testhms.controller;

import com.example.testhms.model.Location;
import com.example.testhms.service.LocationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.testhms.model.Location;
import com.example.testhms.service.LocationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@CrossOrigin(origins = "https://wildwingshotel1.onrender.com")
    @RestController
    @RequestMapping("/api/locations")
    @RequiredArgsConstructor
    public class LocationController {

        private final LocationService locationService;

        // ── GET all locations ─────────────────────────────────────────────────────
        // GET /api/locations
        @GetMapping
        public ResponseEntity<List<Location>> getAllLocations() {
            return ResponseEntity.ok(locationService.getAllLocations());
        }

        // ── GET single location by ID ─────────────────────────────────────────────
        // GET /api/locations/3
        @GetMapping("/{id}")
        public ResponseEntity<Location> getLocationById(@PathVariable Long id) {
            return ResponseEntity.ok(locationService.getLocationById(id));
        }

        // ── CREATE location (admin only) ──────────────────────────────────────────
        // POST /api/locations
        @PostMapping
        public ResponseEntity<Location> createLocation(@RequestBody Location location) {
            Location created = locationService.createLocation(location);
            return ResponseEntity.status(201).body(created);
        }

        // ── UPDATE location (admin only) ──────────────────────────────────────────
        // PUT /api/locations/3
        @PutMapping("/{id}")
        public ResponseEntity<Location> updateLocation(
                @PathVariable Long id,
                @RequestBody Location location) {
            return ResponseEntity.ok(locationService.updateLocation(id, location));
        }

        // ── DELETE location (admin only) ──────────────────────────────────────────
        // DELETE /api/locations/3
        @DeleteMapping("/{id}")
        public ResponseEntity<Void> deleteLocation(@PathVariable Long id) {
            locationService.deleteLocation(id);
            return ResponseEntity.noContent().build();
        }
    }
