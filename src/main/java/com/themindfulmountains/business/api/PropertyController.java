package com.themindfulmountains.business.api;

import com.themindfulmountains.business.payload.request.PropertyRequestPayload;
import com.themindfulmountains.business.payload.request.RoomRequestPayload;
import com.themindfulmountains.business.payload.response.PropertyResponsePayload;
import com.themindfulmountains.business.payload.response.RoomResponsePayload;
import com.themindfulmountains.business.service.PropertyService;
import com.themindfulmountains.business.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
@Controller
@RequestMapping("/property")
public class PropertyController {

    @Autowired
    PropertyService service;

    @Autowired
    RoomService roomService;

    @PostMapping
    public ResponseEntity<String> onboardProperty(@RequestBody PropertyRequestPayload payload) {
        service.onboardProperty(payload);
        return ResponseEntity.ok("Thanks for request Submission. Our team will contact you soon!");
    }

    @GetMapping("/all")
    public ResponseEntity<List<PropertyResponsePayload>> getAllProperties() {
        return ResponseEntity.ok(service.getAllProperties());
    }

    @GetMapping("/{propertyId}")
    public ResponseEntity<PropertyResponsePayload> getPropertyById(@PathVariable String propertyId) {
        Optional<PropertyResponsePayload> property = service.getPropertyById(propertyId);
        return property.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{propertyId}")
    public ResponseEntity<String> updateProperty(@PathVariable String propertyId, @RequestBody PropertyRequestPayload payload) {
        boolean isUpdated = service.updateProperty(propertyId, payload);
        if (isUpdated) {
            return ResponseEntity.ok("Property updated successfully!");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/{propertyId}/room")
    public ResponseEntity<String> addRoomToProperty(@PathVariable String propertyId, @RequestBody RoomRequestPayload payload) {
        boolean isAdded = roomService.addRoomToProperty(propertyId, payload);
        if (isAdded) {
            return ResponseEntity.ok("Room added successfully!");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{propertyId}/room/all")
    public ResponseEntity<List<RoomResponsePayload>> getRoomsByPropertyId(@PathVariable String propertyId) {
        List<RoomResponsePayload> rooms = roomService.getRoomsByPropertyId(propertyId);
        if (!rooms.isEmpty()) {
            return ResponseEntity.ok(rooms);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/room/{roomId}")
    public ResponseEntity<RoomResponsePayload> getRoomById(@PathVariable String roomId) {
        Optional<RoomResponsePayload> room = roomService.getRoomById(roomId);
        return room.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/room/{roomId}")
    public ResponseEntity<String> updateRoom(@PathVariable String roomId, @RequestBody RoomRequestPayload payload) {
        boolean isUpdated = roomService.updateRoom(roomId, payload);
        if (isUpdated) {
            return ResponseEntity.ok("Room updated successfully!");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
