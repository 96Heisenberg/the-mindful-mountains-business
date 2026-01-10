package com.themindfulmountains.business.service;

import com.themindfulmountains.business.model.Property;
import com.themindfulmountains.business.model.Room;
import com.themindfulmountains.business.payload.request.RoomRequestPayload;
import com.themindfulmountains.business.payload.response.RoomResponsePayload;
import com.themindfulmountains.business.repository.PropertyRepository;
import com.themindfulmountains.business.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RoomService {

    @Autowired
    RoomRepository roomRepository;

    @Autowired
    PropertyRepository propertyRepository;

    public boolean addRoomToProperty(String propertyId, RoomRequestPayload payload) {
        Optional<Property> propertyOptional = propertyRepository.findById(propertyId);
        if (propertyOptional.isPresent()) {
            Property property = propertyOptional.get();
            Room room = mapToRoom(payload);
            room.setProperty(property);
            roomRepository.save(room);
            return true;
        }
        return false;
    }

    public List<RoomResponsePayload> getRoomsByPropertyId(String propertyId) {
        return StringUtils.isEmpty(propertyId) ?
                roomRepository.findAll().stream().map(this::convertToRoomResponsePayload)
                        .collect(Collectors.toList())
                : roomRepository.findByPropertyPropertyId(propertyId).stream()
                .map(this::convertToRoomResponsePayload)
                .collect(Collectors.toList());
    }

    public Optional<RoomResponsePayload> getRoomById(String roomId) {
        return roomRepository.findById(roomId).map(this::convertToRoomResponsePayload);
    }

    private RoomResponsePayload convertToRoomResponsePayload(Room room) {
        RoomResponsePayload payload = new RoomResponsePayload();
        payload.setRoomId(room.getRoomId());
        payload.setRoomName(room.getRoomName());
        payload.setServices(room.getServices());
        payload.setRoomTariffB2C(room.getRoomTariffB2C());
        payload.setRoomTariffB2B(room.getRoomTariffB2B());

        // Map Property details
        if (room.getProperty() != null) {
            payload.setPropertyId(room.getProperty().getPropertyId());
            payload.setPropertyName(room.getProperty().getName());
            payload.setPropertyLocation(room.getProperty().getLocation());
        }

        return payload;
    }

    public boolean updateRoom(String roomId, RoomRequestPayload payload) {
        Optional<Room> existingRoom = roomRepository.findById(roomId);
        if (existingRoom.isPresent()) {
            Room room = existingRoom.get();
            room.setRoomName(payload.getRoomName());
            room.setServices(payload.getServices());
            room.setRoomTariffB2C(payload.getRoomTariffB2C());
            room.setRoomTariffB2B(payload.getRoomTariffB2B());

            roomRepository.save(room);
            return true;
        }
        return false;
    }

    //TODO Implement Mapstruct Later
    private Room mapToRoom(RoomRequestPayload payload) {
        Room room = new Room();
        room.setRoomName(payload.getRoomName());
        room.setServices(payload.getServices());
        room.setRoomTariffB2C(payload.getRoomTariffB2C());
        room.setRoomTariffB2B(payload.getRoomTariffB2B());
        return room;
    }
}
