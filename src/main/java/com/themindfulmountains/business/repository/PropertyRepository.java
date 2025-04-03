package com.themindfulmountains.business.repository;

import com.themindfulmountains.business.model.Property;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PropertyRepository extends JpaRepository<Property, Long> {
}
