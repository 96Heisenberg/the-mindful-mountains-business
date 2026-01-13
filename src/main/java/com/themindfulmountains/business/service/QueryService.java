package com.themindfulmountains.business.service;

import com.themindfulmountains.business.dto.request.DayItineraryRequest;
import com.themindfulmountains.business.dto.request.QueryRequest;
import com.themindfulmountains.business.dto.request.RoomRef;
import com.themindfulmountains.business.dto.request.TransportRequest;
import com.themindfulmountains.business.dto.response.QueryResponse;
import com.themindfulmountains.business.mapper.QueryMapper;
import com.themindfulmountains.business.model.*;
import com.themindfulmountains.business.repository.QueryRepository;
import com.themindfulmountains.business.repository.RoomRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
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


    public QueryResponse raiseQuery(QueryRequest request, String customerId) {

        Customer customer = customerService.getCustomerById(customerId)
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        QueryItinerary query = new QueryItinerary();
        query.setCustomer(customer);
        query.setNoOfAdults(request.getNoOfAdults());
        query.setNoOfChilds(request.getNoOfChilds());

        BigDecimal totalB2B = BigDecimal.ZERO;
        BigDecimal totalB2C = BigDecimal.ZERO;

        for(DayItineraryRequest d : request.getDayItineraries()) {

            DayItinerary day = new DayItinerary();
            day.setItineraryDay(java.sql.Date.valueOf(d.getItineraryDay()));
            day.setRoomsOpted(d.isRoomsOpted());
            day.setTransportOpted(d.isTransportOpted());
            day.setNoOfUnits(d.getNoOfUnits());
            day.setQuery(query);

            // Resolve rooms
            if (d.getRooms() != null) {
                List<Room> rooms = roomRepository.findAllById(d.getRooms().stream().map(RoomRef::getRoomId).collect(Collectors.toList()));

                day.setRooms(rooms);

                for (Room r : rooms) {
                    totalB2B = totalB2B.add(r.getRoomTariffB2B().multiply(BigDecimal.valueOf(d.getNoOfUnits())));
                    totalB2C = totalB2C.add(r.getRoomTariffB2C().multiply(BigDecimal.valueOf(d.getNoOfUnits())));
                }
            }

            // Map transports
            if (d.getTransports() != null) {
                for(TransportRequest t : d.getTransports()) {
                    Transport tr = new Transport();
                    tr.setName(t.getName());
                    tr.setDescription(t.getDescription());
                    tr.setContactNo(t.getContactNo());
                    tr.setBookingVia(t.getBookingVia());
                    tr.setB2bTariff(t.getB2bTariff());
                    tr.setB2cTariff(t.getB2cTariff());

                    totalB2B = totalB2B.add(t.getB2bTariff());
                    totalB2C = totalB2C.add(t.getB2cTariff());

                    day.getTransports().add(tr);

                };

            }

            query.getDayItineraries().add(day);
        }

        log.info("Prices are b2b {} b2c {}", totalB2B , totalB2C);
        query.setB2bTotalItineraryCost(totalB2B);
        query.setB2cTotalItineraryCost(totalB2C);

        // Generate PDF link
        query.setQueryPdfLink(generatePdfLink());

        repository.save(query);

        return QueryMapper.toResponse(query);
    }

    private String generatePdfLink() {
        return "https://cdn.themindfulmountains.com/queries/q-" + System.currentTimeMillis() + ".pdf";
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
    public QueryResponse updateQuery(String queryId, QueryRequest request) {

        QueryItinerary existing = repository.findById(queryId)
                .orElseThrow(() -> new RuntimeException("Query not found"));

        existing.setNoOfAdults(request.getNoOfAdults());
        existing.setNoOfChilds(request.getNoOfChilds());

        // Clear existing days (orphan removal will handle deletes)
        existing.getDayItineraries().clear();

        BigDecimal totalB2B = BigDecimal.ZERO;
        BigDecimal totalB2C = BigDecimal.ZERO;

        for (DayItineraryRequest d : request.getDayItineraries()) {

            DayItinerary day = new DayItinerary();
            day.setItineraryDay(java.sql.Date.valueOf(d.getItineraryDay()));
            day.setRoomsOpted(d.isRoomsOpted());
            day.setTransportOpted(d.isTransportOpted());
            day.setQuery(existing);
            day.setNoOfUnits(d.getNoOfUnits());
            log.info("No Uunits coming {}", day.getNoOfUnits());
            // Resolve Rooms
            if (d.getRooms() != null) {
                List<Room> rooms = roomRepository.findAllById(
                        d.getRooms().stream()
                                .map(RoomRef::getRoomId)
                                .toList()
                );

                day.setRooms(rooms);

                for (Room r : rooms) {
                    log.info("No Uunits coming {}", d.getNoOfUnits());
                    totalB2B = totalB2B.add(r.getRoomTariffB2B().multiply(BigDecimal.valueOf(d.getNoOfUnits())));
                    totalB2C = totalB2C.add(r.getRoomTariffB2C().multiply(BigDecimal.valueOf(d.getNoOfUnits())));
                }
            }

            // Map Transports
            if (d.getTransports() != null) {
                for (TransportRequest t : d.getTransports()) {
                    Transport tr = new Transport();
                    tr.setName(t.getName());
                    tr.setDescription(t.getDescription());
                    tr.setContactNo(t.getContactNo());
                    tr.setBookingVia(t.getBookingVia());
                    tr.setB2bTariff(t.getB2bTariff());
                    tr.setB2cTariff(t.getB2cTariff());

                    totalB2B = totalB2B.add(t.getB2bTariff());
                    totalB2C = totalB2C.add(t.getB2cTariff());

                    day.getTransports().add(tr);
                }
            }

            existing.getDayItineraries().add(day);
        }

        existing.setB2bTotalItineraryCost(totalB2B);
        existing.setB2cTotalItineraryCost(totalB2C);
        existing.setQueryPdfLink(generatePdfLink()); // regenerate

        repository.save(existing);

        return QueryMapper.toResponse(existing);
    }

    @Transactional
    public void deleteQuery(String queryId) {
        if (!repository.existsById(queryId)) {
            throw new RuntimeException("Query not found with id: " + queryId);
        }
        repository.deleteById(queryId);
    }




}
