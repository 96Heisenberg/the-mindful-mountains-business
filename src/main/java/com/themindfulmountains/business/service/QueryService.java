package com.themindfulmountains.business.service;

import com.themindfulmountains.business.model.*;
import com.themindfulmountains.business.repository.QueryRepository;
import com.themindfulmountains.business.repository.RoomRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
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

        log.info("No of days for trip {}", query.getDayItineraries().size());

        query.getDayItineraries().forEach(day -> {

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

    @Transactional
    public void updateQuery(String queryId, QueryItinerary updated) {

        QueryItinerary existing = repository.findById(queryId)
                .orElseThrow(() -> new RuntimeException("Query not found"));

        // Update scalar fields
        existing.setQueryPdfLink(updated.getQueryPdfLink());
        existing.setNoOfAdults(updated.getNoOfAdults());
        existing.setNoOfChilds(updated.getNoOfChilds());
        existing.setB2bTotalItineraryCost(updated.getB2bTotalItineraryCost());
        existing.setB2cTotalItineraryCost(updated.getB2cTotalItineraryCost());

        // Remove old days
        existing.getDayItineraries().clear();
        
        log.info("Updated itinerary size {}", updated.getDayItineraries().size());

        for (DayItinerary incomingDay : updated.getDayItineraries()) {

            DayItinerary day = new DayItinerary();
            day.setItineraryDay(incomingDay.getItineraryDay());
            day.setRoomsOpted(incomingDay.isRoomsOpted());
            day.setTransportOpted(incomingDay.isTransportOpted());
            day.setQuery(existing);

            // Rooms (ManyToMany, already exist)
            if (incomingDay.getRooms() != null) {
                List<Room> rooms = incomingDay.getRooms().stream()
                        .map(r -> roomRepository.findById(r.getRoomId())
                                .orElseThrow(() -> new RuntimeException("Room not found: " + r.getRoomId())))
                        .toList();
                day.setRooms(rooms);
            }

            // 🚨 IMPORTANT: add day to parent BEFORE transports
            existing.getDayItineraries().add(day);
        }

        log.info("Existing itinerary size {}", existing.getDayItineraries().size());

        // 🚨 Persist days FIRST
        repository.saveAndFlush(existing);

        // 🚨 NOW attach transports (day_id exists now)
        for (int i = 0; i < existing.getDayItineraries().size(); i++) {
            DayItinerary persistedDay = existing.getDayItineraries().get(i);
            DayItinerary incomingDay = updated.getDayItineraries().get(i);

            if (incomingDay.getTransports() != null) {
                List<Transport> transports = incomingDay.getTransports().stream()
                        .map(t -> {
                            Transport tr = new Transport();
                            tr.setName(t.getName());
                            tr.setDescription(t.getDescription());
                            tr.setContactNo(t.getContactNo());
                            tr.setBookingVia(t.getBookingVia());
                            tr.setB2cTariff(t.getB2cTariff());
                            tr.setB2bTariff(t.getB2bTariff());
                            return tr;
                        })
                        .toList();

                persistedDay.getTransports().addAll(transports);
            }
        }

        repository.save(existing);
    }



}
