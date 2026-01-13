package com.themindfulmountains.business.repository;

import com.themindfulmountains.business.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BookingRepository extends JpaRepository<Booking, UUID> {
    boolean existsByQueryId(UUID queryId);
}
