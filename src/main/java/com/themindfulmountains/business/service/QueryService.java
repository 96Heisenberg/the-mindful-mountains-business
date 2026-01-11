package com.themindfulmountains.business.service;

import com.themindfulmountains.business.model.Customer;
import com.themindfulmountains.business.model.QueryItinerary;
import com.themindfulmountains.business.model.Room;
import com.themindfulmountains.business.repository.QueryRepository;
import com.themindfulmountains.business.repository.RoomRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class QueryService {

    @Autowired
    private QueryRepository repository;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private RoomRepository roomRepository;


    public void raiseQuery(QueryItinerary query, String customerId) {

        Customer customer = customerService.getCustomerById(customerId)
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        query.setCustomer(customer);

        log.info("No of days for trip {}", query.getDayItineraryList().size());

        query.getDayItineraryList().forEach(day -> {

            if (day.getRooms() != null) {
                List<Room> fullRooms = day.getRooms().stream()
                        .map(r -> roomRepository.findById(r.getRoomId())
                                .orElseThrow(() -> new RuntimeException("Room not found: " + r.getRoomId())))
                        .collect(Collectors.toList());

                day.setRooms(fullRooms);
                day.getRooms().forEach(r ->log.info("Room Details are : {} and price {}",r.getRoomName() , r.getRoomTariffB2C()));
                day.getTransports().forEach(t ->log.info("Transport Details are : {} and price {}",t.getB2bTariff() , t.getContactNo()));

            }

            day.setQuery(query);
        });

        repository.save(query);
    }

    public List<QueryItinerary> getAllQueries() {
        return repository.findAll();
    }

    public QueryItinerary getQueryById(String queryId) {
        return repository.findById(queryId).orElse(null);
    }

    public List<QueryItinerary> getQueriesByCustomerId(String customerId) {
        return repository.findByCustomerCustomerId(customerId);
    }

    public void updateQuery(String queryId, QueryItinerary updated) {
        updated.setItineraryId(queryId);
        QueryItinerary existing = repository.getById(queryId);
        updated.setCustomer(existing.getCustomer());
        repository.save(updated);
    }
}
