package com.themindfulmountains.business.repository;

import com.themindfulmountains.business.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoomRepository extends JpaRepository<Room, String> {
    // Get all rooms for a given property ID
    List<Room> findByPropertyPropertyId(String propertyId);
}
