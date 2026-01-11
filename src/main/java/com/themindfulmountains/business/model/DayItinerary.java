package com.themindfulmountains.business.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "day_itinerary")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DayItinerary {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private String dayId;

    private boolean roomsOpted;
    private boolean transportOpted;

    @Temporal(TemporalType.DATE)
    private Date itineraryDay;

    @ManyToOne
    @JoinColumn(name = "query_id")
    private QueryItinerary query;

    @ManyToMany
    @JoinTable(
            name = "day_itinerary_room",
            joinColumns = @JoinColumn(name = "day_id"),
            inverseJoinColumns = @JoinColumn(name = "room_id")
    )
    private List<Room> rooms = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "day_id")
    private List<Transport> transports = new ArrayList<>();
}
