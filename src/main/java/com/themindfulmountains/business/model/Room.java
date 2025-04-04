package com.themindfulmountains.business.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "room")
public class Room {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "room_id", updatable = false, nullable = false)
    private String roomId;

    @Column(name = "room_name")
    private String roomName;

    private String services;

    @Column(name = "room_tariff_b2c")
    private BigDecimal roomTariffB2C;

    @Column(name = "room_tariff_b2b")
    private BigDecimal roomTariffB2B;

    // Many rooms to one property
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "property_id")
    private Property property;
}
