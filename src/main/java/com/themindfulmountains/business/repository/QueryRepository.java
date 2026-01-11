package com.themindfulmountains.business.repository;

import com.themindfulmountains.business.model.QueryItinerary;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QueryRepository extends JpaRepository<QueryItinerary, String> {

    List<QueryItinerary> findByCustomerCustomerId(String customerId);
}
