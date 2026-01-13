package com.themindfulmountains.business.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.themindfulmountains.business.dto.DaySnapshot;
import com.themindfulmountains.business.dto.QuerySnapshotDTO;
import com.themindfulmountains.business.dto.RoomSnapshot;
import com.themindfulmountains.business.dto.TransportSnapshot;
import com.themindfulmountains.business.dto.request.BookingRequest;
import com.themindfulmountains.business.dto.response.BookingResponse;
import com.themindfulmountains.business.model.Booking;
import com.themindfulmountains.business.model.QueryItinerary;
import com.themindfulmountains.business.repository.BookingRepository;
import com.themindfulmountains.business.repository.CustomerRepository;
import com.themindfulmountains.business.repository.QueryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class BookingService {

    @Autowired
    private QueryRepository queryRepository;

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private CustomerRepository customerRepository;


    @Transactional
    public Booking bookQuery(String queryId, BookingRequest request) {

        QueryItinerary query = queryRepository.findById(queryId)
                .orElseThrow(() -> new RuntimeException("Query not found"));

        if (bookingRepository.existsByQueryId(UUID.fromString(queryId))) {
            throw new RuntimeException("Query already booked");
        }

        Booking booking = new Booking();
        booking.setQueryId(UUID.fromString(query.getItineraryId()));
        booking.setCustomerId(UUID.fromString(query.getCustomer().getCustomerId()));
        booking.setB2bTotal(query.getB2bTotalItineraryCost());
        booking.setB2cTotal(query.getB2cTotalItineraryCost());

        booking.setAdditionalCosts(request.getAdditionalCosts());
        booking.setDiscountAmount(request.getDiscountAmount());
        booking.setFinalCost(request.getFinalCost());
        booking.setAdvancePaid(request.getAdvancePaid());
        booking.setDue(request.getDue());
        booking.setSummary(request.getSummary());

        booking.setStatus("BOOKED");
        booking.setCreatedAt(LocalDateTime.now());

        try {
            QuerySnapshotDTO snapshot = buildSnapshot(query);
            booking.setQuerySnapshot(objectMapper.writeValueAsString(snapshot));
        } catch (Exception e) {
            throw new RuntimeException("Failed to serialize query", e);
        }

        return bookingRepository.save(booking);
    }

    private QuerySnapshotDTO buildSnapshot(QueryItinerary query) {

        QuerySnapshotDTO dto = new QuerySnapshotDTO();
        dto.setQueryId(UUID.fromString(query.getItineraryId()));
        dto.setCustomerId(UUID.fromString(query.getCustomer().getCustomerId()));
        dto.setNoOfAdults(query.getNoOfAdults());
        dto.setNoOfChilds(query.getNoOfChilds());
        dto.setB2bTotalItineraryCost(query.getB2bTotalItineraryCost());
        dto.setB2cTotalItineraryCost(query.getB2cTotalItineraryCost());

        List<DaySnapshot> daySnapshots = query.getDayItineraries().stream().map(day -> {
            DaySnapshot d = new DaySnapshot();
            d.setItineraryDay(day.getItineraryDay());
            d.setRoomsOpted(day.isRoomsOpted());
            d.setTransportOpted(day.isTransportOpted());
            d.setNoOfUnits(day.getNoOfUnits());

            d.setRooms(day.getRooms().stream().map(r -> {
                RoomSnapshot rs = new RoomSnapshot();
                rs.setRoomId(UUID.fromString(r.getRoomId()));
                rs.setRoomName(r.getRoomName());
                rs.setB2bTariff(r.getRoomTariffB2B());
                rs.setB2cTariff(r.getRoomTariffB2C());
                return rs;
            }).toList());

            d.setTransports(day.getTransports().stream().map(t -> {
                TransportSnapshot ts = new TransportSnapshot();
                ts.setName(t.getName());
                ts.setDescription(t.getDescription());
                ts.setContactNo(t.getContactNo());
                ts.setB2bTariff(t.getB2bTariff());
                ts.setB2cTariff(t.getB2cTariff());
                return ts;
            }).toList());

            return d;
        }).toList();

        dto.setDays(daySnapshots);
        return dto;
    }


    public List<BookingResponse> getAllBookingsWithCustomerName() {
        List<Booking> bookings = bookingRepository.findAll();

        return bookings.stream().map(booking -> {
            BookingResponse dto = new BookingResponse();

            dto.setBookingId(booking.getBookingId());
            dto.setQueryId(booking.getQueryId());
            dto.setCustomerId(booking.getCustomerId());
            dto.setB2bTotal(booking.getB2bTotal());
            dto.setB2cTotal(booking.getB2cTotal());
            dto.setAdditionalCosts(booking.getAdditionalCosts());
            dto.setDiscountAmount(booking.getDiscountAmount());
            dto.setFinalCost(booking.getFinalCost());
            dto.setAdvancePaid(booking.getAdvancePaid());
            dto.setDue(booking.getDue());
            dto.setSummary(booking.getSummary());
            dto.setStatus(booking.getStatus());
            dto.setCreatedAt(booking.getCreatedAt());

            customerRepository.findById(String.valueOf(booking.getCustomerId()))
                    .ifPresent(customer -> dto.setCustomerName(customer.getName()));

            return dto;
        }).toList();
    }
    public Booking updateBooking(String bookingId, Booking updated) {
        Booking existing = bookingRepository.findById(UUID.fromString(bookingId))
                .orElseThrow(() -> new RuntimeException("Booking not found"));

        existing.setStatus(updated.getStatus());
        existing.setAdditionalCosts(updated.getAdditionalCosts());
        existing.setDiscountAmount(updated.getDiscountAmount());
        existing.setFinalCost(updated.getFinalCost());
        existing.setAdvancePaid(updated.getAdvancePaid());
        existing.setDue(updated.getDue());
        existing.setSummary(updated.getSummary());

        return bookingRepository.save(existing);
    }

}
