package com.example.testhms.repository;

import com.example.testhms.model.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// repository/LocationRepository.java
@Repository
public interface LocationRepository extends JpaRepository<Location, Long> {}
